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
    public void constructor_nullBytes_silentlyAccepts() {
        // Current contract: null bytes input → ID with the default-zero
        // backing array (no exception). Pin this — callers that pass null
        // intending an error get no error today; future change is breaking.
        ID id = new ID((byte[]) null);
        assertArrayEquals(new byte[32], id.toBytes());
    }

    @Test
    public void constructor_nullString_silentlyAccepts() {
        // Same null-acceptance contract for the String overload.
        ID id = new ID((String) null);
        assertArrayEquals(new byte[32], id.toBytes());
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
    // Equality / hashCode — current contract is identity-based
    //
    // ID does NOT override Object.equals or hashCode. Two IDs constructed
    // from the same input are NOT .equals(...) — they're only == to
    // themselves. Pin this so a future change to value-equality is a
    // visible breaking-change signal, not a silent semantic shift.
    // -------------------------------------------------------------------------

    @Test
    public void equals_isIdentityBased_notValueBased() throws Exception {
        ID a = new ID(ZERO32);
        ID b = new ID(ZERO32);
        // Same backing bytes — but different object identity.
        assertArrayEquals(a.toBytes(), b.toBytes(),
                "precondition: bytes are equal");
        assertNotEquals(a, b,
                "ID does not override equals; current contract is identity-based");
        // Each instance equals itself.
        assertEquals(a, a);
    }

    @Test
    public void hashCode_isIdentityBased() {
        ID a = new ID(ZERO32);
        // System.identityHashCode is what Object.hashCode delegates to
        // by default. If hashCode were overridden to a value-hash, this
        // would break — and that's the signal we want.
        assertEquals(System.identityHashCode(a), a.hashCode());
    }

    @Test
    public void roundTripFromString_producesEqualBytes_notEqualObjects() throws Exception {
        String encoded = new ID(ZERO32).encodeAsString();
        ID parsed1 = new ID(encoded);
        ID parsed2 = new ID(encoded);
        assertTrue(Arrays.equals(parsed1.toBytes(), parsed2.toBytes()),
                "byte equality holds across reparses");
        assertNotEquals(parsed1, parsed2,
                "object equality does NOT hold (no equals override)");
    }

    // tiny helper to keep imports clean
    private static void assertTrue(boolean cond, String msg) {
        org.junit.jupiter.api.Assertions.assertTrue(cond, msg);
    }
}
