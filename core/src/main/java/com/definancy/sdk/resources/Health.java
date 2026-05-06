package com.definancy.sdk.resources;

import com.definancy.api.ProbeApi;
import com.definancy.model.Status;
import com.definancy.sdk.internal.Invoker;

/** Health probes — service liveness and readiness. Both unauthenticated. */
public final class Health {
    private final ProbeApi api;
    private final Invoker invoker;

    public Health(ProbeApi api, Invoker invoker) {
        this.api = api;
        this.invoker = invoker;
    }

    /** Liveness check. Returns OK when the daemon is operational. */
    public Status healthy() {
        return invoker.invoke(api::healthyCheck);
    }

    /** Readiness check. Returns OK when the daemon is ready for traffic. */
    public Status ready() {
        return invoker.invoke(api::readyCheck);
    }
}
