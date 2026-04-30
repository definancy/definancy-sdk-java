package com.definancy.sdk.auth;

import com.definancy.sdk.util.Encoder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.nio.charset.StandardCharsets;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Claims {
	@JsonIgnore
	public String json() throws JsonProcessingException {
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
