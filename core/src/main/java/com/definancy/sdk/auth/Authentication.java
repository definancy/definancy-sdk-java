package com.definancy.sdk.auth;

public class Authentication {
    final private Authorization authorization;
    final private DPoP dpop;

    public Authentication(Authorization authorization, DPoP dpop) throws Exception {
        this.authorization = authorization;
        this.dpop = dpop;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public DPoP getDPoP() {
        return dpop;
    }
}
