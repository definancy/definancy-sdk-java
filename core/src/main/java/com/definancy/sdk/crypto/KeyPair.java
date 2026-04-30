package com.definancy.sdk.crypto;

import com.definancy.sdk.auth.Jwk;
import com.definancy.sdk.auth.Signer;
import com.definancy.sdk.util.CryptoProvider;
import com.definancy.sdk.util.Encoder;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;

import java.nio.charset.StandardCharsets;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Arrays;
import java.util.Objects;

public class KeyPair implements Signer {
    private final java.security.KeyPair privateKeyPair;

    private static final String KEY_ALGO = "Ed25519";
    private static final String SIGN_ALGO = "EdDSA";
    private static final int PK_SIZE = 32;
    private static final int SK_SIZE = 32;
    private static final int SK_SIZE_BITS = SK_SIZE * 8;
    private static final int SK_PKCS_PREFIX_LENGTH = 16; // Ed25519 specific
    private static final int SK_SEPARATOR_LENGTH = 3; // separator is 0x81 0x21 0x00

    /**
     * KeyPair creates a new, random account.
     */
    private KeyPair() throws Exception {
        this((SecureRandom)null);
    }

    public static KeyPair generateKeyPair() throws Exception {
        return new KeyPair();
    }

    public static KeyPair generateKeyPairFromSecret(String secret) throws Exception {
        Objects.requireNonNull(secret, "secret must not be null");
        byte[] bytes = Encoder.decodeFromBase64(secret);

        if (bytes.length != SK_SIZE) {
            throw new IllegalArgumentException("secret length must be " + SK_SIZE + " bytes");
        }

        return new KeyPair(bytes);
    }

    public KeyPair(byte[] seed) throws Exception {
        // seed here corresponds to rfc8037 private key, corresponds to seed in go impl
        // BC for instance takes the seed as private key straight up
        this(new FixedSecureRandom(seed));
    }

    // randomSrc can be null, in which case system default is used
    public KeyPair(SecureRandom randomSrc) throws Exception {
        CryptoProvider.setupIfNeeded();
        KeyPairGenerator gen = KeyPairGenerator.getInstance(KEY_ALGO);
        if (randomSrc != null) {
            gen.initialize(SK_SIZE_BITS, randomSrc);
        }
        this.privateKeyPair = gen.generateKeyPair();
    }

    public KeyPair(PrivateKey pk) throws Exception {
        CryptoProvider.setupIfNeeded();
        if (!pk.getAlgorithm().equals(KEY_ALGO))
            throw new IllegalArgumentException("KeyPair cannot be generated from a non-Ed25519 key");

        if (pk.getEncoded().length != SK_PKCS_PREFIX_LENGTH + SK_SIZE + SK_SEPARATOR_LENGTH + PK_SIZE)
            throw new RuntimeException("Private Key cannot generate clear private key bytes");

        byte[] clearPrivateKey = new byte[SK_SIZE];
        System.arraycopy(pk.getEncoded(), SK_PKCS_PREFIX_LENGTH, clearPrivateKey, 0, SK_SIZE);

        KeyPairGenerator gen = KeyPairGenerator.getInstance(KEY_ALGO);
        gen.initialize(SK_SIZE_BITS, new FixedSecureRandom(clearPrivateKey));
        this.privateKeyPair = gen.generateKeyPair();
    }

    public String export() throws Exception {
        byte[] X509enc = this.privateKeyPair.getPrivate().getEncoded();
        PrivateKeyInfo pkinfo = PrivateKeyInfo.getInstance(X509enc);
        try {
            ASN1Encodable keyOcts = pkinfo.parsePrivateKey();
            byte[] res = ASN1OctetString.getInstance(keyOcts).getOctets();
            return Encoder.encodeToBase64(res);
        } catch (Exception e) {
            throw new RuntimeException("unexpected behavior", e);
        }
    }

    public Ed25519PublicKey publicKey() {
        return new Ed25519PublicKey(this.privateKeyPair.getPublic());
    }

    public Jwk jwk() throws Exception{
        return this.publicKey().jwk();
    }

    /**
     * Sign raw message bytes and return the base64url-encoded signature.
     * The primary implementation; {@link #sign(String)} delegates here
     * after UTF-8 encoding the string.
     */
    public String sign(byte[] data) throws Exception {
        CryptoProvider.setupIfNeeded();
        Signature signer = Signature.getInstance(SIGN_ALGO);
        signer.initSign(this.privateKeyPair.getPrivate());
        signer.update(data);
        byte[] signature = signer.sign();

        return Encoder.encodeToBase64(signature);
    }

    public String sign(String message) throws Exception {
        return sign(message.getBytes(StandardCharsets.UTF_8));
    }

    public Boolean verify(String message, String signature) throws Exception {
        CryptoProvider.setupIfNeeded();

        Signature verifier = Signature.getInstance(SIGN_ALGO);
        verifier.initVerify(this.privateKeyPair.getPublic());
        byte[] msgBytes = message.getBytes(StandardCharsets.UTF_8);
        verifier.update(msgBytes);
        byte[] sigBytes = Encoder.decodeFromBase64(signature);

        return verifier.verify(sigBytes);
    }

    // Return a pre-set seed in response to nextBytes or generateSeed
    public static class FixedSecureRandom extends SecureRandom {
        private final byte[] fixedValue;
        private int index = 0;

        public FixedSecureRandom(byte[] fixedValue) {
            this.fixedValue = Arrays.copyOf(fixedValue, fixedValue.length);
        }

        @Override
        public void nextBytes(byte[] bytes) {
            if (this.index >= this.fixedValue.length) {
                // no more data to copy
                return;
            }
            int len = bytes.length;
            if (len > this.fixedValue.length - this.index) {
                len = this.fixedValue.length - this.index;
            }
            System.arraycopy(this.fixedValue, this.index, bytes, 0, len);
            this.index += bytes.length;
        }

        @Override
        public byte[] generateSeed(int numBytes) {
            byte[] bytes = new byte[numBytes];
            this.nextBytes(bytes);
            return bytes;
        }
    }
}
