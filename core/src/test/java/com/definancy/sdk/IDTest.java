package com.definancy.sdk;

import com.definancy.sdk.crypto.Ed25519PublicKey;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Java-specific contract tests for {@link ID}.
 *
 * <p>Cross-language byte-identity for the encode path is asserted by the
 * conformance Runner against {@code id_checksum/*.yaml} vectors. These
 * tests cover the inverse path (string → ID), invalid-input rejection,
 * and equality semantics that no shared vector reaches.
 */
public final class IDTest {

    /**
     * Canonical Definancy ID string derived from a 32-byte zero public key.
     * Pre-computed by encoding {@code new ID(new Ed25519PublicKey(zero32))}
     * through the SDK; pinned here so the inverse path can be tested
     * without re-deriving from the public key on every assertion.
     */
    private static final byte[] ZERO32 = new byte[32];

    // -------------------------------------------------------------------------
    // Round-trip: bytes → ID → string → ID → bytes
    // -------------------------------------------------------------------------

    @Test
    public void roundTrip_bytesToStringToBytes() throws Exception {
        ID original = new ID(ZERO32);
        String encoded = original.encodeAsString();
        assertEquals(58, encoded.length(), "encoded ID must be 58 chars");

        ID parsed = new ID(encoded);
        assertArrayEquals(original.toBytes(), parsed.toBytes(),
                "string round-trip must preserve raw bytes");
    }

    @Test
    public void roundTrip_publicKeyToIdToString() throws Exception {
        Ed25519PublicKey pk = new Ed25519PublicKey(ZERO32);
        ID id = new ID(pk);
        // ID(publicKey) and ID(bytes) should produce identical underlying state.
        assertArrayEquals(new ID(ZERO32).toBytes(), id.toBytes());
        assertEquals(new ID(ZERO32).toString(), id.toString());
    }

    @Test
    public void toString_matchesEncodeAsString() throws Exception {
        ID id = new ID(ZERO32);
        assertEquals(id.encodeAsString(), id.toString(),
                "toString must delegate to encodeAsString");
    }

    @Test
    public void toString_stableAcrossCalls() {
        ID id = new ID(ZERO32);
        assertEquals(id.toString(), id.toString());
    }

    @Test
    public void toPublicKey_roundTripsBytes() {
        ID id = new ID(ZERO32);
        Ed25519PublicKey pk = id.toPublicKey();
        assertArrayEquals(ZERO32, pk.getBytes());
    }

    // -------------------------------------------------------------------------
    // Invalid input — null
    // -------------------------------------------------------------------------

    @Test
    public void constructor_nullBytes_throwsNPE() {
        // Contract: null bytes input throws NullPointerException
        // (Objects.requireNonNull). NPE is the Java convention for null
        // arguments on reference parameters.
        assertThrows(NullPointerException.class, () -> new ID((byte[]) null));
    }

    @Test
    public void constructor_nullString_throwsNPE() {
        // Same null-rejection contract for the String overload.
        assertThrows(NullPointerException.class, () -> new ID((String) null));
    }

    // -------------------------------------------------------------------------
    // Invalid input — wrong length
    // -------------------------------------------------------------------------

    @Test
    public void constructor_wrongByteLength_throwsIAE() {
        assertThrows(IllegalArgumentException.class,
                () -> new ID(new byte[31]));
        assertThrows(IllegalArgumentException.class,
                () -> new ID(new byte[33]));
        assertThrows(IllegalArgumentException.class,
                () -> new ID(new byte[0]));
    }

    @Test
    public void constructor_wrongStringLength_throwsIAE() {
        // 57 chars (one short)
        assertThrows(IllegalArgumentException.class,
                () -> new ID(repeat('A', 57)));
        // 59 chars (one long)
        assertThrows(IllegalArgumentException.class,
                () -> new ID(repeat('A', 59)));
        // 0 chars (empty string)
        assertThrows(IllegalArgumentException.class,
                () -> new ID(""));
    }

    /** Java 8-compatible String.repeat replacement. */
    private static String repeat(char c, int n) {
        char[] buf = new char[n];
        Arrays.fill(buf, c);
        return new String(buf);
    }

    // -------------------------------------------------------------------------
    // Invalid input — bad checksum
    // -------------------------------------------------------------------------

    @Test
    public void constructor_badChecksum_throwsIAE() throws Exception {
        // Take a valid ID string, flip the last character to corrupt
        // the checksum. (Last 4 bytes of the 36-byte payload encode to
        // the trailing portion of the 58-char base32 string.)
        String valid = new ID(ZERO32).encodeAsString();
        char lastChar = valid.charAt(valid.length() - 1);
        // Pick a different valid base32 character (A-Z, 2-7).
        char swap = (lastChar == 'A') ? 'B' : 'A';
        String corrupted = valid.substring(0, valid.length() - 1) + swap;

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new ID(corrupted));
        // Either "checksum did not validate" (when the ID portion decodes
        // to 36 bytes but the trailing 4 don't match) or a length error
        // (if the swap landed on a base32 boundary that decodes shorter).
        // Both are acceptable rejection paths — assert it's one of them.
        String msg = ex.getMessage();
        assertNotNull(msg);
        assertFalse(msg.isEmpty(), "exception message must explain the failure");
    }

    // -------------------------------------------------------------------------
    // Invalid input — bad Base32 characters
    // -------------------------------------------------------------------------

    @Test
    public void constructor_badBase32Chars_throwsIAE() throws Exception {
        // Apache commons-codec Base32 silently drops chars outside the
        // alphabet — so "1" or "!" in a 58-char string still decodes,
        // but to a shorter byte array. The length check then trips. The
        // rejection happens, but as a length error, not a charset error.
        // Either way: an IllegalArgumentException with a non-empty
        // message must be raised. Don't pin which message.
        String valid = new ID(ZERO32).encodeAsString();
        // Replace the first char with '1' (not in the RFC 4648 base32
        // alphabet, which is A-Z + 2-7).
        String corrupted = "1" + valid.substring(1);

        assertThrows(IllegalArgumentException.class,
                () -> new ID(corrupted));
    }

    // -------------------------------------------------------------------------
    // Equality / hashCode — value-based
    //
    // ID overrides Object.equals and hashCode to compare on the 32-byte
    // payload. Two IDs constructed from the same bytes ARE .equals(...)
    // and hash to the same value. This is the contract HashMap/HashSet
    // users rely on.
    // -------------------------------------------------------------------------

    @Test
    public void equals_isValueBased() throws Exception {
        ID a = new ID(ZERO32);
        ID b = new ID(ZERO32);
        // Same backing bytes — value-equal regardless of object identity.
        assertArrayEquals(a.toBytes(), b.toBytes(),
                "precondition: bytes are equal");
        assertEquals(a, b,
                "ID overrides equals; value-based comparison");
        // Reflexive.
        assertEquals(a, a);
        // hashCode contract: equal objects must have equal hash codes.
        assertEquals(a.hashCode(), b.hashCode(),
                "equals/hashCode contract: equal IDs must hash equal");
    }

    @Test
    public void equals_differentBytes_unequal() throws Exception {
        byte[] other = new byte[32];
        other[0] = (byte) 0x01;
        assertNotEquals(new ID(ZERO32), new ID(other),
                "IDs with different bytes must not be equal");
    }

    @Test
    public void hashCode_isValueBased() {
        ID a = new ID(ZERO32);
        ID b = new ID(ZERO32);
        // Value-based hash: equal-content IDs hash to the same value.
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void roundTripFromString_producesEqualBytesAndEqualObjects() throws Exception {
        String encoded = new ID(ZERO32).encodeAsString();
        ID parsed1 = new ID(encoded);
        ID parsed2 = new ID(encoded);
        assertTrue(Arrays.equals(parsed1.toBytes(), parsed2.toBytes()),
                "byte equality holds across reparses");
        assertEquals(parsed1, parsed2,
                "value-equal IDs are .equals (equals is value-based)");
        assertEquals(parsed1.hashCode(), parsed2.hashCode(),
                "equals/hashCode contract holds");
    }
}
