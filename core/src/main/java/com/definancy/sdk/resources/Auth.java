package com.definancy.sdk.resources;

import com.definancy.api.AuthApi;
import com.definancy.sdk.internal.Invoker;

/**
 * Auth registration — register a Definancy ID with the daemon so its
 * subsequent DPoP-signed requests are recognized as authenticated. In
 * stub environments the daemon auto-registers on first authenticated
 * call; this is the explicit onboarding step.
 */
public final class Auth {
    private final AuthApi api;
    private final Invoker invoker;

    public Auth(AuthApi api, Invoker invoker) {
        this.api = api;
        this.invoker = invoker;
    }

    /** Register a Definancy ID. Idempotent. */
    public void register(String definancyId) {
        invoker.invokeVoid(() -> api.registerAuth(definancyId));
    }
}
