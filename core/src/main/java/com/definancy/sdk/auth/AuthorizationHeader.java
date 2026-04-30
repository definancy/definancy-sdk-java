package com.definancy.sdk.auth;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorizationHeader extends Header {
	public AuthorizationHeader() {
		super("JWT");
	}
}
