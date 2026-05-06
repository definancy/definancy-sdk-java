package com.definancy.sdk.exceptions;

import com.definancy.sdk.DefinancyApiException;
import com.definancy.sdk.internal.ErrorBody;

import java.util.List;

/**
 * Server-side error (HTTP 5xx) — the daemon failed to handle the
 * request for an internal reason. The retry filter will have already
 * retried up to the configured maximum before this surfaces to partner
 * code.
 */
public class ServerException extends DefinancyApiException {
    private static final long serialVersionUID = 1L;

    public ServerException(int status, List<ErrorBody> errors, String requestId) {
        super(status, errors, requestId);
    }

    public ServerException(int status, List<ErrorBody> errors, String requestId, Throwable cause) {
        super(status, errors, requestId, cause);
    }
}
