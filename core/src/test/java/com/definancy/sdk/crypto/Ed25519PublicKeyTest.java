package com.definancy.sdk.crypto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Java-specific contract tests for {@link Ed25519PublicKey}.
 *
 * <p>Pins the 0.2.0 fixes:
 * <ul>
 *   <li>{@code Ed25519PublicKey(byte[])} rejects null (was silently
 *       constructing a zero-byte instance).</li>
 *   <li>{@code hashCode} consistent with the value-based {@code equals}
 *       (was inheriting identity-based default — broke
 *       {@code HashMap}/{@code HashSet} keying).</li>
 *   <li>{@code getBytes()} returns a defensive copy (was leaking the
 *       internal mutable array).</li>
 * </ul>
 */
public final class Ed25519PublicKeyTest {

    private static byte[] sampleKey(byte fill) {
        byte[] raw = new byte[Ed25519PublicKey.KEY_LEN_BYTES];
        Arrays.fill(raw, fill);
        return raw;
    }

    // -------------------------------------------------------------------------
    // Null handling
    // -------------------------------------------------------------------------

    @Test
    public void byteConstructor_nullRaw_throwsNPE() {
        assertThrows(NullPointerException.class,
                () -> new Ed25519PublicKey((byte[]) null));
    }

    @Test
    public void publicKeyConstructor_nullPublicKey_throwsNPE() {
        assertThrows(NullPointerException.class,
                () -> new Ed25519PublicKey((java.security.PublicKey) null));
    }

    // -------------------------------------------------------------------------
    // Length validation
    // -------------------------------------------------------------------------

    @Test
    public void byteConstructor_wrongLength_throwsIAE() {
        // 31 bytes — one short of Ed25519's 32-byte public key.
        assertThrows(IllegalArgumentException.class,
                () -> new Ed25519PublicKey(new byte[31]));
    }

    // -------------------------------------------------------------------------
    // equals/hashCode contract — value-based
    // -------------------------------------------------------------------------

    @Test
    public void equals_sameBytes_areEqual() {
        Ed25519PublicKey a = new Ed25519PublicKey(sampleKey((byte) 0x42));
        Ed25519PublicKey b = new Ed25519PublicKey(sampleKey((byte) 0x42));
        assertEquals(a, b, "same bytes must compare equal");
        assertEquals(a, a, "instance must equal itself");
    }

    @Test
    public void equals_differentBytes_areUnequal() {
        Ed25519PublicKey a = new Ed25519PublicKey(sampleKey((byte) 0x01));
        Ed25519PublicKey b = new Ed25519PublicKey(sampleKey((byte) 0x02));
        assertNotEquals(a, b, "different bytes must not be equal");
    }

    @Test
    public void hashCode_sameBytes_sameHashCode() {
        // The equals/hashCode contract: two objects that compare equal
        // MUST hash equal. Before 0.2.0 this was broken — equals was
        // value-based but hashCode inherited Object's identity-based
        // implementation.
        Ed25519PublicKey a = new Ed25519PublicKey(sampleKey((byte) 0x42));
        Ed25519PublicKey b = new Ed25519PublicKey(sampleKey((byte) 0x42));
        assertEquals(a.hashCode(), b.hashCode(),
                "equals/hashCode contract: equal instances must hash equal");
    }

    @Test
    public void hashCode_usableAsHashMapKey() {
        // Smoke test that the equals/hashCode pair actually works in the
        // collections most likely to surface contract violations.
        Ed25519PublicKey key1 = new Ed25519PublicKey(sampleKey((byte) 0x42));
        Ed25519PublicKey lookup = new Ed25519PublicKey(sampleKey((byte) 0x42));

        HashMap<Ed25519PublicKey, String> map = new HashMap<>();
        map.put(key1, "stored");
        assertEquals("stored", map.get(lookup),
                "lookup with a different but value-equal key must find the entry");

        HashSet<Ed25519PublicKey> set = new HashSet<>();
        set.add(key1);
        assertTrue(set.contains(lookup),
                "set membership check must use value equality");
    }

    // -------------------------------------------------------------------------
    // Defensive-copy accessor
    // -------------------------------------------------------------------------

    @Test
    public void getBytes_returnsDefensiveCopy() {
        byte[] original = sampleKey((byte) 0x42);
        Ed25519PublicKey pk = new Ed25519PublicKey(original);

        byte[] first = pk.getBytes();
        byte[] second = pk.getBytes();
        assertNotSame(first, second,
                "getBytes() must not return the same array reference twice");

        // Mutating the returned copy must not affect the instance.
        Arrays.fill(first, (byte) 0x00);
        byte[] refetch = pk.getBytes();
        byte[] expected = sampleKey((byte) 0x42);
        assertTrue(Arrays.equals(expected, refetch),
                "mutation of the returned array must not corrupt internal state");
    }

    @Test
    public void byteConstructor_copiesInputArray() {
        // Defensive on the way in too — mutating the input array post
        // construction must not corrupt the instance. (Pre-0.2.0 the
        // constructor already did System.arraycopy; this test simply
        // pins that behavior so a future "optimisation" can't regress it.)
        byte[] input = sampleKey((byte) 0x42);
        Ed25519PublicKey pk = new Ed25519PublicKey(input);
        Arrays.fill(input, (byte) 0xff);
        byte[] expected = sampleKey((byte) 0x42);
        assertTrue(Arrays.equals(expected, pk.getBytes()),
                "constructor must copy the input array, not retain a reference");
    }
}
