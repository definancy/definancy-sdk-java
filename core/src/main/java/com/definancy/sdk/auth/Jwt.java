package com.definancy.sdk.auth;

import com.definancy.sdk.util.Encoder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.nio.charset.StandardCharsets;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Jwt {
	@JsonProperty("header")
	private Header header;
	@JsonProperty("claims")
	private Claims claims;
	@JsonProperty("signature")
	private String signature;

    public Jwt() {}

	public Jwt(Header header, Claims claims) {
		this.header = header != null ? header : new Header();
		this.claims = claims != null ? claims : new Claims();
	}

    @JsonIgnore
    public void setHeader(Header header) {
        this.header = header;
    }

    @JsonIgnore
    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    @JsonIgnore
	public String json() throws Exception {
		return Encoder.encodeToJson(this);
	}

	@JsonIgnore
	public byte[] encode() throws Exception {
		return this.encodeB64().getBytes(StandardCharsets.UTF_8);
	}

	@JsonIgnore
	public String encodeB64() throws Exception {
		String h = this.header.encodeB64();
		String c = this.claims.encodeB64();

		if (this.signature == null) {
			return h + "." + c;
		}

		return h + "." + c + "." + this.signature;
	}

	@JsonIgnore
	public void setSignature(String signature) throws Exception {
		this.signature = signature;
	}

	@Override
	public String toString() {
		String result = "<invalid>";

		try {
			result = this.encodeB64();
		} catch (Exception e) {
			/* do nothing */;
		}

		return result;
	}
}
