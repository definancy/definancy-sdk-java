package com.definancy.sdk.auth;

import java.nio.charset.StandardCharsets;

/**
 * Abstraction over cryptographic signing operations. Implementations
 * must provide Ed25519 signing and JWK export. The default
 * implementation is {@link com.definancy.sdk.crypto.KeyPair}, but
 * custom implementations can be provided (e.g. HSM-backed signers,
 * remote signing services).
 *
 * <p>{@code sign(byte[])} is the primary method — it signs arbitrary
 * bytes. {@code sign(String)} is a convenience overload that
 * UTF-8-encodes the string and delegates to the bytes form. This
 * mirrors the TypeScript SDK's {@code Signer.sign(string | Uint8Array)}
 * union type and lets conformance vectors with non-UTF-8 message bytes
 * (e.g. RFC 8032 §7.1 Test 3's {@code 0xaf82}) be signed.
 */
public interface Signer {
    /** Sign raw message bytes and return the base64url-encoded signature. */
    String sign(byte[] data) throws Exception;

    /**
     * Sign a string message (UTF-8 encoded) and return the base64url-encoded
     * signature. Default implementation delegates to {@link #sign(byte[])}.
     */
    default String sign(String message) throws Exception {
        return sign(message.getBytes(StandardCharsets.UTF_8));
    }

    Jwk jwk() throws Exception;
}
