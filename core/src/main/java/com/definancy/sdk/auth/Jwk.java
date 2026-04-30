package com.definancy.sdk.auth;

import com.definancy.sdk.util.Digester;
import com.definancy.sdk.util.Encoder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Jwk {
	@JsonProperty("kty")
	public String kty;
	@JsonProperty("crv")
	public String crv;
	@JsonProperty("x")
	public String x;

	public Jwk() {}

	public Jwk(String kty, String crv, String x) {
		this.kty = kty;
		this.crv = crv;
		this.x = x;
	}

	@JsonIgnore
	public String json() throws Exception {
		return Encoder.encodeToJson(this);
	}

	@JsonIgnore
	public byte[] encode() throws Exception {
		return this.json().getBytes(StandardCharsets.UTF_8);
	}

	public String thumbprint() throws Exception {
		byte[] encoded = this.encode();
        byte[] digested = DigestUtils.sha256(encoded);
		return Encoder.encodeToBase64(digested);
	}
}
