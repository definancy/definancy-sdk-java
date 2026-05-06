package com.definancy.sdk.internal;

import com.definancy.sdk.RateLimitInfo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Thread-safe holder for the last response's tracked metadata —
 * {@code X-Request-Id} and parsed rate-limit headers.
 *
 * <p>Updated by {@link TrackingFilter} on every response (success or
 * failure); read by {@link com.definancy.sdk.DefinancyClient#lastRequestId()}
 * and {@link com.definancy.sdk.DefinancyClient#lastRateLimit()}.
 */
public final class ResponseTracker {
    private final AtomicReference<String> lastRequestId = new AtomicReference<>();
    private final AtomicReference<RateLimitInfo> lastRateLimit = new AtomicReference<>();

    public String lastRequestId() {
        return lastRequestId.get();
    }

    public RateLimitInfo lastRateLimit() {
        return lastRateLimit.get();
    }

    public void setLastRequestId(String id) {
        if (id != null) lastRequestId.set(id);
    }

    public void setLastRateLimit(RateLimitInfo info) {
        if (info != null) lastRateLimit.set(info);
    }
}
