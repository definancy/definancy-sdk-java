package com.definancy.sdk.internal;

import com.definancy.ApiClient;
import com.definancy.sdk.auth.AuthProvider;
import com.definancy.sdk.auth.AuthRequestFilter;

import java.time.Duration;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClientBuilder;

/**
 * Internal factory that builds the Jersey {@link Client} +
 * openapi-generator {@link ApiClient} with the SDK's defaults wired up:
 * Apache HttpClient connector (so {@code PATCH} works), DPoP auth
 * filter, response-tracker filter, plus any caller-supplied extra
 * filters.
 *
 * <p>Package-internal — partners construct the public
 * {@link com.definancy.sdk.DefinancyClient} via its builder; this class
 * absorbs the wiring previously exposed as the now-deleted
 * {@code com.definancy.sdk.Client} helper.
 */
public final class ApiClientFactory {
    private ApiClientFactory() {}

    public static ApiClient create(
            String audience,
            AuthProvider authProvider,
            ResponseTracker tracker,
            Duration connectTimeout,
            Duration readTimeout,
            List<Object> extraFilters) {

        if (audience == null || audience.isEmpty()) {
            throw new IllegalArgumentException("audience is required");
        }

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.connectorProvider(new ApacheConnectorProvider());
        if (connectTimeout != null) {
            clientConfig.property(
                    ClientProperties.CONNECT_TIMEOUT,
                    (int) Math.min(connectTimeout.toMillis(), Integer.MAX_VALUE));
        }
        if (readTimeout != null) {
            clientConfig.property(
                    ClientProperties.READ_TIMEOUT,
                    (int) Math.min(readTimeout.toMillis(), Integer.MAX_VALUE));
        }

        ClientBuilder builder = JerseyClientBuilder.newBuilder().withConfig(clientConfig);

        if (authProvider != null) {
            try {
                builder.register(new AuthRequestFilter(authProvider));
            } catch (Exception e) {
                throw new IllegalStateException("could not install DPoP auth filter", e);
            }
        }

        if (tracker != null) {
            builder.register(new TrackingFilter(tracker));
        }

        if (extraFilters != null) {
            for (Object f : extraFilters) {
                if (f != null) builder.register(f);
            }
        }

        Client httpClient = builder.build();
        ApiClient apiClient = new ApiClient();
        apiClient.setHttpClient(httpClient);
        apiClient.setBasePath(audience);
        return apiClient;
    }
}
