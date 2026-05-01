package com.definancy.sdk.crypto;

import com.definancy.sdk.util.Encoder;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Java-specific contract tests for {@link KeyPair}.
 *
 * <p>Cross-language byte-identity for {@code generateKeyPairFromSecret}
 * and {@code sign} is asserted by the conformance Runner against
 * {@code ed25519/*.yaml} vectors. These tests pin Java-side concerns:
 * non-null outputs, deterministic seed expansion within Java,
 * malformed/null seed rejection, the signature length contract, and
 * default identity-based equality semantics.
 */
public final class KeyPairTest {

    /** Ed25519 raw signature is exactly 64 bytes (R||S, RFC 8032). */
    private static final int ED25519_SIG_LEN = 64;

    /** Ed25519 raw public key is exactly 32 bytes. */
    private static final int ED25519_PK_LEN = 32;

    private static final byte[] SEED_32 = new byte[]{
            (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03,
            (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07,
            (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b,
            (byte) 0x0c, (byte) 0x0d, (byte) 0x0e, (byte) 0x0f,
            (byte) 0x10, (byte) 0x11, (byte) 0x12, (byte) 0x13,
            (byte) 0x14, (byte) 0x15, (byte) 0x16, (byte) 0x17,
            (byte) 0x18, (byte) 0x19, (byte) 0x1a, (byte) 0x1b,
            (byte) 0x1c, (byte) 0x1d, (byte) 0x1e, (byte) 0x1f
    };

    // -------------------------------------------------------------------------
    // generateKeyPair() — random source
    // -------------------------------------------------------------------------

    @Test
    public void generateKeyPair_producesNonNullPublicKey() throws Exception {
        KeyPair kp = KeyPair.generateKeyPair();
        assertNotNull(kp);
        Ed25519PublicKey pk = kp.publicKey();
        assertNotNull(pk);
        byte[] raw = pk.getBytes();
        assertNotNull(raw);
        assertEquals(ED25519_PK_LEN, raw.length,
                "Ed25519 public key must be 32 bytes");
    }

    @Test
    public void generateKeyPair_distinctRunsProduceDistinctKeys() throws Exception {
        // Sanity check that random generation is actually random — two
        // back-to-back calls produce different public keys with
        // overwhelming probability.
        KeyPair a = KeyPair.generateKeyPair();
        KeyPair b = KeyPair.generateKeyPair();
        assertTrue(!Arrays.equals(a.publicKey().getBytes(), b.publicKey().getBytes()),
                "two random KeyPairs must produce distinct public keys");
    }

    // -------------------------------------------------------------------------
    // generateKeyPairFromSecret(String) — deterministic
    // -------------------------------------------------------------------------

    @Test
    public void generateKeyPairFromSecret_sameSeedProducesSameKey() throws Exception {
        String secretB64 = Encoder.encodeToBase64(SEED_32);
        KeyPair a = KeyPair.generateKeyPairFromSecret(secretB64);
        KeyPair b = KeyPair.generateKeyPairFromSecret(secretB64);
        assertArrayEquals(a.publicKey().getBytes(), b.publicKey().getBytes(),
                "deterministic seed expansion: same seed → same public key");
    }

    @Test
    public void generateKeyPairFromSecret_differentSeedsProduceDifferentKeys() throws Exception {
        byte[] otherSeed = new byte[32];
        Arrays.fill(otherSeed, (byte) 0xff);
        KeyPair a = KeyPair.generateKeyPairFromSecret(Encoder.encodeToBase64(SEED_32));
        KeyPair b = KeyPair.generateKeyPairFromSecret(Encoder.encodeToBase64(otherSeed));
        assertTrue(!Arrays.equals(a.publicKey().getBytes(), b.publicKey().getBytes()),
                "different seeds must produce different public keys");
    }

    // -------------------------------------------------------------------------
    // generateKeyPairFromSecret(String) — null / malformed input
    // -------------------------------------------------------------------------

    @Test
    public void generateKeyPairFromSecret_nullSecret_throwsNPE() {
        // Implementation has Objects.requireNonNull(secret, ...) — the
        // declared NullPointerException is the contract.
        assertThrows(NullPointerException.class,
                () -> KeyPair.generateKeyPairFromSecret(null));
    }

    @Test
    public void generateKeyPairFromSecret_wrongSeedLength_throwsIAE() throws Exception {
        // 31 bytes — one short of Ed25519's 32-byte seed.
        byte[] tooShort = new byte[31];
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> KeyPair.generateKeyPairFromSecret(Encoder.encodeToBase64(tooShort)));
        assertTrue(ex.getMessage().contains("32"),
                "rejection message should mention the required size");
    }

    @Test
    public void generateKeyPairFromSecret_oversizeSeed_throwsIAE() {
        // 64 bytes — twice the required size.
        byte[] tooLong = new byte[64];
        assertThrows(IllegalArgumentException.class,
                () -> KeyPair.generateKeyPairFromSecret(Encoder.encodeToBase64(tooLong)));
    }

    @Test
    public void generateKeyPairFromSecret_emptySeed_throwsIAE() {
        // Zero-length seed encoded as base64url is "".
        assertThrows(IllegalArgumentException.class,
                () -> KeyPair.generateKeyPairFromSecret(""));
    }

    // -------------------------------------------------------------------------
    // KeyPair(byte[]) constructor — null seed
    // -------------------------------------------------------------------------

    @Test
    public void byteSeedConstructor_nullSeed_throwsNPE() {
        // Goes through FixedSecureRandom(byte[]) which calls
        // Arrays.copyOf(null, ...) → NullPointerException.
        assertThrows(NullPointerException.class,
                () -> new KeyPair((byte[]) null));
    }

    // -------------------------------------------------------------------------
    // sign() — signature contract
    // -------------------------------------------------------------------------

    @Test
    public void sign_bytes_producesValidEd25519SignatureLength() throws Exception {
        KeyPair kp = KeyPair.generateKeyPairFromSecret(Encoder.encodeToBase64(SEED_32));
        byte[] message = "hello world".getBytes(java.nio.charset.StandardCharsets.UTF_8);
        String sigB64 = kp.sign(message);
        assertNotNull(sigB64);
        byte[] sigBytes = Encoder.decodeFromBase64(sigB64);
        assertEquals(ED25519_SIG_LEN, sigBytes.length,
                "Ed25519 signature must be exactly 64 bytes (R||S)");
    }

    @Test
    public void sign_string_delegatesToBytes() throws Exception {
        KeyPair kp = KeyPair.generateKeyPairFromSecret(Encoder.encodeToBase64(SEED_32));
        String sigFromString = kp.sign("hello");
        String sigFromBytes = kp.sign("hello".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        assertEquals(sigFromString, sigFromBytes,
                "sign(String) must produce identical output to sign(UTF-8 bytes)");
    }

    @Test
    public void sign_emptyBytes_producesValidSignature() throws Exception {
        // RFC 8032 explicitly permits signing the empty message.
        KeyPair kp = KeyPair.generateKeyPairFromSecret(Encoder.encodeToBase64(SEED_32));
        String sigB64 = kp.sign(new byte[0]);
        byte[] sigBytes = Encoder.decodeFromBase64(sigB64);
        assertEquals(ED25519_SIG_LEN, sigBytes.length,
                "empty-message signing produces standard 64-byte signature");
    }

    @Test
    public void verify_validSignature_returnsTrue() throws Exception {
        KeyPair kp = KeyPair.generateKeyPairFromSecret(Encoder.encodeToBase64(SEED_32));
        String message = "verifiable payload";
        String sig = kp.sign(message);
        assertTrue(kp.verify(message, sig),
                "freshly-signed message must verify against its signing keypair");
    }

    @Test
    public void verify_tamperedMessage_returnsFalse() throws Exception {
        KeyPair kp = KeyPair.generateKeyPairFromSecret(Encoder.encodeToBase64(SEED_32));
        String sig = kp.sign("original");
        assertTrue(!kp.verify("tampered", sig),
                "verification must fail when message differs from signed payload");
    }

    // -------------------------------------------------------------------------
    // Equality semantics — KeyPair does NOT override equals/hashCode.
    // Pin this so a future change to value-equality (e.g. comparing
    // private-key bytes) is a deliberate, visible decision rather than
    // an accidental semantic shift.
    // -------------------------------------------------------------------------

    @Test
    public void equals_isIdentityBased_notValueBased() throws Exception {
        String secret = Encoder.encodeToBase64(SEED_32);
        KeyPair a = KeyPair.generateKeyPairFromSecret(secret);
        KeyPair b = KeyPair.generateKeyPairFromSecret(secret);
        // Public keys are byte-identical (deterministic from same seed).
        assertArrayEquals(a.publicKey().getBytes(), b.publicKey().getBytes());
        // But the KeyPair objects themselves are NOT equal — no override.
        assertTrue(!a.equals(b),
                "KeyPair has no equals() override; identity-based comparison");
        assertEquals(a, a, "each instance equals itself");
    }
}
