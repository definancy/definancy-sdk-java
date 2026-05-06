package com.definancy.sdk.demo;

import com.definancy.sdk.DID;
import com.definancy.sdk.DefinancyClient;
import com.definancy.sdk.auth.impl.LocalAuthProvider;
import com.definancy.sdk.crypto.KeyPair;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.logging.LoggingFeature;

public class Config {
    public static String network = "stub";
    public static String audience = "https://stub.definancy.com";
    public static String secret = "qHWHe6jLnx7gD-CZSe3X2UwgC-ISFOVy4rfFWxxJXX0";
    public static String vaultId = "sdkDemoVault";

    public static KeyPair getKeyPair() throws Exception {
        return KeyPair.generateKeyPairFromSecret(secret);
    }

    public static DID getDID() throws Exception {
        return getKeyPair().publicKey().computeDID(network);
    }

    public static DefinancyClient newClient() throws Exception {
        DID did = getDID();
        LocalAuthProvider signer = new LocalAuthProvider(did, getKeyPair());

        // Enable Jersey logging for the demo so the request/response signing
        // is visible. Production code typically omits the .filter(...) call.
        LoggingFeature loggingFilter = new LoggingFeature(
                Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                Level.INFO,
                LoggingFeature.Verbosity.PAYLOAD_ANY,
                1024);

        return DefinancyClient.builder()
                .audience(audience)
                .auth(signer)
                .filter(loggingFilter)
                .build();
    }
}
