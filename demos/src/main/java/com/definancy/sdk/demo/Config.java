package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.sdk.DID;
import com.definancy.sdk.auth.AuthRequestFilter;
import com.definancy.sdk.auth.impl.LocalAuthProvider;
import com.definancy.sdk.crypto.KeyPair;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.logging.LoggingFeature;

public class Config {
    public static String network = "dev";
    public static String audience = "https://dev.definancy.com";
    public static String secret = "qHWHe6jLnx7gD-CZSe3X2UwgC-ISFOVy4rfFWxxJXX0";
    public static String vaultId = "sdkDemoVault";

    public static KeyPair getKeyPair() throws Exception {
        return KeyPair.generateKeyPairFromSecret(secret);
    }

    public static DID getDID() throws Exception {
        KeyPair keyPair = getKeyPair();
        return keyPair.publicKey().computeDID(Config.network);
    }

    public static ApiClient GetApiClient() throws Exception {
        String audience = Config.audience;

        KeyPair keyPair = getKeyPair();
        DID did = getDID();

        LocalAuthProvider signer = new LocalAuthProvider(did, keyPair);
        AuthRequestFilter authInterceptor = new AuthRequestFilter(signer);

        LoggingFeature loggingFilter = new LoggingFeature(
                Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                Level.INFO,
                LoggingFeature.Verbosity.PAYLOAD_ANY,
                1024 // max entity size to log
        );

        Client httpClient = JerseyClientBuilder.newBuilder()
                .register(authInterceptor)
                .register(loggingFilter)
                .build();

        ApiClient apiClient = new ApiClient();
        apiClient.setHttpClient(httpClient);
        apiClient.setBasePath(audience);

        return apiClient;
    }
}
