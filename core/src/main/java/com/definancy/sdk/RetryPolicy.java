package com.definancy.sdk;

import java.time.Duration;
import java.util.Objects;

/**
 * Retry policy applied to transient failures (HTTP 5xx and 429). Skipped
 * on 2xx, on 4xx other than 429, and on cancellation.
 *
 * <p>Use {@link #exponentialBackoff(int, Duration, Duration)} for the
 * common case; {@link #DEFAULT} is also available for sensible defaults
 * (3 attempts, 200 ms base delay, 5 s cap, 20% jitter).
 */
public final class RetryPolicy {
    private final int maxAttempts;
    private final Duration baseDelay;
    private final Duration maxDelay;
    private final double jitter;

    private RetryPolicy(int maxAttempts, Duration baseDelay, Duration maxDelay, double jitter) {
        if (maxAttempts < 1) {
            throw new IllegalArgumentException("maxAttempts must be >= 1");
        }
        if (jitter < 0 || jitter > 1) {
            throw new IllegalArgumentException("jitter must be in [0, 1]");
        }
        this.maxAttempts = maxAttempts;
        this.baseDelay = Objects.requireNonNull(baseDelay, "baseDelay");
        this.maxDelay = Objects.requireNonNull(maxDelay, "maxDelay");
        this.jitter = jitter;
    }

    /** Maximum attempts including the initial request. */
    public int maxAttempts() {
        return maxAttempts;
    }

    /** Initial backoff delay before the first retry. */
    public Duration baseDelay() {
        return baseDelay;
    }

    /** Cap on the exponential backoff delay. */
    public Duration maxDelay() {
        return maxDelay;
    }

    /** Jitter factor in [0, 1]. */
    public double jitter() {
        return jitter;
    }

    /**
     * Exponential backoff with the given parameters. Jitter defaults to
     * 20% — actual delay is sampled uniformly from
     * {@code [delay * 0.8, delay * 1.2]}.
     */
    public static RetryPolicy exponentialBackoff(int maxAttempts, Duration baseDelay, Duration maxDelay) {
        return new RetryPolicy(maxAttempts, baseDelay, maxDelay, 0.2);
    }

    /**
     * Exponential backoff with explicit jitter (0 disables jitter; 1
     * means delay can range from 0 to 2× the computed value).
     */
    public static RetryPolicy exponentialBackoff(
            int maxAttempts, Duration baseDelay, Duration maxDelay, double jitter) {
        return new RetryPolicy(maxAttempts, baseDelay, maxDelay, jitter);
    }

    /** Disable retries entirely (1 attempt, no backoff). */
    public static RetryPolicy disabled() {
        return new RetryPolicy(1, Duration.ZERO, Duration.ZERO, 0);
    }

    /** Sensible defaults: 3 attempts, 200 ms → 5 s with 20% jitter. */
    public static final RetryPolicy DEFAULT = exponentialBackoff(
            3, Duration.ofMillis(200), Duration.ofSeconds(5));

    /**
     * Compute the backoff delay for the given attempt index (0-based).
     * If {@code retryAfterSeconds} is non-null and positive, it
     * supersedes the computed value (capped at {@link #maxDelay()}).
     */
    public Duration computeBackoff(int attempt, Long retryAfterSeconds) {
        if (retryAfterSeconds != null && retryAfterSeconds > 0) {
            Duration suggested = Duration.ofSeconds(retryAfterSeconds);
            return suggested.compareTo(maxDelay) > 0 ? maxDelay : suggested;
        }
        long base = baseDelay.toMillis() * (1L << Math.min(attempt, 30));
        long capped = Math.min(base, maxDelay.toMillis());
        if (jitter <= 0) {
            return Duration.ofMillis(capped);
        }
        double low = capped * (1.0 - jitter);
        double high = capped * (1.0 + jitter);
        long jittered = (long) (low + Math.random() * (high - low));
        return Duration.ofMillis(jittered);
    }
}
