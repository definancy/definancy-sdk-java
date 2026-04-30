# definancy-sdk-java

Java SDK for the [Definancy API](https://github.com/definancy/definancy-api).
Multi-module Maven project.

## Layout

```
.
├── pom.xml          ← parent POM (packaging=pom): coordinates, shared
│                       properties, strict-Java-8-on-jdk9plus profile
├── core/            ← the SDK itself (generated client + hand-written
│                       auth, identity, crypto, amount math)
│   └── pom.xml      ← com.definancy:definancy-sdk-java-core
└── demos/           ← runnable mains exercising the SDK end-to-end;
                        separate jar, not shipped with the core artifact
    └── pom.xml      ← com.definancy:definancy-sdk-java-demos
```

## Build

```bash
mvn -B package          # builds both modules; runs tests; produces shaded core jar
mvn -B package -DskipTests   # faster, same artifacts
```

Java 8 is the production runtime (TIBCO BW5, WebSphere v9.5). The build
also runs on JDK 11/17/21 — on those, the `strict-java8-on-jdk9plus`
profile activates automatically and validates the Java 8 API surface
via `<release>8</release>`. Local JDK 8 builds keep using
`<source>/<target>=1.8` (where `<release>` doesn't exist).

## Run a demo

Demos live in `demos/src/main/java/com/definancy/sdk/demo/`. They are
plain `main()` classes; pick one and run it on the test classpath
(which includes the provided-scope deps the SDK requires at runtime):

```bash
cd demos
mvn -B exec:java -Dexec.mainClass=com.definancy.sdk.demo.APIRegisterDid \
                 -Dexec.classpathScope=test
```

Edit `demos/src/main/java/com/definancy/sdk/demo/Config.java` to point
at the API endpoint and provide credentials before running anything
that talks to a server.

## Consumer install

The `core` jar (and shaded variant) does NOT bundle Jersey, JAX-RS,
HK2, javassist, mimepull, Apache HttpClient 4, or BouncyCastle.
Deployment platforms (BW5, WebSphere v9.5) ship those alongside the
SDK as a shared library. The version-pinned consumer install list
lives in the factory: `../OVERRIDES.md`
(section "Provided-scope dependencies").

## Regenerate

The `core/src/main/java/com/definancy/{api,model,*}` tree, plus
`core/api/openapi.yaml` and `core/docs/`, are generated from the
OpenAPI spec by `openapi-generator` (jersey2 library, v7.20.0). Don't
hand-edit those — they get clobbered on every regenerate.

Hand-written code lives at `core/src/main/java/com/definancy/sdk/`
(auth, crypto, util, identity types) and stays put across regens.
The list of files protected from regeneration (build files, CI
workflow, etc.) is in `core/.openapi-generator-ignore`.

Regen happens from the factory:

```bash
task gen:java   # from the factory root (../../..)
```
