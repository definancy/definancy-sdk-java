package com.definancy.sdk.auth;

import javax.ws.rs.client.ClientRequestContext;

public interface AuthProvider {
    Authentication authenticate(ClientRequestContext requestContext) throws Exception;
}
