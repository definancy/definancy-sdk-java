package com.definancy.sdk.auth.impl;

import com.definancy.sdk.DID;
import com.definancy.sdk.auth.*;
import com.definancy.sdk.util.Encoder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.ext.Provider;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

@Provider
public class LocalAuthProvider implements AuthProvider {
        private final DID did;
        private final Signer signer;

        public LocalAuthProvider(DID did, Signer signer) throws Exception {
                this.did = did;
                this.signer = signer;
        }

        public Authentication authenticate(ClientRequestContext requestContext) throws Exception {
                URI uri = requestContext.getUri();
                URI audience = new URIBuilder()
                                .setScheme(uri.getScheme())
                                .setHost(uri.getHost())
                                .setPort(uri.getPort())
                                .build();

                // RFC 9449 §4.2: htu is the request URI without query and fragment.
                URI htu = new URIBuilder()
                                .setScheme(uri.getScheme())
                                .setHost(uri.getHost())
                                .setPort(uri.getPort())
                                .setPath(uri.getPath())
                                .build();

                Jwk jwk = signer.jwk();

                // Build and sign Authorization JWT (clock impurity stays at this boundary)
                long iat = Instant.now().getEpochSecond();
                Authorization authorization = new Authorization(
                                did,
                                audience.toString(),
                                jwk.thumbprint(),
                                iat,
                                iat + 60);

                authorization.setSignature(
                                signer.sign(authorization.encodeB64()));

                // Generate DPoP token for this specific request.
                //
                // Body bytes must be the actual wire bytes Jersey will send, not
                // `entity.toString()` (which for typed POJOs returns `Class@hex`
                // — a silent integrity bypass). For non-String entities we
                // serialize via the SDK's shared Jackson mapper, which produces
                // the same canonical JSON Jersey emits over the wire. For
                // String entities (raw JSON, etc.) the bytes are taken directly.
                Object entity = requestContext.getEntity();
                byte[] bodyBytes = null;
                if (entity != null) {
                        String wire = (entity instanceof String)
                                        ? (String) entity
                                        : Encoder.encodeToJson(entity);
                        bodyBytes = wire.getBytes(StandardCharsets.UTF_8);
                }
                String id = UUID.randomUUID().toString();
                long dpopIat = Instant.now().getEpochSecond();

                DPoP dpop = new DPoP(
                                id,
                                requestContext.getMethod(),
                                htu.toString(),
                                bodyBytes,
                                jwk,
                                dpopIat,
                                dpopIat + 60);
                dpop.setSignature(
                                signer.sign(dpop.encodeB64()));

                return new Authentication(authorization, dpop);
        }
}
