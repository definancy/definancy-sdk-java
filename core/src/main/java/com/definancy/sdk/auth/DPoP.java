package com.definancy.sdk.auth;

import com.definancy.sdk.util.Digester;
import com.definancy.sdk.util.Encoder;

/**
 * DPoP proof JWT (RFC 9449, customized).
 *
 * {@code id} (jti), {@code iat}, and {@code exp} are explicit
 * parameters (no implicit {@link java.time.Instant#now()} or
 * {@link java.util.UUID#randomUUID()}). The factory stays a pure data
 * transformation; impurities (clock, randomness) live at the call
 * boundary.
 *
 * <p>The {@code body} parameter is the actual wire bytes of the request
 * body (or {@code null} when there is no body). Hashing the wire bytes
 * is what makes the {@code bsh} claim a valid integrity check; an
 * earlier version of this constructor took {@code String} and the
 * caller passed {@code entity.toString()} of a JAX-RS entity, which
 * for any non-{@code String} POJO yielded the JVM identity string
 * (`Class@hex`) — silently bypassing real body integrity. Callers
 * (e.g. {@link com.definancy.sdk.auth.impl.LocalAuthProvider}) are now
 * responsible for serializing the entity to its wire form before
 * passing it in.
 *
 * <p>An empty body ({@code body.length == 0}) is treated identically
 * to {@code null} — the {@code bsh} claim is omitted. This matches
 * the TypeScript SDK's behaviour and the practical interpretation of
 * "the request has no body to hash" in HTTP.
 */
public class DPoP extends Jwt {
    public DPoP(String id, String method, String uri, byte[] body, Jwk jwk, long iat, long exp) {
        super();
        String bodyHash = null;
        if (body != null && body.length > 0) {
            byte[] bodyDigest = Digester.digest(body);
            bodyHash = Encoder.encodeToBase64(bodyDigest);
        }

        DPoPHeader header = new DPoPHeader();
        header.jwk = jwk;

        DPoPClaims claims = new DPoPClaims();
        claims.id = id;
        claims.method = method;
        claims.uri = uri;
        claims.issuedAt = iat;
        claims.expiresAt = exp;
        claims.bodyHash = bodyHash;

        this.setHeader(header);
        this.setClaims(claims);
    }
}
