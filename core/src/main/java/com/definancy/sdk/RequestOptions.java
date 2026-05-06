package com.definancy.sdk;

import java.time.Duration;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Per-call request options. Passed as the optional last argument on every
 * facade method (for example {@code definancy.vaults().get(id, options)}).
 *
 * <p>Immutable. Build via {@link #builder()}; reuse a single instance
 * across many calls when the options don't vary.
 */
public final class RequestOptions {
    private final Duration timeout;
    private final Map<String, String> headers;

    private RequestOptions(Builder b) {
        this.timeout = b.timeout;
        this.headers = b.headers == null
                ? Collections.emptyMap()
                : Collections.unmodifiableMap(new LinkedHashMap<>(b.headers));
    }

    /** Per-call read timeout override. {@code null} means inherit the client default. */
    public Duration timeout() {
        return timeout;
    }

    /** Extra headers merged onto the outgoing request. Never {@code null}. */
    public Map<String, String> headers() {
        return headers;
    }

    public static Builder builder() {
        return new Builder();
    }

    /** A pre-built no-op {@link RequestOptions} (no timeout override, no headers). */
    public static final RequestOptions DEFAULT = builder().build();

    public static final class Builder {
        private Duration timeout;
        private Map<String, String> headers;

        private Builder() {}

        public Builder timeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder header(String name, String value) {
            if (headers == null) headers = new LinkedHashMap<>();
            headers.put(name, value);
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers = headers == null ? null : new LinkedHashMap<>(headers);
            return this;
        }

        public RequestOptions build() {
            return new RequestOptions(this);
        }
    }
}
