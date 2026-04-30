package com.definancy.sdk.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Digester {
	public static byte[] digest(byte[] data) {
		return sha512_256(data);
	}

	public static byte[] sha512_256(byte[] data) {
		return DigestUtils.sha512_256(data);
	}
	public static byte[] sha256(byte[] data) {
		return DigestUtils.sha256(data);
	}
}
