package com.definancy.sdk.resources;

import com.definancy.api.ExperimentalApi;
import com.definancy.model.Status;
import com.definancy.sdk.internal.Invoker;

/**
 * Experimental endpoints reserved for development-environment use.
 * Calls here may evolve faster than the stable surface; partners
 * should not build production flows against them.
 */
public final class Experimental {
    private final ExperimentalApi api;
    private final Invoker invoker;

    public Experimental(ExperimentalApi api, Invoker invoker) {
        this.api = api;
        this.invoker = invoker;
    }

    /** Connectivity probe for the Experimental tag surface. */
    public Status ping() {
        return invoker.invoke(api::experimentalPing);
    }
}
