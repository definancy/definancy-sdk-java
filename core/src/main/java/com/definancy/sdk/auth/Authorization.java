package com.definancy.sdk.auth;

import com.definancy.sdk.DID;

/**
 * Authorization JWT — identifies the caller via DID and binds to a DPoP key.
 *
 * {@code iat} and {@code exp} are explicit parameters (no implicit
 * {@link java.time.Instant#now()}). The JWT factory stays a pure data
 * transformation; clock impurity lives at the call boundary (e.g.
 * {@link com.definancy.sdk.auth.impl.LocalAuthProvider#authenticate}).
 * This shape lets conformance vectors pin specific timestamps for
 * byte-exact cross-language comparison.
 */
public class Authorization extends Jwt {
    public Authorization(DID did, String audience, String thumbprint, long iat, long exp) {
        super();

        AuthorizationHeader header = new AuthorizationHeader();

        AuthorizationClaims claims = new AuthorizationClaims();
        claims.issuer = did.toString();
        claims.subject = did.toString();
        claims.audience = audience;
        claims.issuedAt = iat;
        claims.expiresAt = exp;
        claims.confirmation = new AuthorizationClaims.ConfirmationClaims();
        claims.confirmation.thumbprint = thumbprint;

        this.setHeader(header);
        this.setClaims(claims);
    }
}
