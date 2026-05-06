package com.definancy.sdk.exceptions;

import com.definancy.sdk.DefinancyApiException;
import com.definancy.sdk.internal.ErrorBody;

import java.time.Duration;
import java.util.List;

/**
 * Rate limit exceeded (HTTP 429). {@link #retryAfter()} reflects the
 * {@code Retry-After} header when the server set one; {@code null}
 * otherwise. The client's retry filter already honours this header —
 * this subclass is exposed for partner code that wants to surface the
 * wait time in UI or logs.
 */
public class RateLimitException extends DefinancyApiException {
    private static final long serialVersionUID = 1L;

    private final Duration retryAfter;

    public RateLimitException(
            int status, List<ErrorBody> errors, String requestId, Duration retryAfter) {
        super(status, errors, requestId);
        this.retryAfter = retryAfter;
    }

    public RateLimitException(
            int status, List<ErrorBody> errors, String requestId, Duration retryAfter, Throwable cause) {
        super(status, errors, requestId, cause);
        this.retryAfter = retryAfter;
    }

    /** Server-suggested wait before retry. May be {@code null}. */
    public Duration retryAfter() {
        return retryAfter;
    }
}
