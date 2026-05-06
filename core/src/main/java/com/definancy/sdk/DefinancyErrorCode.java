package com.definancy.sdk;

/**
 * Hand-curated error-code constants for the codes partners are most
 * likely to branch on. The full code space is open-ended (the wire
 * format is {@code AAA-NNN}, e.g. {@code AUT-401}, {@code BIZ-010},
 * {@code RES-001}); the constants here are the subset where typed
 * branching beats raw string comparison.
 *
 * <p>For any code not listed here, use the raw string accessible via
 * {@link DefinancyApiException#code()}.
 */
public final class DefinancyErrorCode {
    private DefinancyErrorCode() {}

    /** Authentication missing or rejected (HTTP 401). */
    public static final String AUTH_MISSING = "AUT-401";

    /** Velocity-limit reject-mode threshold breached. */
    public static final String VELOCITY_LIMIT_EXCEEDED = "BIZ-010";
}
