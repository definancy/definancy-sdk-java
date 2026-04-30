package com.definancy.sdk.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DPoPHeader extends Header {
	@JsonProperty("jwk")
	public Jwk jwk;

	public DPoPHeader() {
		super("DPoP+JWT");
	}

	public DPoPHeader(Jwk jwk) {
		this.jwk = jwk;
	}
}
