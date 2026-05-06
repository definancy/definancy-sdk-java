# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- **`com.definancy.sdk.Client`** — convenience builder that returns a
  fully-configured `ApiClient` with the **Apache HttpClient connector**
  pre-wired (Jersey's default `HttpURLConnection` connector rejects
  `PATCH`, which every `config*` endpoint of the API uses). Removes a
  recurring footgun for partner integrations that touched any
  configuration endpoint. The helper also installs the `AuthRequestFilter`
  for a supplied `AuthProvider` and accepts a third `Object` slot for
  caller-supplied Jersey features (logging, metrics, custom interceptors).
  Available as a thin layer-2 utility — not the eventual layer-3 facade
  with resource-grouped accessors (still deferred).

### Fixed
- **DPoP `htu` claim** now follows RFC 9449 §4.2 (scheme + authority + path,
  no query, no fragment), replacing the previous audience-only value
  (scheme + host + port) that diverged from spec. Servers strictly
  validating `htu` against the request URI will now accept proofs that
  previously would have been rejected.

### Demos
- **`Config.java`**: `network` default is now `"stub"` (matches the
  deployed `stub.definancy.com` daemon's `Environment: stub`); switched to
  `com.definancy.sdk.Client` for `ApiClient` construction so the demos
  inherit the Apache connector.
- **`APIRegisterDid`**: retargeted from the retired `ExperimentalApi.registerDid`
  (`PUT /v1/did/{id}`, no longer in the spec) to `AuthApi.registerAuth`
  (`PUT /v1/auth/{definancyId}`, the current `RegisterAuth` operation).
- **`APISetVault`**: rewritten to mirror the gateway-demo bootstrap pattern
  — discover all networks/assets/contracts, enable disabled ones, then
  set the vault to include every available contract. Self-contained and
  idempotent against the stub. Replaces the previous hardcoded contract
  list whose network IDs (`bitcoin`, `ethereum`, `algorand`) had been
  retired in favour of the chain-suffixed forms (`bitcoin-testnet4`,
  `ethereum-11155111`, `algorand-testnet`, etc.).

### Changed
- **Conformance Runner** loads vectors from `spec/conformance/vectors/`
  (the conformance suite consolidated into the spec submodule —
  `definancy-spec` repo).

### Documentation
- README upstream link updated `definancy-api` → `definancy-spec` (the
  spec repo was renamed; the old URL still redirects).
- README "Consumer install" section now lists provided-scope dependencies
  inline and points at `core/pom.xml` instead of an upstream tooling file.
- README "Regeneration" section no longer references upstream task
  recipes — clarifies that this repo ships the generated artifacts
  ready to build and that regeneration happens upstream.
- `CLAUDE.md` rewritten for standalone-clone consumers — removed
  references to upstream tooling and replaced relative cross-repo paths
  with public GitHub URLs. Conformance section explains how to clone
  `definancy-spec` as a sibling so the Runner can locate the vectors.

## [0.3.0] - 2026-05-02

No code changes since 0.2.0. Version bumped in lockstep with the factory tag
(`factory-v0.3.0`) per the release contract; the published artifact is
byte-identical to 0.2.0. The 0.3.0 cycle is a docs-only release at the
factory level (the hosted docs site landed at `factory-v0.3.0`).

## [0.2.0] - 2026-05-01

Cleanup MINOR — fixes the 5 latent defects flagged by the 0.1.0 release.
Same template: tighten throwing semantics on previously-silent-failure
input shapes. Ships as MINOR per the strict SemVer policy
("PATCH never breaks; MINOR may break").

### Changed (BREAKING)
- `Ed25519PublicKey(byte[] raw)` now throws `NullPointerException` on null
  input. Previously silently constructed an instance with a null backing
  array, with subsequent operations failing in surprising ways.
- `Ed25519PublicKey.hashCode()` is now overridden (uses `Arrays.hashCode`
  on the byte payload, consistent with the existing value-based `equals`).
  Previously the equals/hashCode contract was violated — `HashMap` /
  `HashSet` keyed by `Ed25519PublicKey` would not retrieve correctly when
  the key was reconstructed from the same bytes.
- `Ed25519PublicKey.getBytes()` now returns a defensive copy. Previously
  returned the internal mutable array; callers that mutated it would
  corrupt the instance.
- `AmountMath.rawToValue(null)` now throws `NullPointerException`.
- `AmountMath.rawToValue("")` now throws `IllegalArgumentException`.
  Previously returned `"0"`. Brings `rawToValue` consistent with the
  0.1.0 `valueToRaw` tightening.
- `AmountMath.valueToRaw("-")` (and any input that has a sign but no
  digits, e.g. `"-."`) now throws `IllegalArgumentException`. Previously
  returned `"0"`. Same class of "empty fell through" bug fixed in 0.1.0,
  applied to a different input shape.

### Migration
- Same shape as 0.1.0: catch `NullPointerException` / `IllegalArgumentException`
  at any call site that was relying on the silent-failure behaviors above.

## [0.1.0] - 2026-05-01

### Changed (BREAKING)
- `ID` constructor and `fromString` now throw `NullPointerException` on null
  input. Previously silently accepted null (constructor produced a zero-byte
  ID; `fromString` produced a zero-byte ID via the same path).
- `ID.equals` and `ID.hashCode` are now value-based (compare on the 32-byte
  payload). Previously identity-based (default `Object.equals`). Code using
  `ID` instances as `HashMap` / `HashSet` keys will see different behavior:
  two instances constructed from the same bytes are now equal.
- `KeyPair.equals` and `KeyPair.hashCode` overridden. `equals` compares
  public + private key bytes via `MessageDigest.isEqual` (constant-time).
  `hashCode` uses public bytes only (avoids private-key side-channel).
- `Encoder.decodeFromBase32` and `Encoder.decodeFromBase64` now throw
  `IllegalArgumentException` on input containing characters outside their
  respective alphabets. Previously silently dropped invalid characters
  (Apache commons-codec lenient behavior).
- `AmountMath.valueToRaw("")` now throws `IllegalArgumentException`.
  Previously returned `"0"` (empty fell through padding logic).
- `AmountMath.valueToRaw(null)` now throws `NullPointerException` (was
  already broken, tightened for consistency).

### Migration
- Catch `NullPointerException` / `IllegalArgumentException` at any call
  site that was relying on the silent-failure behaviors above.
- Code using `ID` in `IdentityHashMap` (rare) is unaffected; `HashMap`
  / `HashSet` users get the new value-equality semantics.

## [0.0.3] - 2026-05-01

### Added
- JUnit 5 unit tests for `AmountMath`, `ID`, `crypto.KeyPair`, and
  `util.Encoder` covering Java-specific edge cases (null handling,
  BigDecimal scale, malformed inputs, equality semantics) that the
  cross-language conformance Runner doesn't reach.

## [0.0.2] - 2026-05-01

### Added
- `CLAUDE.md` documenting wrapper internals, multi-module layout, and
  Java-specific design patterns (Jersey filter wiring, JDK 8 floor,
  provided-scope discipline).
- `SECURITY.md` with vulnerability reporting policy and supported-versions
  matrix.

## [0.0.1] - 2026-05-01

### Added
- Initial release.
