package com.definancy.sdk.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorizationClaims extends Claims{
	@JsonProperty("iss")
	public String issuer;
	@JsonProperty("sub")
	public String subject;
	@JsonProperty("aud")
	public String audience;
	@JsonProperty("iat")
	public Long issuedAt;
	@JsonProperty("exp")
	public Long expiresAt;
	@JsonProperty("cnf")
	public ConfirmationClaims confirmation;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	static public class ConfirmationClaims {
		@JsonProperty("jkt")
		public String thumbprint;
	}
}
