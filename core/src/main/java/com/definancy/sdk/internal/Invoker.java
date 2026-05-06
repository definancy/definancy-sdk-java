package com.definancy.sdk.internal;

import com.definancy.ApiException;
import com.definancy.sdk.DefinancyApiException;
import com.definancy.sdk.RetryPolicy;
import com.definancy.sdk.exceptions.RateLimitException;
import com.definancy.sdk.exceptions.ServerException;

import java.time.Duration;
import java.util.Objects;

/**
 * Wraps a single SDK call (typically a {@code *Api} method invocation)
 * with retry + exception mapping. Each resource class delegates its
 * methods through {@link #invoke}; the public surface only ever sees
 * typed {@link DefinancyApiException} subclasses.
 */
public final class Invoker {
    private final RetryPolicy retry;

    public Invoker(RetryPolicy retry) {
        this.retry = Objects.requireNonNull(retry, "retry");
    }

    @FunctionalInterface
    public interface ThrowingSupplier<T> {
        T get() throws ApiException;
    }

    @FunctionalInterface
    public interface ThrowingRunnable {
        void run() throws ApiException;
    }

    /** Invoke a value-returning call with retry. */
    public <T> T invoke(ThrowingSupplier<T> call) {
        ApiException last = null;
        for (int attempt = 0; attempt < retry.maxAttempts(); attempt++) {
            try {
                return call.get();
            } catch (ApiException e) {
                last = e;
                DefinancyApiException mapped = ExceptionMapper.map(e);
                if (!isRetryable(mapped) || attempt + 1 >= retry.maxAttempts()) {
                    throw mapped;
                }
                Duration delay = retry.computeBackoff(
                        attempt, retryAfterSeconds(mapped));
                sleep(delay);
            }
        }
        // Loop body always returns or throws; this is a safety net.
        throw ExceptionMapper.map(last);
    }

    /** Invoke a void call with retry. */
    public void invokeVoid(ThrowingRunnable call) {
        invoke(() -> {
            call.run();
            return null;
        });
    }

    private static boolean isRetryable(DefinancyApiException e) {
        return e instanceof RateLimitException || e instanceof ServerException;
    }

    private static Long retryAfterSeconds(DefinancyApiException e) {
        if (e instanceof RateLimitException) {
            Duration d = ((RateLimitException) e).retryAfter();
            if (d != null) return d.getSeconds();
        }
        return null;
    }

    private static void sleep(Duration d) {
        if (d == null || d.isZero() || d.isNegative()) return;
        try {
            Thread.sleep(d.toMillis());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("interrupted during retry backoff", ex);
        }
    }
}
