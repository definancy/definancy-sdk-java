# definancy-sdk-java

Java SDK for the [Definancy API](https://github.com/definancy/definancy-spec).
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

The `core` jar (and shaded variant) does **not** bundle the following —
they're declared `<scope>provided</scope>` in [`core/pom.xml`](core/pom.xml)
so the consumer (or its container) supplies them at runtime:

- Jersey 2 (`org.glassfish.jersey.core:jersey-client` + `jersey-common`)
- JAX-RS API (`jakarta.ws.rs:jakarta.ws.rs-api`)
- HK2 (`org.glassfish.hk2:hk2-locator`)
- javassist
- mimepull
- Apache HttpClient 4 (`org.apache.httpcomponents:httpclient`)
- BouncyCastle (`org.bouncycastle:bcprov-jdk18on`)

The reference deployment platforms (TIBCO BusinessWorks 5, IBM WebSphere
v9.5) ship the Jersey / JAX-RS / HK2 stack as part of the platform.
Standalone deployments must pull the same versions in compile scope —
see `core/pom.xml`'s `<dependencies>` block for the pinned versions.

## Regeneration

The `core/src/main/java/com/definancy/{api,model,*}` tree (plus
`core/api/openapi.yaml` and `core/docs/`) is auto-generated from the
upstream OpenAPI spec
([`definancy-spec`](https://github.com/definancy/definancy-spec)) by
`openapi-generator` (jersey2 library, v7.20.0). **This repo ships those
artifacts ready to build** — regeneration is performed in the upstream
release pipeline, not here. Don't hand-edit the generated files; they'd
be overwritten on the next regeneration.

Hand-written code lives at `core/src/main/java/com/definancy/sdk/`
(auth, crypto, util, identity types) and stays put across regens. The
file-by-file regeneration policy is in `core/.openapi-generator-ignore`.
