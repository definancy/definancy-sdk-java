package com.definancy.sdk;

import com.definancy.sdk.crypto.Ed25519PublicKey;
import com.definancy.sdk.util.Digester;
import com.definancy.sdk.util.Encoder;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class ID {
	/**
	 * The length of an ID. Equal to the size of a SHA256 checksum.
	 */
	public static final int LEN_BYTES = 32;

	// the underlying bytes
	private final byte[] bytes = new byte[LEN_BYTES];
	// the length of checksum to append
	private static final int CHECKSUM_LEN_BYTES = 4;
	// expected length of base32-encoded checksum-appended IDs
	private static final int EXPECTED_STR_ENCODED_LEN = 58;
	// signature algorithm for verifying signature

	public ID(final Ed25519PublicKey publicKey) {
		byte[] bytes = publicKey.getBytes();
		System.arraycopy(bytes, 0, this.bytes, 0, LEN_BYTES);
	}

	public ID(final byte[] idBytes) {
		if (idBytes == null) {
			return;
		}

		if (idBytes.length != LEN_BYTES) {
			throw new IllegalArgumentException(String.format("Given ID length is not %s", LEN_BYTES));
		}

		System.arraycopy(idBytes, 0, this.bytes, 0, LEN_BYTES);
	}

	public ID(final String idStr) {
		if (idStr == null) {
			return;
		}

		if (idStr.length() != EXPECTED_STR_ENCODED_LEN) {
			throw new IllegalArgumentException(String.format("Given ID length is not %s", EXPECTED_STR_ENCODED_LEN));
		}

		byte[] idBytes = Encoder.decodeFromBase32(idStr);
		if (idBytes.length != LEN_BYTES+CHECKSUM_LEN_BYTES) {
			throw new IllegalArgumentException(String.format("Given ID length is not %s", LEN_BYTES+CHECKSUM_LEN_BYTES));
		}

		final byte[] checksum = Arrays.copyOfRange(idBytes, LEN_BYTES, LEN_BYTES + CHECKSUM_LEN_BYTES);

		final byte[] hashedId = Digester.digest(Arrays.copyOf(idBytes, LEN_BYTES));
		final byte[] expectedChecksum = Arrays.copyOfRange(hashedId, LEN_BYTES - CHECKSUM_LEN_BYTES, hashedId.length);

		if (Arrays.equals(checksum, expectedChecksum)) {
			System.arraycopy(idBytes, 0, this.bytes, 0, LEN_BYTES);
		} else {
			throw new IllegalArgumentException("Input checksum did not validate");
		}
	}

	public byte[] toBytes() {
		return Arrays.copyOf(this.bytes, this.bytes.length);
	}

	/**
	 * encodeAsString converts the ID to a human-readable representation, with
	 * a 4-byte checksum appended at the end, using SHA256.
	 */
	public String encodeAsString() throws NoSuchAlgorithmException {
		// compute sha512/256 checksum
		final byte[] hashedId = Digester.digest(Arrays.copyOf(this.bytes, LEN_BYTES));

		// take the last 4 bytes, and append to addr
		final byte[] checksum = Arrays.copyOfRange(hashedId, LEN_BYTES - CHECKSUM_LEN_BYTES, hashedId.length);
		byte[] checksumAddr = Arrays.copyOf(this.bytes, this.bytes.length + CHECKSUM_LEN_BYTES);
		System.arraycopy(checksum, 0, checksumAddr, this.bytes.length, CHECKSUM_LEN_BYTES);

		// encodeToMsgPack addr+checksum as base32 and return.
		String res = Encoder.encodeToBase32(checksumAddr);
		if (res.length() != EXPECTED_STR_ENCODED_LEN) {
			throw new RuntimeException("unexpected address length " + res.length());
		}

		return res;
	}

	public Ed25519PublicKey toPublicKey() {
		return new Ed25519PublicKey(this.bytes);
	}

	@Override
	public String toString() {
		try {
			return this.encodeAsString();
		} catch (NoSuchAlgorithmException e) {
			// encoding should always succeed when provider properly set up
			throw new RuntimeException(e);
		}
	}
}
