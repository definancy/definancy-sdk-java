package com.definancy.sdk.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DPoPClaims extends Claims {
	@JsonProperty("jti")
	public String id;
	@JsonProperty("htm")
	public String method;
	@JsonProperty("htu")
	public String uri;
	@JsonProperty("iat")
	public Long issuedAt;
	@JsonProperty("exp")
	public Long expiresAt;
	@JsonProperty("bsh")
	public String bodyHash;
}
