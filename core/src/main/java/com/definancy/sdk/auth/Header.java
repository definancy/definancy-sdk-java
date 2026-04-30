package com.definancy.sdk.auth;

import com.definancy.sdk.util.Encoder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.nio.charset.StandardCharsets;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Header {
	@JsonProperty("alg")
	private final String algorithm = "EdDSA";
	@JsonProperty("typ")
	private final String type;

	public Header() {
		this.type = "JWT";
	}

	public Header(final String type) {
		this.type = type;
	}

	@JsonIgnore
	public String json() throws Exception {
		return Encoder.encodeToJson(this);
	}

	@JsonIgnore
	public byte[] encode() throws Exception {
		return this.json().getBytes(StandardCharsets.UTF_8);
	}

	@JsonIgnore
	public String encodeB64() throws Exception {
		return Encoder.encodeToBase64(this.encode());
	}
}
