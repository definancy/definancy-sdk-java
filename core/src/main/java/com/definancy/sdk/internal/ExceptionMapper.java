package com.definancy.sdk.internal;

import com.definancy.ApiException;
import com.definancy.sdk.DefinancyApiException;
import com.definancy.sdk.exceptions.AuthenticationException;
import com.definancy.sdk.exceptions.NotFoundException;
import com.definancy.sdk.exceptions.RateLimitException;
import com.definancy.sdk.exceptions.ServerException;
import com.definancy.sdk.exceptions.ValidationException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Maps openapi-generator's {@link ApiException} into the SDK's typed
 * {@link DefinancyApiException} hierarchy. Parses the response body as
 * an {@code ErrorList} (per spec), extracts {@code X-Request-Id} from
 * the response headers, and selects the most specific subclass based
 * on HTTP status.
 */
public final class ExceptionMapper {
    private static final ObjectMapper JSON = new ObjectMapper();

    private ExceptionMapper() {}

    /** Map an openapi-generator {@link ApiException} into the typed hierarchy. */
    public static DefinancyApiException map(ApiException e) {
        int status = e.getCode();
        Map<String, List<String>> headers = e.getResponseHeaders();
        String requestId = DefinancyApiException.extractRequestId(headers);
        List<ErrorBody> errors = parseErrors(e.getResponseBody());

        if (status == 400) {
            return new ValidationException(status, errors, requestId, e);
        }
        if (status == 401 || status == 403) {
            return new AuthenticationException(status, errors, requestId, e);
        }
        if (status == 404) {
            return new NotFoundException(status, errors, requestId, e);
        }
        if (status == 429) {
            Duration retryAfter = parseRetryAfter(headers);
            return new RateLimitException(status, errors, requestId, retryAfter, e);
        }
        if (status >= 500 && status <= 599) {
            return new ServerException(status, errors, requestId, e);
        }
        return new DefinancyApiException(status, errors, requestId, e);
    }

    private static List<ErrorBody> parseErrors(String body) {
        if (body == null || body.isEmpty()) return Collections.emptyList();
        try {
            JsonNode root = JSON.readTree(body);
            if (!root.isArray()) return Collections.emptyList();
            List<ErrorBody> out = new ArrayList<>(root.size());
            for (JsonNode node : root) {
                JsonNode codeNode = node.get("code");
                JsonNode msgNode = node.get("message");
                if (codeNode == null || msgNode == null) continue;
                out.add(new ErrorBody(codeNode.asText(), msgNode.asText()));
            }
            return out;
        } catch (JsonProcessingException ex) {
            return Collections.emptyList();
        }
    }

    private static Duration parseRetryAfter(Map<String, List<String>> headers) {
        if (headers == null) return null;
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase("retry-after")) continue;
            List<String> values = entry.getValue();
            if (values == null || values.isEmpty()) continue;
            String raw = values.get(0);
            try {
                long seconds = Long.parseLong(raw.trim());
                if (seconds >= 0) return Duration.ofSeconds(seconds);
            } catch (NumberFormatException ignored) {
                // Try HTTP-date form
                try {
                    OffsetDateTime when = OffsetDateTime.parse(raw);
                    long delta = Duration.between(OffsetDateTime.now(), when).getSeconds();
                    if (delta < 0) delta = 0;
                    return Duration.ofSeconds(delta);
                } catch (DateTimeParseException ignoredToo) {
                    return null;
                }
            }
        }
        return null;
    }
}
