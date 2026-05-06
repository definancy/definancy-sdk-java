package com.definancy.sdk.internal;

import com.definancy.sdk.RateLimitInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * Jersey filter that captures response headers — {@code X-Request-Id}
 * and parsed {@code x-ratelimit-*} — into a {@link ResponseTracker} on
 * every response (success or failure).
 *
 * <p>Runs at the Jersey filter layer rather than at the SDK call site
 * so it captures metadata even on responses the openapi-generator
 * client converts into {@code ApiException}s.
 */
@Provider
public final class TrackingFilter implements ClientResponseFilter {
    private final ResponseTracker tracker;

    public TrackingFilter(ResponseTracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext)
            throws IOException {
        MultivaluedMap<String, String> headers = responseContext.getHeaders();
        if (headers == null) return;

        Map<String, List<String>> asMap = toMap(headers);
        String requestId = firstHeader(asMap, "x-request-id");
        if (requestId != null) {
            tracker.setLastRequestId(requestId);
        }

        RateLimitInfo info = RateLimitInfo.parse(asMap);
        if (info != null) {
            tracker.setLastRateLimit(info);
        }
    }

    private static String firstHeader(Map<String, List<String>> headers, String name) {
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(name)) continue;
            List<String> values = entry.getValue();
            if (values != null && !values.isEmpty()) return values.get(0);
        }
        return null;
    }

    private static Map<String, List<String>> toMap(MultivaluedMap<String, String> mv) {
        Map<String, List<String>> out = new HashMap<>(mv.size());
        for (Map.Entry<String, List<String>> e : mv.entrySet()) {
            out.put(e.getKey(), new ArrayList<>(e.getValue()));
        }
        return out;
    }
}
