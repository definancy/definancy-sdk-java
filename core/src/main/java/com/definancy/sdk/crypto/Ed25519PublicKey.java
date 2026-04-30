package com.definancy.sdk.crypto;

import com.definancy.sdk.DID;
import com.definancy.sdk.ID;
import com.definancy.sdk.auth.Jwk;
import com.definancy.sdk.util.Encoder;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Ed25519PublicKey implements Serializable {
	public static final int KEY_LEN_BYTES  = 32;

	private static final int PK_X509_PREFIX_LENGTH = 12; // Ed25519 specific

	private final byte[] raw = new byte[KEY_LEN_BYTES];

	@JsonIgnore
	public Ed25519PublicKey(PublicKey publicKey) {
		/*
			Java uses DER-encoded ASN.1 format for signatures.
			Definancy uses raw 64-byte signatures (R||S), as defined by the RFC 8032.
			This class exists to support the conversion.
		*/

		byte[] x509 = publicKey.getEncoded(); // X.509 prepended with ASN.1 prefix
		if (x509.length != KEY_LEN_BYTES + PK_X509_PREFIX_LENGTH) {
			throw new RuntimeException("Generated public key and X.509 prefix is the wrong size");
		}

		System.arraycopy(x509, PK_X509_PREFIX_LENGTH, this.raw, 0, KEY_LEN_BYTES);
	}

	@JsonCreator
	public Ed25519PublicKey(byte[] raw) {
		if (raw == null) {
			return;
		}

		if (raw.length != KEY_LEN_BYTES) {
			throw new IllegalArgumentException("ed25519 public key wrong length");
		}

		System.arraycopy(raw, 0, this.raw, 0, KEY_LEN_BYTES);
	}

	// default values for serializer to ignore
	public Ed25519PublicKey() {
	}

    public DID computeDID(String network) throws Exception {
        ID id = new ID(this);
        return new DID(network, id);
    }

	@JsonIgnore
	public Jwk jwk() {
		String x = Encoder.encodeToBase64(this.raw);
		return new Jwk("OKP", "Ed25519", x);
	}

	@JsonValue
	public byte[] getBytes() {
		return this.raw;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Ed25519PublicKey && Arrays.equals(this.raw, ((Ed25519PublicKey) obj).raw);
	}
}
