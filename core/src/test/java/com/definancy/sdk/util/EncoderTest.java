package com.definancy.sdk.util;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Java-specific contract tests for {@link Encoder} (Base32 + Base64url).
 *
 * <p>Cross-language byte-identity for both encoders is asserted by the
 * conformance Runner against {@code base32/*.yaml} and
 * {@code base64url/*.yaml} vectors — those exercise canonical
 * input→output mapping. These tests cover the inverse path
 * (round-trip), all 5 Base32 padding cases, all 3 Base64url padding
 * cases, empty-input handling, and the practical behavior on
 * out-of-alphabet input (which is more lenient than callers might
 * assume — pinning current behavior).
 */
public final class EncoderTest {

    // -------------------------------------------------------------------------
    // Round-trip — Base32 (covers all 5 length-mod-5 padding cases)
    //
    // RFC 4648 Base32 packs 5 bytes per 8 chars. Lengths 0/1/2/3/4 mod 5
    // each produce a different padding pattern. The Encoder strips '='
    // padding on encode but commons-codec's decoder accepts both forms.
    // -------------------------------------------------------------------------

    @Test
    public void base32_roundTrip_emptyInput() {
        roundTripBase32(new byte[0]);
    }

    @Test
    public void base32_roundTrip_oneByte() {
        roundTripBase32(new byte[]{(byte) 0xab}); // length 1 → 6 '=' pads
    }

    @Test
    public void base32_roundTrip_twoBytes() {
        roundTripBase32(new byte[]{(byte) 0xab, (byte) 0xcd}); // length 2 → 4 pads
    }

    @Test
    public void base32_roundTrip_threeBytes() {
        roundTripBase32(new byte[]{(byte) 0xab, (byte) 0xcd, (byte) 0xef}); // 3 pads
    }

    @Test
    public void base32_roundTrip_fourBytes() {
        roundTripBase32(new byte[]{(byte) 0xab, (byte) 0xcd, (byte) 0xef, (byte) 0x01}); // 1 pad
    }

    @Test
    public void base32_roundTrip_fiveBytes() {
        roundTripBase32(new byte[]{(byte) 0xab, (byte) 0xcd, (byte) 0xef, (byte) 0x01, (byte) 0x23}); // no pad
    }

    @Test
    public void base32_roundTrip_largerInput() {
        // 32 bytes — the canonical Definancy ID byte length, exercises
        // the encode/decode loop beyond a single 5-byte block.
        byte[] data = new byte[32];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i * 7 + 13);
        }
        roundTripBase32(data);
    }

    // -------------------------------------------------------------------------
    // Base32 — encoder strips trailing '=' padding (Definancy contract)
    // -------------------------------------------------------------------------

    @Test
    public void base32_encode_stripsTrailingPadding() {
        // 1 byte normally encodes to "VK======" — Encoder must strip the '='.
        String encoded = Encoder.encodeToBase32(new byte[]{(byte) 0xab});
        assertEquals(2, encoded.length(),
                "encoded form must have padding stripped");
        assertTrue(!encoded.contains("="),
                "encoded form must not contain '='");
    }

    @Test
    public void base32_decode_acceptsBothPaddedAndUnpadded() {
        byte[] data = new byte[]{(byte) 0xab};
        String unpadded = Encoder.encodeToBase32(data);
        // commons-codec Base32 decoder accepts padded form too.
        String padded = unpadded + "======";
        assertArrayEquals(data, Encoder.decodeFromBase32(padded),
                "decoder must accept padded input");
        assertArrayEquals(data, Encoder.decodeFromBase32(unpadded),
                "decoder must accept unpadded input");
    }

    // -------------------------------------------------------------------------
    // Base32 — out-of-alphabet input is rejected strictly.
    //
    // Apache commons-codec Base32 is internally lenient (silently drops
    // chars outside the alphabet), so the Encoder pre-validates input
    // against the RFC 4648 alphabet [A-Z2-7=] and throws
    // IllegalArgumentException on any out-of-alphabet character.
    // -------------------------------------------------------------------------

    @Test
    public void base32_decode_outOfAlphabetChars_throwsIAE() {
        byte[] data = new byte[]{(byte) 0xab};
        String valid = Encoder.encodeToBase32(data);
        // Inject "!" into the middle — not in A-Z + 2-7.
        String corrupted = valid.charAt(0) + "!" + valid.charAt(1);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> Encoder.decodeFromBase32(corrupted),
                "out-of-alphabet chars must be rejected, not silently dropped");
        assertNotNull(ex.getMessage());
        assertTrue(ex.getMessage().contains("Base32"),
                "rejection message should identify the encoding");
    }

    @Test
    public void base32_decode_nullInput_throwsNPE() {
        assertThrows(NullPointerException.class, () -> Encoder.decodeFromBase32(null));
    }

    // -------------------------------------------------------------------------
    // Round-trip — Base64url (covers all 3 length-mod-3 padding cases)
    //
    // RFC 4648 Base64 packs 3 bytes per 4 chars. Lengths 0/1/2 mod 3
    // each produce a different padding pattern.
    // -------------------------------------------------------------------------

    @Test
    public void base64_roundTrip_emptyInput() {
        roundTripBase64(new byte[0]);
    }

    @Test
    public void base64_roundTrip_oneByte() {
        roundTripBase64(new byte[]{(byte) 0xab}); // length 1 → 2 pads in standard form
    }

    @Test
    public void base64_roundTrip_twoBytes() {
        roundTripBase64(new byte[]{(byte) 0xab, (byte) 0xcd}); // length 2 → 1 pad
    }

    @Test
    public void base64_roundTrip_threeBytes() {
        roundTripBase64(new byte[]{(byte) 0xab, (byte) 0xcd, (byte) 0xef}); // length 3 → no pad
    }

    @Test
    public void base64_roundTrip_largerInput() {
        // 64 bytes — Ed25519 signature length.
        byte[] data = new byte[64];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (i * 11 + 37);
        }
        roundTripBase64(data);
    }

    @Test
    public void base64_encode_isUrlSafeAndUnpadded() {
        // commons-codec Base64(0, null, true) → urlSafe + unpadded.
        // That means '+'→'-', '/'→'_', and no trailing '='.
        // Construct a byte sequence that would produce '+' or '/' in
        // standard base64 — bytes {0xff, 0xff, 0xff} → "////" standard,
        // "____" url-safe.
        String encoded = Encoder.encodeToBase64(new byte[]{(byte) 0xff, (byte) 0xff, (byte) 0xff});
        assertTrue(!encoded.contains("+"),
                "url-safe alphabet must not produce '+'");
        assertTrue(!encoded.contains("/"),
                "url-safe alphabet must not produce '/'");
        assertTrue(!encoded.contains("="),
                "unpadded url-safe form must not contain '='");
        assertEquals("____", encoded);
    }

    // -------------------------------------------------------------------------
    // Base64url — out-of-alphabet input is rejected strictly.
    //
    // Same hardening as Base32: the Encoder pre-validates input against
    // the RFC 4648 §5 url-safe alphabet [A-Za-z0-9_=-] and throws
    // IllegalArgumentException on any out-of-alphabet character.
    // -------------------------------------------------------------------------

    @Test
    public void base64_decode_outOfAlphabetChars_throwsIAE() {
        byte[] data = new byte[]{(byte) 0xab, (byte) 0xcd, (byte) 0xef};
        String valid = Encoder.encodeToBase64(data);
        // '!' is not in the base64url alphabet (which is A-Z + a-z + 0-9 + - + _).
        String corrupted = valid.charAt(0) + "!" + valid.substring(1);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> Encoder.decodeFromBase64(corrupted),
                "out-of-alphabet chars must be rejected, not silently dropped");
        assertNotNull(ex.getMessage());
        assertTrue(ex.getMessage().contains("Base64"),
                "rejection message should identify the encoding");
    }

    @Test
    public void base64_decode_nullInput_throwsNPE() {
        assertThrows(NullPointerException.class, () -> Encoder.decodeFromBase64(null));
    }

    // -------------------------------------------------------------------------
    // Empty-input handling — both directions
    // -------------------------------------------------------------------------

    @Test
    public void base32_emptyInputs_returnEmptyOutputs() {
        assertEquals("", Encoder.encodeToBase32(new byte[0]));
        assertArrayEquals(new byte[0], Encoder.decodeFromBase32(""));
    }

    @Test
    public void base64_emptyInputs_returnEmptyOutputs() {
        assertEquals("", Encoder.encodeToBase64(new byte[0]));
        assertArrayEquals(new byte[0], Encoder.decodeFromBase64(""));
    }

    // -------------------------------------------------------------------------
    // ASCII text round-trip — sanity check that both encoders handle
    // human-readable bytes (not just random binary).
    // -------------------------------------------------------------------------

    @Test
    public void base32_textRoundTrip() {
        String text = "Definancy SDK";
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        byte[] decoded = Encoder.decodeFromBase32(Encoder.encodeToBase32(bytes));
        assertEquals(text, new String(decoded, StandardCharsets.UTF_8));
    }

    @Test
    public void base64_textRoundTrip() {
        String text = "Definancy SDK with url-safe chars: ?+/=&";
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        byte[] decoded = Encoder.decodeFromBase64(Encoder.encodeToBase64(bytes));
        assertEquals(text, new String(decoded, StandardCharsets.UTF_8));
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private static void roundTripBase32(byte[] data) {
        String encoded = Encoder.encodeToBase32(data);
        assertNotNull(encoded);
        byte[] decoded = Encoder.decodeFromBase32(encoded);
        assertArrayEquals(data, decoded,
                "base32 round-trip failed for " + Arrays.toString(data)
                        + " (encoded=\"" + encoded + "\")");
    }

    private static void roundTripBase64(byte[] data) {
        String encoded = Encoder.encodeToBase64(data);
        assertNotNull(encoded);
        byte[] decoded = Encoder.decodeFromBase64(encoded);
        assertArrayEquals(data, decoded,
                "base64url round-trip failed for " + Arrays.toString(data)
                        + " (encoded=\"" + encoded + "\")");
    }
}
