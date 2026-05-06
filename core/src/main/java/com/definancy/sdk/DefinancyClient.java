package com.definancy.sdk;

import com.definancy.ApiClient;
import com.definancy.api.AssetApi;
import com.definancy.api.AuthApi;
import com.definancy.api.ContractApi;
import com.definancy.api.ExperimentalApi;
import com.definancy.api.NetworkApi;
import com.definancy.api.ProbeApi;
import com.definancy.api.QrCodeApi;
import com.definancy.api.VaultApi;
import com.definancy.api.VaultContractSubscriptionApi;
import com.definancy.api.VaultDocumentApi;
import com.definancy.api.VaultPaymentApi;
import com.definancy.api.VelocityApi;
import com.definancy.sdk.internal.Invoker;
import com.definancy.sdk.internal.ResponseTracker;
import com.definancy.sdk.resources.Assets;
import com.definancy.sdk.resources.Auth;
import com.definancy.sdk.resources.Contracts;
import com.definancy.sdk.resources.Documents;
import com.definancy.sdk.resources.Experimental;
import com.definancy.sdk.resources.Health;
import com.definancy.sdk.resources.Networks;
import com.definancy.sdk.resources.PaymentAcceptances;
import com.definancy.sdk.resources.QrCodes;
import com.definancy.sdk.resources.Vaults;
import com.definancy.sdk.resources.VelocityLimits;

import java.util.Objects;

import javax.ws.rs.client.Client;

/**
 * Top-level Definancy SDK facade. Resource-grouped accessors expose the
 * full Definancy API as idiomatic Java method calls; the underlying
 * generated {@link ApiClient} is reachable through {@link #raw()} as an
 * escape hatch for endpoints not yet wrapped here.
 *
 * <p>Construct via {@link #builder()}:
 *
 * <pre>{@code
 * try (DefinancyClient definancy = DefinancyClient.builder()
 *         .audience("https://stub.definancy.com")
 *         .auth(authProvider)
 *         .build()) {
 *     List<Vault> vaults = definancy.vaults().list();
 * }
 * }</pre>
 *
 * <p>Implements {@link AutoCloseable} — closing releases the Jersey
 * client's connection pool and worker threads.
 */
public final class DefinancyClient implements AutoCloseable {
    private final ApiClient apiClient;
    private final ResponseTracker tracker;

    private final Health health;
    private final Auth auth;
    private final Networks networks;
    private final Assets assets;
    private final Contracts contracts;
    private final Vaults vaults;
    private final PaymentAcceptances paymentAcceptances;
    private final Documents documents;
    private final VelocityLimits velocityLimits;
    private final QrCodes qrCodes;
    private final Experimental experimental;

    DefinancyClient(ApiClient apiClient, ResponseTracker tracker, Invoker invoker) {
        this.apiClient = Objects.requireNonNull(apiClient, "apiClient");
        this.tracker = Objects.requireNonNull(tracker, "tracker");

        this.health = new Health(new ProbeApi(apiClient), invoker);
        this.auth = new Auth(new AuthApi(apiClient), invoker);
        this.networks = new Networks(new NetworkApi(apiClient), invoker);
        this.assets = new Assets(new AssetApi(apiClient), invoker);
        this.contracts = new Contracts(new ContractApi(apiClient), invoker);
        this.vaults = new Vaults(
                new VaultApi(apiClient),
                new VaultContractSubscriptionApi(apiClient),
                invoker);
        this.paymentAcceptances = new PaymentAcceptances(new VaultPaymentApi(apiClient), invoker);
        this.documents = new Documents(new VaultDocumentApi(apiClient), invoker);
        this.velocityLimits = new VelocityLimits(new VelocityApi(apiClient), invoker);
        this.qrCodes = new QrCodes(new QrCodeApi(apiClient), invoker);
        this.experimental = new Experimental(new ExperimentalApi(apiClient), invoker);
    }

    /** Start a new builder with the SDK's defaults applied. */
    public static DefinancyClientBuilder builder() {
        return new DefinancyClientBuilder();
    }

    public Health health() { return health; }
    public Auth auth() { return auth; }
    public Networks networks() { return networks; }
    public Assets assets() { return assets; }
    public Contracts contracts() { return contracts; }
    public Vaults vaults() { return vaults; }
    public PaymentAcceptances paymentAcceptances() { return paymentAcceptances; }
    public Documents documents() { return documents; }
    public VelocityLimits velocityLimits() { return velocityLimits; }
    public QrCodes qrCodes() { return qrCodes; }
    public Experimental experimental() { return experimental; }

    /**
     * Escape hatch — the underlying generated {@link ApiClient}, with
     * the SDK's transport configuration applied (Apache connector, DPoP
     * filter, response tracker). Use to construct per-tag {@code *Api}
     * instances for endpoints the facade does not yet wrap.
     */
    public ApiClient raw() {
        return apiClient;
    }

    /**
     * The {@code X-Request-Id} returned by the most recent successful
     * call (or {@code null} if no call has succeeded yet, or the response
     * carried no such header). Use for correlating with daemon logs.
     */
    public String lastRequestId() {
        return tracker.lastRequestId();
    }

    /**
     * The {@code x-ratelimit-*} info parsed from the most recent
     * successful call (or {@code null} if no call has succeeded yet, or
     * the response carried no rate-limit headers).
     */
    public RateLimitInfo lastRateLimit() {
        return tracker.lastRateLimit();
    }

    /**
     * Release the Jersey client's connection pool + worker threads.
     * Safe to call multiple times. After close, this client must not be
     * used.
     */
    @Override
    public void close() {
        Client httpClient = (Client) apiClient.getHttpClient();
        if (httpClient != null) {
            httpClient.close();
        }
    }
}
