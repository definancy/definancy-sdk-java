package com.definancy.sdk;

/**
 * Pure-string conversion between human-readable decimal values and raw
 * integer strings (smallest-unit representation) for asset amounts.
 *
 * <p>No floating-point arithmetic — every operation is on string digits.
 * Mirrors the TypeScript SDK's {@code amounts.ts} byte-for-byte; the
 * cross-language conformance suite asserts both implementations produce
 * identical outputs against the same {@code amount_math} vectors.
 *
 * <p>Examples:
 * <pre>{@code
 *   valueToRaw("1.5", 6)         → "1500000"
 *   valueToRaw("0.000001", 6)    → "1"
 *   rawToValue("1500000", 6)     → "1.5"
 *   rawToValue("1", 6)           → "0.000001"
 * }</pre>
 *
 * <p>Round-trip is stable for canonical inputs (no trailing zeros after
 * the decimal point, no leading zeros on the integer part). A non-
 * canonical value like {@code "1.50"} round-trips to {@code "1.5"} —
 * vectors should always pin canonical forms.
 */
public final class AmountMath {

    /**
     * Convert a human-readable decimal value string to a raw integer
     * string in the smallest unit.
     *
     * @throws IllegalArgumentException if {@code decimals} is negative or
     *         if the value carries more decimal places than {@code decimals}.
     */
    public static String valueToRaw(String value, int decimals) {
        if (decimals < 0) {
            throw new IllegalArgumentException(
                    "decimals must be non-negative, got " + decimals);
        }
        boolean negative = value.startsWith("-");
        String abs = negative ? value.substring(1) : value;

        int dotIndex = abs.indexOf('.');
        String intPart;
        String fracPart;
        if (dotIndex == -1) {
            intPart = abs;
            fracPart = "";
        } else {
            intPart = abs.substring(0, dotIndex);
            fracPart = abs.substring(dotIndex + 1);
        }

        if (fracPart.length() > decimals) {
            throw new IllegalArgumentException(
                    "value \"" + value + "\" has " + fracPart.length()
                            + " decimal places, but only " + decimals + " are allowed");
        }

        String paddedFrac = padEnd(fracPart, decimals, '0');
        String raw = stripLeadingZeros(intPart + paddedFrac);
        return (negative && !raw.equals("0")) ? "-" + raw : raw;
    }

    /**
     * Convert a raw integer string in the smallest unit to a
     * human-readable decimal value string.
     *
     * <p>Strips trailing zeros from the fractional part — {@code "1500000"}
     * with {@code decimals=6} returns {@code "1.5"}, not {@code "1.500000"}.
     *
     * @throws IllegalArgumentException if {@code decimals} is negative.
     */
    public static String rawToValue(String raw, int decimals) {
        if (decimals < 0) {
            throw new IllegalArgumentException(
                    "decimals must be non-negative, got " + decimals);
        }
        boolean negative = raw.startsWith("-");
        String abs = negative ? raw.substring(1) : raw;

        if (decimals == 0) {
            String val = stripLeadingZeros(abs);
            return (negative && !val.equals("0")) ? "-" + val : val;
        }

        String padded = padStart(abs, decimals + 1, '0');
        String intPart = stripLeadingZeros(padded.substring(0, padded.length() - decimals));
        String fracPart = stripTrailingZeros(padded.substring(padded.length() - decimals));

        String value = !fracPart.isEmpty() ? intPart + "." + fracPart : intPart;
        return (negative && !value.equals("0")) ? "-" + value : value;
    }

    private static String padEnd(String s, int len, char pad) {
        if (s.length() >= len) {
            return s;
        }
        StringBuilder sb = new StringBuilder(len);
        sb.append(s);
        for (int i = s.length(); i < len; i++) {
            sb.append(pad);
        }
        return sb.toString();
    }

    private static String padStart(String s, int len, char pad) {
        if (s.length() >= len) {
            return s;
        }
        StringBuilder sb = new StringBuilder(len);
        for (int i = s.length(); i < len; i++) {
            sb.append(pad);
        }
        sb.append(s);
        return sb.toString();
    }

    private static String stripLeadingZeros(String s) {
        int i = 0;
        while (i < s.length() - 1 && s.charAt(i) == '0') {
            i++;
        }
        return s.substring(i);
    }

    private static String stripTrailingZeros(String s) {
        int i = s.length();
        while (i > 0 && s.charAt(i - 1) == '0') {
            i--;
        }
        return s.substring(0, i);
    }

    private AmountMath() {}
}
