package com.definancy.sdk;

import com.definancy.ApiClient;
import com.definancy.sdk.auth.AuthProvider;
import com.definancy.sdk.auth.AuthRequestFilter;

import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClientBuilder;

/**
 * Convenience builder for the Definancy {@link ApiClient}, wiring the
 * Apache HttpClient connector and the DPoP-style {@link AuthRequestFilter}
 * with sensible defaults.
 *
 * <p>Why a helper class:
 * <ul>
 *   <li>Jersey's default connector ({@link java.net.HttpURLConnection})
 *       rejects {@code PATCH} — every {@code config*} endpoint of this API
 *       uses {@code PATCH}, so consumers always need
 *       {@link ApacheConnectorProvider}. Bundling that here removes a
 *       footgun.</li>
 *   <li>Centralises the auth-filter wiring so partner code doesn't
 *       reconstruct the same boilerplate per project.</li>
 * </ul>
 *
 * <p>Custom Jersey configuration can be applied with {@link #httpClient}.
 * For unit tests against the {@code stub} environment, pass
 * {@code authProvider = null} to skip auth-header signing entirely.
 *
 * <p>This is a thin layer-2 utility, not the eventual layer-3 public-surface
 * facade. It does not (yet) expose resource-grouped accessors
 * ({@code definancy.vault().getVault(id)}) — instantiate the per-tag
 * {@code *Api} classes directly off the returned {@link ApiClient}.
 */
public final class Client {
    private Client() { /* no instances */ }

    /**
     * Build a fully-configured {@link ApiClient} pointing at {@code audience},
     * signing each outbound request with {@code authProvider}.
     *
     * @param audience      the API base URL (e.g. {@code https://stub.definancy.com})
     * @param authProvider  the request signer; {@code null} disables auth headers
     *                      (only useful against the stub environment)
     */
    public static ApiClient create(String audience, AuthProvider authProvider) {
        return create(audience, authProvider, null);
    }

    /**
     * Build an {@link ApiClient} with a caller-supplied Jersey {@link Client}
     * (already configured by the caller — connector, filters, timeouts, etc.).
     * The Apache connector and DPoP filter are <strong>not</strong> applied
     * to the supplied client; full responsibility for transport configuration
     * rests with the caller.
     */
    public static ApiClient create(String audience, javax.ws.rs.client.Client jerseyClient) {
        if (audience == null || audience.isEmpty()) {
            throw new IllegalArgumentException("audience is required");
        }
        if (jerseyClient == null) {
            throw new IllegalArgumentException("jerseyClient is required (or use create(audience, AuthProvider) instead)");
        }
        ApiClient apiClient = new ApiClient();
        apiClient.setHttpClient(jerseyClient);
        apiClient.setBasePath(audience);
        return apiClient;
    }

    /**
     * Build an {@link ApiClient} with the default Apache connector + DPoP
     * filter, plus an opt-in extension hook to register additional Jersey
     * features (logging, metrics, custom interceptors).
     */
    public static ApiClient create(String audience, AuthProvider authProvider, Object extraFeature) {
        if (audience == null || audience.isEmpty()) {
            throw new IllegalArgumentException("audience is required");
        }

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.connectorProvider(new ApacheConnectorProvider());

        ClientBuilder builder = JerseyClientBuilder.newBuilder()
                .withConfig(clientConfig);

        if (authProvider != null) {
            try {
                builder.register(new AuthRequestFilter(authProvider));
            } catch (Exception e) {
                throw new IllegalStateException("could not install auth filter", e);
            }
        }

        if (extraFeature != null) {
            builder.register(extraFeature);
        }

        javax.ws.rs.client.Client jerseyClient = builder.build();
        ApiClient apiClient = new ApiClient();
        apiClient.setHttpClient(jerseyClient);
        apiClient.setBasePath(audience);
        return apiClient;
    }
}
