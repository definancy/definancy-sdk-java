package com.definancy.sdk;

import java.util.List;
import java.util.Map;

/**
 * Parsed rate-limit headers from a daemon response.
 *
 * <p>The Definancy API emits {@code x-ratelimit-limit},
 * {@code x-ratelimit-remaining}, and {@code x-ratelimit-reset} on every
 * authenticated response. The SDK captures these and exposes them via
 * {@link DefinancyClient#lastRateLimit()}. Useful for partner code that
 * wants to pace itself before hitting the cap, or surface remaining
 * budget in UIs.
 */
public final class RateLimitInfo {
    private final long limit;
    private final long remaining;
    private final long resetSeconds;

    public RateLimitInfo(long limit, long remaining, long resetSeconds) {
        this.limit = limit;
        this.remaining = remaining;
        this.resetSeconds = resetSeconds;
    }

    /** Maximum requests allowed in the current window. */
    public long limit() {
        return limit;
    }

    /** Requests remaining in the current window. */
    public long remaining() {
        return remaining;
    }

    /** Seconds until the current window resets. */
    public long resetSeconds() {
        return resetSeconds;
    }

    /**
     * Parse the {@code x-ratelimit-*} headers from a Jersey-style header
     * map. Returns {@code null} if any required header is missing or
     * unparseable — callers should treat that as "no rate-limit info
     * available for this response."
     */
    public static RateLimitInfo parse(Map<String, List<String>> headers) {
        if (headers == null) return null;
        Long limit = parseFirstLong(headers, "x-ratelimit-limit");
        Long remaining = parseFirstLong(headers, "x-ratelimit-remaining");
        Long reset = parseFirstLong(headers, "x-ratelimit-reset");
        if (limit == null || remaining == null || reset == null) return null;
        return new RateLimitInfo(limit, remaining, reset);
    }

    private static Long parseFirstLong(Map<String, List<String>> headers, String name) {
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(name)) continue;
            List<String> values = entry.getValue();
            if (values == null || values.isEmpty()) return null;
            try {
                return Long.parseLong(values.get(0));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "RateLimitInfo{limit=" + limit + ", remaining=" + remaining
                + ", resetSeconds=" + resetSeconds + '}';
    }
}
