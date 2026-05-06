package com.definancy.sdk;

import com.definancy.ApiException;
import com.definancy.sdk.internal.ErrorBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Typed runtime exception thrown by {@link DefinancyClient} facade
 * methods on non-2xx responses. Wraps the openapi-generator-emitted
 * {@link ApiException} with a partner-friendly surface:
 *
 * <ul>
 *   <li>{@link #status()} — HTTP status code.</li>
 *   <li>{@link #errors()} — parsed {@code ErrorList} body
 *       ({@code [{code, message}, ...]}). Always non-null; empty if
 *       the response body wasn't a valid error envelope.</li>
 *   <li>{@link #code()} — convenience accessor for the first error
 *       code (or "UNKNOWN" if {@link #errors()} is empty).</li>
 *   <li>{@link #requestId()} — server-issued {@code X-Request-Id}
 *       header. Always set when the daemon answered; may be
 *       {@code null} only if the request never reached the daemon
 *       (DNS failure, network unreachable).</li>
 * </ul>
 *
 * Discriminated subclasses ({@code NotFoundException},
 * {@code RateLimitException}, etc. under {@code com.definancy.sdk.exceptions})
 * cover the common HTTP status families.
 */
public class DefinancyApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int status;
    private final List<ErrorBody> errors;
    private final String requestId;

    public DefinancyApiException(int status, List<ErrorBody> errors, String requestId) {
        this(status, errors, requestId, /* cause */ null);
    }

    public DefinancyApiException(
            int status, List<ErrorBody> errors, String requestId, Throwable cause) {
        super(buildMessage(status, errors), cause);
        this.status = status;
        this.errors = errors == null
                ? Collections.<ErrorBody>emptyList()
                : Collections.unmodifiableList(errors);
        this.requestId = requestId;
    }

    public int status() {
        return status;
    }

    public List<ErrorBody> errors() {
        return errors;
    }

    public String code() {
        return errors.isEmpty() ? "UNKNOWN" : errors.get(0).code();
    }

    public String requestId() {
        return requestId;
    }

    public boolean hasCode(String code) {
        for (ErrorBody e : errors) {
            if (e.code().equals(code)) return true;
        }
        return false;
    }

    public boolean isUnauthorized() {
        return status == 401;
    }

    public boolean isForbidden() {
        return status == 403;
    }

    public boolean isNotFound() {
        return status == 404;
    }

    public boolean isConflict() {
        return status == 409;
    }

    public boolean isValidation() {
        return status == 400;
    }

    /**
     * Extract the first {@code X-Request-Id} value from a Jersey-style
     * response-headers map. Returns {@code null} if absent.
     */
    public static String extractRequestId(Map<String, List<String>> headers) {
        if (headers == null) return null;
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase("x-request-id")) continue;
            List<String> values = entry.getValue();
            if (values != null && !values.isEmpty()) return values.get(0);
        }
        return null;
    }

    private static String buildMessage(int status, List<ErrorBody> errors) {
        if (errors == null || errors.isEmpty()) {
            return "HTTP " + status;
        }
        ErrorBody first = errors.get(0);
        return first.code() + ": " + first.message();
    }
}
