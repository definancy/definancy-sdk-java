# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

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
