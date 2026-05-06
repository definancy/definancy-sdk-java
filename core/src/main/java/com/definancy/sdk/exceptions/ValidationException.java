package com.definancy.sdk.exceptions;

import com.definancy.sdk.DefinancyApiException;
import com.definancy.sdk.internal.ErrorBody;

import java.util.List;

/**
 * Validation failure (HTTP 400) — request shape was rejected by the
 * server (missing required field, invalid format, etc.).
 */
public class ValidationException extends DefinancyApiException {
    private static final long serialVersionUID = 1L;

    public ValidationException(int status, List<ErrorBody> errors, String requestId) {
        super(status, errors, requestId);
    }

    public ValidationException(int status, List<ErrorBody> errors, String requestId, Throwable cause) {
        super(status, errors, requestId, cause);
    }
}
