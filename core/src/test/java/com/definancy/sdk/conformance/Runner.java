package com.definancy.sdk.conformance;

import com.definancy.sdk.AmountMath;
import com.definancy.sdk.DID;
import com.definancy.sdk.ID;
import com.definancy.sdk.auth.Authorization;
import com.definancy.sdk.auth.DPoP;
import com.definancy.sdk.auth.Jwk;
import com.definancy.sdk.crypto.Ed25519PublicKey;
import com.definancy.sdk.crypto.KeyPair;
import com.definancy.sdk.util.Digester;
import com.definancy.sdk.util.Encoder;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.LoaderOptions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Cross-language conformance runner — Java SDK.
 *
 * Loads YAML test vectors from the spec submodule's `spec/conformance/vectors/`
 * tree and asserts that the SDK produces byte-identical outputs to the
 * canonical reference values. Reports `name:label PASS|FAIL` per case
 * and exits non-zero on any failure.
 *
 * Usage:
 *   mvn test-compile -q
 *   java -cp target/classes:target/test-classes:target/lib/* \
 *        com.definancy.sdk.conformance.Runner
 *
 * Vectors live at <factory>/spec/conformance/vectors/<domain>/<scenario>.yaml.
 * The runner walks up from this submodule's location to find them.
 *
 * See <factory>/spec/conformance/README.md for the schema and runner contract.
 * Vectors encode CORRECT behavior; failing cases here are SDK bugs to
 * fix, not vectors to relax.
 */
public final class Runner {

    private static int passed = 0;
    private static int failed = 0;
    private static int skipped = 0;

    public static void main(String[] args) throws Exception {
        Path vectorsRoot = locateVectorsRoot();
        System.out.println("Loading vectors from: " + vectorsRoot);
        System.out.println();

        runBase32(vectorsRoot);
        runBase64Url(vectorsRoot);
        runSha256(vectorsRoot);
        runSha512_256(vectorsRoot);
        runEd25519(vectorsRoot);
        runJwkThumbprint(vectorsRoot);
        runIdChecksum(vectorsRoot);
        runDidParse(vectorsRoot);
        runJwtCanonical(vectorsRoot);
        runAuthorizationJwt(vectorsRoot);
        runDpopProof(vectorsRoot);
        runDpopBodyHash(vectorsRoot);
        runAmountMath(vectorsRoot);

        System.out.println();
        System.out.println("Total: " + passed + " PASS, " + failed + " FAIL, " + skipped + " SKIP");
        System.exit(failed > 0 ? 1 : 0);
    }

    // -------------------------------------------------------------------------
    // Reporting helpers
    // -------------------------------------------------------------------------

    private static void report(String name, String label, boolean ok, String diag) {
        String status = ok ? "PASS" : "FAIL";
        String suffix = (diag == null || diag.isEmpty()) ? "" : " " + diag;
        System.out.println(name + ":" + label + " " + status + suffix);
        if (ok) {
            passed++;
        } else {
            failed++;
        }
    }

    private static void skip(String name, String label, String reason) {
        System.out.println(name + ":" + label + " SKIP " + reason);
        skipped++;
    }

    // -------------------------------------------------------------------------
    // Hex / bytes helpers (byte[] is the canonical wire shape)
    // -------------------------------------------------------------------------

    private static byte[] hexToBytes(String hex) {
        if (hex == null || hex.isEmpty()) {
            return new byte[0];
        }
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // Vector loading
    // -------------------------------------------------------------------------

    /**
     * Walk up from this submodule's location to find the factory's
     * `spec/conformance/vectors/` directory (the spec submodule hosts the
     * conformance suite — see `<factory>/spec/conformance/README.md`).
     * Layout assumption matches `<factory>/languages/java/sdk/`.
     */
    private static Path locateVectorsRoot() {
        Path cwd = Paths.get("").toAbsolutePath();
        // Try cwd-relative first (common when running from the SDK root)
        Path candidate = cwd.resolve("../../../spec/conformance/vectors").normalize();
        if (Files.isDirectory(candidate)) {
            return candidate;
        }
        // Fall back to walking parents until we find spec/conformance/vectors/
        Path p = cwd;
        for (int i = 0; i < 8 && p != null; i++) {
            Path c = p.resolve("spec/conformance/vectors");
            if (Files.isDirectory(c)) {
                return c;
            }
            p = p.getParent();
        }
        throw new IllegalStateException("Cannot locate spec/conformance/vectors/ from " + cwd);
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> loadVectors(Path root, String domain) throws IOException {
        Path domainDir = root.resolve(domain);
        List<Map<String, Object>> result = new ArrayList<>();
        if (!Files.isDirectory(domainDir)) {
            return result;
        }
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(domainDir, "*.yaml")) {
            Yaml yaml = new Yaml(new LoaderOptions());
            for (Path file : stream) {
                String text = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
                result.add((Map<String, Object>) yaml.load(text));
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> casesOf(Map<String, Object> vec) {
        return (List<Map<String, Object>>) vec.get("cases");
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> mapOf(Map<String, Object> caseObj, String key) {
        return (Map<String, Object>) caseObj.get(key);
    }

    private static String str(Map<String, Object> m, String k) {
        Object v = m.get(k);
        return v == null ? "" : v.toString();
    }

    // -------------------------------------------------------------------------
    // Domain runners
    // -------------------------------------------------------------------------

    private static void runBase32(Path root) throws IOException {
        for (Map<String, Object> vec : loadVectors(root, "base32")) {
            String name = (String) vec.get("name");
            for (Map<String, Object> c : casesOf(vec)) {
                String label = (String) c.get("label");
                Map<String, Object> in = mapOf(c, "input");
                Map<String, Object> out = mapOf(c, "output");

                byte[] inputBytes = hexToBytes(str(in, "bytes_hex"));
                String expected = str(out, "encoded");

                String actual = Encoder.encodeToBase32(inputBytes);
                report(name, label + "/encode", actual.equals(expected),
                        actual.equals(expected) ? "" : "expected=\"" + expected + "\" actual=\"" + actual + "\"");

                try {
                    byte[] decoded = Encoder.decodeFromBase32(expected);
                    String decodedHex = bytesToHex(decoded);
                    String expectedHex = str(in, "bytes_hex");
                    report(name, label + "/decode", decodedHex.equals(expectedHex),
                            decodedHex.equals(expectedHex)
                                    ? ""
                                    : "expected_hex=\"" + expectedHex + "\" actual_hex=\"" + decodedHex + "\"");
                } catch (Exception e) {
                    report(name, label + "/decode", false, "error=\"" + e.getMessage() + "\"");
                }
            }
        }
    }

    private static void runBase64Url(Path root) throws IOException {
        for (Map<String, Object> vec : loadVectors(root, "base64url")) {
            String name = (String) vec.get("name");
            for (Map<String, Object> c : casesOf(vec)) {
                String label = (String) c.get("label");
                Map<String, Object> in = mapOf(c, "input");
                Map<String, Object> out = mapOf(c, "output");

                byte[] inputBytes = hexToBytes(str(in, "bytes_hex"));
                String expected = str(out, "encoded");

                String actual = Encoder.encodeToBase64(inputBytes);
                report(name, label + "/encode", actual.equals(expected),
                        actual.equals(expected) ? "" : "expected=\"" + expected + "\" actual=\"" + actual + "\"");

                try {
                    byte[] decoded = Encoder.decodeFromBase64(expected);
                    String decodedHex = bytesToHex(decoded);
                    String expectedHex = str(in, "bytes_hex");
                    report(name, label + "/decode", decodedHex.equals(expectedHex),
                            decodedHex.equals(expectedHex)
                                    ? ""
                                    : "expected_hex=\"" + expectedHex + "\" actual_hex=\"" + decodedHex + "\"");
                } catch (Exception e) {
                    report(name, label + "/decode", false, "error=\"" + e.getMessage() + "\"");
                }
            }
        }
    }

    private static void runSha256(Path root) throws IOException {
        for (Map<String, Object> vec : loadVectors(root, "sha256")) {
            String name = (String) vec.get("name");
            for (Map<String, Object> c : casesOf(vec)) {
                String label = (String) c.get("label");
                Map<String, Object> in = mapOf(c, "input");
                Map<String, Object> out = mapOf(c, "output");

                String expected = str(out, "digest_hex");
                try {
                    String actual = bytesToHex(Digester.sha256(hexToBytes(str(in, "bytes_hex"))));
                    report(name, label, actual.equals(expected),
                            actual.equals(expected) ? "" : "expected=\"" + expected + "\" actual=\"" + actual + "\"");
                } catch (Exception e) {
                    report(name, label, false, "error=\"" + e.getMessage() + "\"");
                }
            }
        }
    }

    private static void runSha512_256(Path root) throws IOException {
        for (Map<String, Object> vec : loadVectors(root, "sha512_256")) {
            String name = (String) vec.get("name");
            for (Map<String, Object> c : casesOf(vec)) {
                String label = (String) c.get("label");
                Map<String, Object> in = mapOf(c, "input");
                Map<String, Object> out = mapOf(c, "output");

                String expected = str(out, "digest_hex");
                try {
                    String actual = bytesToHex(Digester.sha512_256(hexToBytes(str(in, "bytes_hex"))));
                    report(name, label, actual.equals(expected),
                            actual.equals(expected) ? "" : "expected=\"" + expected + "\" actual=\"" + actual + "\"");
                } catch (Exception e) {
                    report(name, label, false, "error=\"" + e.getMessage() + "\"");
                }
            }
        }
    }

    /**
     * Ed25519 — derive_pubkey + sign per case. Sign is skipped when the
     * message is not valid UTF-8, since the SDK's
     * {@code KeyPair.sign(String)} can only sign string messages and
     * arbitrary bytes don't round-trip through that API. Same constraint
     * as the TS runner.
     */
    private static void runEd25519(Path root) throws IOException {
        for (Map<String, Object> vec : loadVectors(root, "ed25519")) {
            String name = (String) vec.get("name");
            for (Map<String, Object> c : casesOf(vec)) {
                String label = (String) c.get("label");
                Map<String, Object> in = mapOf(c, "input");
                Map<String, Object> out = mapOf(c, "output");

                String seedHex = str(in, "seed_hex");
                String messageHex = str(in, "message_hex");
                String expectedPubHex = str(out, "public_key_hex");
                String expectedSigHex = str(out, "signature_hex");

                KeyPair kp;
                try {
                    String seedB64Url = Encoder.encodeToBase64(hexToBytes(seedHex));
                    kp = KeyPair.generateKeyPairFromSecret(seedB64Url);
                } catch (Exception e) {
                    report(name, label + "/derive_pubkey", false, "error=\"" + e.getMessage() + "\"");
                    continue;
                }

                String actualPubHex = bytesToHex(kp.publicKey().getBytes());
                report(name, label + "/derive_pubkey", actualPubHex.equals(expectedPubHex),
                        actualPubHex.equals(expectedPubHex)
                                ? ""
                                : "expected=\"" + expectedPubHex + "\" actual=\"" + actualPubHex + "\"");

                // Sign raw bytes — the SDK's sign(byte[]) overload accepts
                // arbitrary message bytes that don't round-trip through UTF-8.
                try {
                    byte[] messageBytes = hexToBytes(messageHex);
                    String sigB64Url = kp.sign(messageBytes);
                    byte[] sigBytes = Encoder.decodeFromBase64(sigB64Url);
                    String actualSigHex = bytesToHex(sigBytes);
                    report(name, label + "/sign", actualSigHex.equals(expectedSigHex),
                            actualSigHex.equals(expectedSigHex)
                                    ? ""
                                    : "expected=\"" + expectedSigHex + "\" actual=\"" + actualSigHex + "\"");
                } catch (Exception e) {
                    report(name, label + "/sign", false, "error=\"" + e.getMessage() + "\"");
                }
            }
        }
    }

    private static void runJwkThumbprint(Path root) throws IOException {
        for (Map<String, Object> vec : loadVectors(root, "jwk_thumbprint")) {
            String name = (String) vec.get("name");
            for (Map<String, Object> c : casesOf(vec)) {
                String label = (String) c.get("label");
                Map<String, Object> in = mapOf(c, "input");
                Map<String, Object> out = mapOf(c, "output");

                try {
                    Ed25519PublicKey pk = new Ed25519PublicKey(hexToBytes(str(in, "public_key_hex")));
                    Jwk jwk = pk.jwk();

                    String expectedX = str(out, "x_b64url");
                    report(name, label + "/x", jwk.x.equals(expectedX),
                            jwk.x.equals(expectedX)
                                    ? ""
                                    : "expected=\"" + expectedX + "\" actual=\"" + jwk.x + "\"");

                    String expectedTp = str(out, "thumbprint_b64url");
                    String actualTp = jwk.thumbprint();
                    report(name, label + "/thumbprint", actualTp.equals(expectedTp),
                            actualTp.equals(expectedTp)
                                    ? ""
                                    : "expected=\"" + expectedTp + "\" actual=\"" + actualTp + "\"");
                } catch (Exception e) {
                    report(name, label, false, "error=\"" + e.getMessage() + "\"");
                }
            }
        }
    }

    private static void runIdChecksum(Path root) throws IOException {
        for (Map<String, Object> vec : loadVectors(root, "id_checksum")) {
            String name = (String) vec.get("name");
            for (Map<String, Object> c : casesOf(vec)) {
                String label = (String) c.get("label");
                Map<String, Object> in = mapOf(c, "input");
                Map<String, Object> out = mapOf(c, "output");

                try {
                    Ed25519PublicKey pk = new Ed25519PublicKey(hexToBytes(str(in, "public_key_hex")));
                    ID id = new ID(pk);
                    String actual = id.encodeAsString();
                    String expected = str(out, "id_string");
                    report(name, label, actual.equals(expected),
                            actual.equals(expected)
                                    ? ""
                                    : "expected=\"" + expected + "\" actual=\"" + actual + "\"");
                } catch (Exception e) {
                    report(name, label, false, "error=\"" + e.getMessage() + "\"");
                }
            }
        }
    }

    private static void runDidParse(Path root) throws IOException {
        for (Map<String, Object> vec : loadVectors(root, "did_parse")) {
            String name = (String) vec.get("name");
            for (Map<String, Object> c : casesOf(vec)) {
                String label = (String) c.get("label");
                Map<String, Object> in = mapOf(c, "input");
                Map<String, Object> out = mapOf(c, "output");

                try {
                    String network = str(in, "network");
                    String idStr = str(in, "id_string");
                    DID did = new DID(network, new ID(idStr));
                    String actual = did.toString();
                    String expected = str(out, "did");
                    report(name, label, actual.equals(expected),
                            actual.equals(expected)
                                    ? ""
                                    : "expected=\"" + expected + "\" actual=\"" + actual + "\"");
                } catch (Exception e) {
                    report(name, label, false, "error=\"" + e.getMessage() + "\"");
                }
            }
        }
    }

    /**
     * JWT canonical serialization. Tests the canonicalization layer that
     * `Header.encodeB64()` and `Claims.encodeB64()` rely on (Jackson with
     * SORT_PROPERTIES_ALPHABETICALLY, recursive). Bypasses the typed
     * Header/Claims classes — the vectors supply arbitrary YAML maps and
     * we feed them through `Encoder.encodeToJson` which uses the same
     * shared ObjectMapper.
     *
     * Catches: divergences in alphabetical key ordering, whitespace
     * handling, or recursive nested-object treatment. Java should pass
     * (Jackson recursive sort is correct); the corresponding TS path
     * fails today on the documented `sortedJsonStringify` bug.
     */
    private static void runJwtCanonical(Path root) throws IOException {
        for (Map<String, Object> vec : loadVectors(root, "jwt_canonical")) {
            String name = (String) vec.get("name");
            for (Map<String, Object> c : casesOf(vec)) {
                String label = (String) c.get("label");
                Map<String, Object> in = mapOf(c, "input");
                Map<String, Object> out = mapOf(c, "output");

                @SuppressWarnings("unchecked")
                Map<String, Object> header = (Map<String, Object>) in.get("header");
                @SuppressWarnings("unchecked")
                Map<String, Object> claims = (Map<String, Object>) in.get("claims");
                String expected = str(out, "unsigned_jwt");

                try {
                    String headerJson = Encoder.encodeToJson(header);
                    String claimsJson = Encoder.encodeToJson(claims);
                    String headerB64 = Encoder.encodeToBase64(headerJson.getBytes(StandardCharsets.UTF_8));
                    String claimsB64 = Encoder.encodeToBase64(claimsJson.getBytes(StandardCharsets.UTF_8));
                    String actual = headerB64 + "." + claimsB64;

                    boolean ok = actual.equals(expected);
                    String diag = "";
                    if (!ok) {
                        String expectedHeader = str(out, "canonical_header_utf8");
                        String expectedClaims = str(out, "canonical_claims_utf8");
                        if (!headerJson.equals(expectedHeader)) {
                            diag += "header_actual_utf8=\"" + headerJson + "\" ";
                        }
                        if (!claimsJson.equals(expectedClaims)) {
                            diag += "claims_actual_utf8=\"" + claimsJson + "\"";
                        }
                    }
                    report(name, label, ok, diag.trim());
                } catch (Exception e) {
                    report(name, label, false, "error=\"" + e.getMessage() + "\"");
                }
            }
        }
    }

    /**
     * Authorization JWT — end-to-end mint pipeline. From a fixed seed,
     * derive keypair → DefinancyId → DefinancyDid → JWK thumbprint, then
     * mint a JWT with pinned iat/exp, sign it, and assemble the compact
     * form. Intermediate values (did, jwk_thumbprint) are checked first.
     */
    private static void runAuthorizationJwt(Path root) throws IOException {
        for (Map<String, Object> vec : loadVectors(root, "authorization_jwt")) {
            String name = (String) vec.get("name");
            for (Map<String, Object> c : casesOf(vec)) {
                String label = (String) c.get("label");
                Map<String, Object> in = mapOf(c, "input");
                Map<String, Object> out = mapOf(c, "output");

                String seedHex = str(in, "seed_hex");
                String network = str(in, "network");
                String audience = str(in, "audience");
                long iat = ((Number) in.get("iat")).longValue();
                long exp = ((Number) in.get("exp")).longValue();

                String expectedDid = str(out, "did");
                String expectedThumbprint = str(out, "jwk_thumbprint_b64url");
                String expectedJwt = str(out, "jwt_compact");

                try {
                    String seedB64Url = Encoder.encodeToBase64(hexToBytes(seedHex));
                    KeyPair kp = KeyPair.generateKeyPairFromSecret(seedB64Url);

                    ID id = new ID(kp.publicKey());
                    DID didObj = new DID(network, id);

                    String actualDid = didObj.toString();
                    report(name, label + "/did", actualDid.equals(expectedDid),
                            actualDid.equals(expectedDid)
                                    ? ""
                                    : "expected=\"" + expectedDid + "\" actual=\"" + actualDid + "\"");

                    Jwk jwk = kp.jwk();
                    String actualTp = jwk.thumbprint();
                    report(name, label + "/jwk_thumbprint", actualTp.equals(expectedThumbprint),
                            actualTp.equals(expectedThumbprint)
                                    ? ""
                                    : "expected=\"" + expectedThumbprint + "\" actual=\"" + actualTp + "\"");

                    Authorization authorization = new Authorization(didObj, audience, actualTp, iat, exp);
                    authorization.setSignature(kp.sign(authorization.encodeB64()));
                    String actualJwt = authorization.encodeB64();

                    report(name, label + "/jwt_compact", actualJwt.equals(expectedJwt),
                            actualJwt.equals(expectedJwt)
                                    ? ""
                                    : "expected=\"" + expectedJwt + "\" actual=\"" + actualJwt + "\"");
                } catch (Exception e) {
                    report(name, label, false, "error=\"" + e.getMessage() + "\"");
                }
            }
        }
    }

    /**
     * DPoP proof JWT — end-to-end mint pipeline. From a fixed seed,
     * derive keypair → JWK, then mint a DPoP proof with pinned
     * jti/iat/exp/method/htu (body=null), sign, assemble compact form.
     * Body-hash testing is in a separate `dpop_body_hash` vector.
     */
    private static void runDpopProof(Path root) throws IOException {
        for (Map<String, Object> vec : loadVectors(root, "dpop_proof")) {
            String name = (String) vec.get("name");
            for (Map<String, Object> c : casesOf(vec)) {
                String label = (String) c.get("label");
                Map<String, Object> in = mapOf(c, "input");
                Map<String, Object> out = mapOf(c, "output");

                String seedHex = str(in, "seed_hex");
                String jti = str(in, "jti");
                String method = str(in, "method");
                String htu = str(in, "htu");
                long iat = ((Number) in.get("iat")).longValue();
                long exp = ((Number) in.get("exp")).longValue();

                String expectedJwk_x = str(out, "jwk_x_b64url");
                String expectedJwt = str(out, "jwt_compact");

                try {
                    String seedB64Url = Encoder.encodeToBase64(hexToBytes(seedHex));
                    KeyPair kp = KeyPair.generateKeyPairFromSecret(seedB64Url);
                    Jwk jwk = kp.jwk();

                    report(name, label + "/jwk_x", jwk.x.equals(expectedJwk_x),
                            jwk.x.equals(expectedJwk_x)
                                    ? ""
                                    : "expected=\"" + expectedJwk_x + "\" actual=\"" + jwk.x + "\"");

                    DPoP dpop = new DPoP(jti, method, htu, (byte[]) null, jwk, iat, exp);
                    dpop.setSignature(kp.sign(dpop.encodeB64()));
                    String actualJwt = dpop.encodeB64();

                    report(name, label + "/jwt_compact", actualJwt.equals(expectedJwt),
                            actualJwt.equals(expectedJwt)
                                    ? ""
                                    : "expected=\"" + expectedJwt + "\" actual=\"" + actualJwt + "\"");
                } catch (Exception e) {
                    report(name, label, false, "error=\"" + e.getMessage() + "\"");
                }
            }
        }
    }

    /**
     * DPoP proof with a non-null body. Two sub-checks per case: the
     * standalone {@code bsh} value (sha512_256 → base64url, computed
     * directly), and the full JWT compact (composes bsh into claims,
     * signs, encodes).
     *
     * <p>This is the vector that catches the legacy
     * {@code body.toString()} bug — pre-fix, the DPoP path took a
     * String body and {@code LocalAuthProvider} passed
     * {@code entity.toString()} on the JAX-RS POJO, hashing JVM
     * identity strings instead of wire bytes. Post-fix, the API takes
     * {@code byte[]} and the runner hands the wire bytes directly.
     */
    private static void runDpopBodyHash(Path root) throws IOException {
        for (Map<String, Object> vec : loadVectors(root, "dpop_body_hash")) {
            String name = (String) vec.get("name");
            for (Map<String, Object> c : casesOf(vec)) {
                String label = (String) c.get("label");
                Map<String, Object> in = mapOf(c, "input");
                Map<String, Object> out = mapOf(c, "output");

                String seedHex = str(in, "seed_hex");
                String jti = str(in, "jti");
                String method = str(in, "method");
                String htu = str(in, "htu");
                String bodyBytesHex = str(in, "body_bytes_hex");
                long iat = ((Number) in.get("iat")).longValue();
                long exp = ((Number) in.get("exp")).longValue();

                String expectedBsh = str(out, "bsh_b64url");
                String expectedJwt = str(out, "jwt_compact");

                try {
                    byte[] bodyBytes = hexToBytes(bodyBytesHex);

                    // Independent bsh check
                    String bsh = Encoder.encodeToBase64(Digester.digest(bodyBytes));
                    report(name, label + "/bsh", bsh.equals(expectedBsh),
                            bsh.equals(expectedBsh)
                                    ? ""
                                    : "expected=\"" + expectedBsh + "\" actual=\"" + bsh + "\"");

                    String seedB64Url = Encoder.encodeToBase64(hexToBytes(seedHex));
                    KeyPair kp = KeyPair.generateKeyPairFromSecret(seedB64Url);
                    Jwk jwk = kp.jwk();

                    DPoP dpop = new DPoP(jti, method, htu, bodyBytes, jwk, iat, exp);
                    dpop.setSignature(kp.sign(dpop.encodeB64()));
                    String actualJwt = dpop.encodeB64();

                    report(name, label + "/jwt_compact", actualJwt.equals(expectedJwt),
                            actualJwt.equals(expectedJwt)
                                    ? ""
                                    : "expected=\"" + expectedJwt + "\" actual=\"" + actualJwt + "\"");
                } catch (Exception e) {
                    report(name, label, false, "error=\"" + e.getMessage() + "\"");
                }
            }
        }
    }

    /**
     * Pure-string conversion between human decimal value and raw integer
     * (smallest-unit). Two ops per case: valueToRaw, rawToValue.
     */
    private static void runAmountMath(Path root) throws IOException {
        for (Map<String, Object> vec : loadVectors(root, "amount_math")) {
            String name = (String) vec.get("name");
            for (Map<String, Object> c : casesOf(vec)) {
                String label = (String) c.get("label");
                Map<String, Object> in = mapOf(c, "input");

                String value = str(in, "value");
                String raw = str(in, "raw");
                int decimals = ((Number) in.get("decimals")).intValue();

                try {
                    String actualRaw = AmountMath.valueToRaw(value, decimals);
                    report(name, label + "/valueToRaw", actualRaw.equals(raw),
                            actualRaw.equals(raw)
                                    ? ""
                                    : "expected=\"" + raw + "\" actual=\"" + actualRaw + "\"");
                } catch (Exception e) {
                    report(name, label + "/valueToRaw", false, "error=\"" + e.getMessage() + "\"");
                }

                try {
                    String actualValue = AmountMath.rawToValue(raw, decimals);
                    report(name, label + "/rawToValue", actualValue.equals(value),
                            actualValue.equals(value)
                                    ? ""
                                    : "expected=\"" + value + "\" actual=\"" + actualValue + "\"");
                } catch (Exception e) {
                    report(name, label + "/rawToValue", false, "error=\"" + e.getMessage() + "\"");
                }
            }
        }
    }

    private Runner() {}
}
