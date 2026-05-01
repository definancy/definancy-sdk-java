# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

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
