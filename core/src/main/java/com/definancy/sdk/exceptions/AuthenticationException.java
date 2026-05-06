package com.definancy.sdk.exceptions;

import com.definancy.sdk.DefinancyApiException;
import com.definancy.sdk.internal.ErrorBody;

import java.util.List;

/**
 * Authentication failure (HTTP 401). Common causes: token expired, DID
 * not registered for the environment, JKT-binding mismatch.
 */
public class AuthenticationException extends DefinancyApiException {
    private static final long serialVersionUID = 1L;

    public AuthenticationException(int status, List<ErrorBody> errors, String requestId) {
        super(status, errors, requestId);
    }

    public AuthenticationException(int status, List<ErrorBody> errors, String requestId, Throwable cause) {
        super(status, errors, requestId, cause);
    }
}
