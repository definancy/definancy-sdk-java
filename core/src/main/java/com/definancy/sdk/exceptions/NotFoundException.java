package com.definancy.sdk.exceptions;

import com.definancy.sdk.DefinancyApiException;
import com.definancy.sdk.internal.ErrorBody;

import java.util.List;

/** Resource not found (HTTP 404). */
public class NotFoundException extends DefinancyApiException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(int status, List<ErrorBody> errors, String requestId) {
        super(status, errors, requestId);
    }

    public NotFoundException(int status, List<ErrorBody> errors, String requestId, Throwable cause) {
        super(status, errors, requestId, cause);
    }
}
