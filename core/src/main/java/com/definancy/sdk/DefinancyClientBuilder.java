package com.definancy.sdk;

import com.definancy.ApiClient;
import com.definancy.sdk.auth.AuthProvider;
import com.definancy.sdk.internal.ApiClientFactory;
import com.definancy.sdk.internal.Invoker;
import com.definancy.sdk.internal.ResponseTracker;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Builder for {@link DefinancyClient}. Construct via
 * {@link DefinancyClient#builder()} — never directly.
 *
 * <p>Required: {@link #audience(String)}.
 *
 * <p>Optional: {@link #auth(AuthProvider)} (omit for unauthenticated
 * stub-only use), {@link #retry(RetryPolicy)} (default:
 * {@link RetryPolicy#DEFAULT}), {@link #connectTimeout(Duration)},
 * {@link #readTimeout(Duration)}, {@link #filter(Object)} for
 * registering additional Jersey {@code ClientRequestFilter} /
 * {@code ClientResponseFilter} / {@code Feature} instances.
 */
public final class DefinancyClientBuilder {
    private String audience;
    private AuthProvider authProvider;
    private RetryPolicy retry = RetryPolicy.DEFAULT;
    private Duration connectTimeout;
    private Duration readTimeout;
    private final List<Object> filters = new ArrayList<>();

    DefinancyClientBuilder() {}

    /** Required. The API base URL (e.g. {@code https://stub.definancy.com}). */
    public DefinancyClientBuilder audience(String audience) {
        this.audience = audience;
        return this;
    }

    /**
     * Sign each outbound request with this {@link AuthProvider}. Pass
     * {@code null} (or omit) to skip auth-header signing entirely —
     * only useful against the stub environment.
     */
    public DefinancyClientBuilder auth(AuthProvider authProvider) {
        this.authProvider = authProvider;
        return this;
    }

    /** Retry policy applied to every facade call. Default: {@link RetryPolicy#DEFAULT}. */
    public DefinancyClientBuilder retry(RetryPolicy retry) {
        this.retry = retry == null ? RetryPolicy.DEFAULT : retry;
        return this;
    }

    /** Connection-establishment timeout. */
    public DefinancyClientBuilder connectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    /** Read timeout for receiving response data. */
    public DefinancyClientBuilder readTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    /**
     * Register an additional Jersey filter, feature, or interceptor on
     * top of the SDK's defaults (Apache connector, DPoP auth filter,
     * response tracker).
     */
    public DefinancyClientBuilder filter(Object filter) {
        if (filter != null) this.filters.add(filter);
        return this;
    }

    /** Bulk-register filters. Equivalent to calling {@link #filter(Object)} per element. */
    public DefinancyClientBuilder filters(Collection<?> filters) {
        if (filters != null) {
            for (Object f : filters) {
                if (f != null) this.filters.add(f);
            }
        }
        return this;
    }

    /** Bulk-register filters (varargs convenience). */
    public DefinancyClientBuilder filters(Object... filters) {
        if (filters != null) return filters(Arrays.asList(filters));
        return this;
    }

    /** Build the {@link DefinancyClient}. Validates that {@link #audience(String)} was set. */
    public DefinancyClient build() {
        if (audience == null || audience.isEmpty()) {
            throw new IllegalStateException("audience(...) is required");
        }
        ResponseTracker tracker = new ResponseTracker();
        ApiClient apiClient = ApiClientFactory.create(
                audience,
                authProvider,
                tracker,
                connectTimeout,
                readTimeout,
                Collections.unmodifiableList(new ArrayList<>(filters)));
        Invoker invoker = new Invoker(retry);
        return new DefinancyClient(apiClient, tracker, invoker);
    }
}
