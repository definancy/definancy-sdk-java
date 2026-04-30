package com.definancy.sdk.auth;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AuthRequestFilter implements ClientRequestFilter {
    private final AuthProvider provider;

    public AuthRequestFilter(AuthProvider provider) throws Exception {
        this.provider = provider;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        if (this.provider == null) {
            return;
        }

        String authorizationToken;
        String dpopToken;
        try {
            Authentication authentication = this.provider.authenticate(requestContext);
            authorizationToken = authentication.getAuthorization().encodeB64();
            dpopToken = authentication.getDPoP().encodeB64();
        } catch (Exception e) {
            throw new IOException(e);
        }

        // Build new request with both headers
        requestContext.getHeaders().add("Authorization", "DPoP " + authorizationToken);
        requestContext.getHeaders().add("DPoP", dpopToken);
    }
}
