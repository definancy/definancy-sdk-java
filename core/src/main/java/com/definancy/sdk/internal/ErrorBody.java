package com.definancy.sdk.internal;

import java.util.Objects;

/**
 * One element of the {@code ErrorList} response body emitted by the
 * Definancy API on non-2xx responses (per {@code components/schemas/Error}).
 *
 * <p>Internal package because it's a parsed-from-JSON value type; the
 * public surface accesses it via {@link com.definancy.sdk.DefinancyApiException#errors()}.
 */
public final class ErrorBody {
    private final String code;
    private final String message;

    public ErrorBody(String code, String message) {
        this.code = Objects.requireNonNull(code, "code");
        this.message = Objects.requireNonNull(message, "message");
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorBody)) return false;
        ErrorBody that = (ErrorBody) o;
        return code.equals(that.code) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }

    @Override
    public String toString() {
        return code + ": " + message;
    }
}
