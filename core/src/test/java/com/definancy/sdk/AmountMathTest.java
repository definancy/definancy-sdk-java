package com.definancy.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Java-specific edge cases for {@link AmountMath} that the cross-language
 * {@code amount_math/round_trip} conformance vector doesn't cover —
 * null handling, decimals=0/decimals=18 boundary behavior, contract for
 * over-precision rejection, round-trip stability, and empty-string input.
 *
 * <p>These tests pin Java-side contracts. Cross-language byte-identity
 * is asserted by the conformance Runner against shared YAML vectors.
 */
public final class AmountMathTest {

    // -------------------------------------------------------------------------
    // Null handling
    // -------------------------------------------------------------------------

    @Test
    public void valueToRaw_nullValue_throwsNPE() {
        // Implementation does no explicit null check; it calls
        // value.startsWith(...) first, which yields NullPointerException.
        // Pin that contract — callers shouldn't expect IllegalArgumentException.
        assertThrows(NullPointerException.class, () -> AmountMath.valueToRaw(null, 6));
    }

    @Test
    public void rawToValue_nullRaw_throwsNPE() {
        assertThrows(NullPointerException.class, () -> AmountMath.rawToValue(null, 6));
    }

    // -------------------------------------------------------------------------
    // Negative-decimals contract
    // -------------------------------------------------------------------------

    @Test
    public void valueToRaw_negativeDecimals_throwsIAE() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> AmountMath.valueToRaw("1.0", -1));
        assertEquals("decimals must be non-negative, got -1", ex.getMessage());
    }

    @Test
    public void rawToValue_negativeDecimals_throwsIAE() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> AmountMath.rawToValue("100", -1));
        assertEquals("decimals must be non-negative, got -1", ex.getMessage());
    }

    // -------------------------------------------------------------------------
    // decimals=0 boundary (no fractional part at all)
    // -------------------------------------------------------------------------

    @Test
    public void valueToRaw_decimalsZero_integerInput() {
        assertEquals("42", AmountMath.valueToRaw("42", 0));
    }

    @Test
    public void valueToRaw_decimalsZero_zeroInput() {
        assertEquals("0", AmountMath.valueToRaw("0", 0));
    }

    @Test
    public void valueToRaw_decimalsZero_fractionalInputRejected() {
        // "1.5" with decimals=0 has 1 decimal place but 0 are allowed.
        assertThrows(
                IllegalArgumentException.class,
                () -> AmountMath.valueToRaw("1.5", 0));
    }

    @Test
    public void rawToValue_decimalsZero_stripsLeadingZeros() {
        assertEquals("42", AmountMath.rawToValue("0042", 0));
    }

    @Test
    public void rawToValue_decimalsZero_negative() {
        assertEquals("-42", AmountMath.rawToValue("-42", 0));
    }

    // -------------------------------------------------------------------------
    // decimals=18 boundary (Ethereum-style, max precision)
    // -------------------------------------------------------------------------

    @Test
    public void valueToRaw_decimals18_oneEther() {
        assertEquals("1000000000000000000", AmountMath.valueToRaw("1", 18));
    }

    @Test
    public void valueToRaw_decimals18_oneWei() {
        assertEquals("1", AmountMath.valueToRaw("0.000000000000000001", 18));
    }

    @Test
    public void valueToRaw_decimals18_overPrecisionRejected() {
        // 19 decimal places, but only 18 allowed.
        assertThrows(
                IllegalArgumentException.class,
                () -> AmountMath.valueToRaw("0.0000000000000000001", 18));
    }

    @Test
    public void rawToValue_decimals18_oneWei() {
        assertEquals("0.000000000000000001", AmountMath.rawToValue("1", 18));
    }

    @Test
    public void rawToValue_decimals18_oneEther() {
        assertEquals("1", AmountMath.rawToValue("1000000000000000000", 18));
    }

    // -------------------------------------------------------------------------
    // Trailing-zero stripping (rawToValue strips them; valueToRaw doesn't
    // round, it requires the canonical form)
    // -------------------------------------------------------------------------

    @Test
    public void rawToValue_stripsTrailingZeros() {
        // 1500000 / 10^6 = 1.5 (not "1.500000")
        assertEquals("1.5", AmountMath.rawToValue("1500000", 6));
    }

    @Test
    public void rawToValue_zeroFractionDropsDot() {
        // 7000000 / 10^6 = 7 (not "7." or "7.0")
        assertEquals("7", AmountMath.rawToValue("7000000", 6));
    }

    @Test
    public void valueToRaw_doesNotRound_tooManyDecimalsThrows() {
        // Confirms the implementation does NOT silently truncate or use
        // any rounding mode (HALF_UP, HALF_EVEN). Over-precision is a
        // hard error — the contract is "no implicit precision loss".
        assertThrows(
                IllegalArgumentException.class,
                () -> AmountMath.valueToRaw("1.1234567", 6));
    }

    // -------------------------------------------------------------------------
    // Round-trip stability for canonical inputs
    // -------------------------------------------------------------------------

    @Test
    public void roundTrip_canonicalValues_decimals6() {
        String[] canonical = {"0", "1", "1.5", "0.000001", "1234567.89", "-1.5"};
        for (String v : canonical) {
            String raw = AmountMath.valueToRaw(v, 6);
            String back = AmountMath.rawToValue(raw, 6);
            assertEquals(v, back, "round-trip drift on \"" + v + "\"");
        }
    }

    @Test
    public void roundTrip_rawCanonical_decimals6() {
        // raw → value → raw should be stable when raw has no leading zeros
        String[] canonical = {"0", "1", "1500000", "1000000", "999999", "-1500000"};
        for (String r : canonical) {
            String v = AmountMath.rawToValue(r, 6);
            String back = AmountMath.valueToRaw(v, 6);
            assertEquals(r, back, "round-trip drift on raw \"" + r + "\"");
        }
    }

    @Test
    public void roundTrip_nonCanonicalCollapsesToCanonical() {
        // Documented in the AmountMath javadoc: "1.50" → "1.5" round-trip.
        // This is intentional behavior — not a bug.
        String raw = AmountMath.valueToRaw("1.50", 6);
        assertEquals("1500000", raw);
        assertEquals("1.5", AmountMath.rawToValue(raw, 6));
    }

    // -------------------------------------------------------------------------
    // Negative-zero handling
    // -------------------------------------------------------------------------

    @Test
    public void valueToRaw_negativeZero_dropsSign() {
        // -0.000 → "0" (not "-0"). Implementation explicitly checks
        // raw.equals("0") before prepending the minus sign.
        assertEquals("0", AmountMath.valueToRaw("-0", 6));
        assertEquals("0", AmountMath.valueToRaw("-0.000000", 6));
    }

    @Test
    public void rawToValue_negativeZero_dropsSign() {
        assertEquals("0", AmountMath.rawToValue("-0", 6));
        assertEquals("0", AmountMath.rawToValue("-0", 0));
    }

    // -------------------------------------------------------------------------
    // Empty-string input
    // -------------------------------------------------------------------------

    @Test
    public void valueToRaw_emptyString_returnsZerosOfLength() {
        // Empty string: not negative, no dot → intPart="", fracPart="".
        // padEnd("", decimals=6, '0') → "000000".
        // stripLeadingZeros("000000") → "0".
        // So empty string → "0". Pin this Java-specific behavior.
        assertEquals("0", AmountMath.valueToRaw("", 6));
    }

    @Test
    public void rawToValue_emptyString_returnsZero() {
        // Empty string: padStart("", 7, '0') → "0000000",
        // intPart=stripLeadingZeros("0") → "0", fracPart="000000" → "".
        // → "0".
        assertEquals("0", AmountMath.rawToValue("", 6));
    }
}
