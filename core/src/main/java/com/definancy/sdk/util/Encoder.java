package com.definancy.sdk.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Encoder {
	private final static ObjectMapper jsonMapper;
	private final static ObjectMapper basicMapper = new ObjectMapper();

	private static final char BASE32_PAD_CHAR = '=';

	private static final Pattern BASE32_ALLOWED =
			Pattern.compile("^[A-Z2-7=]*$");
	private static final Pattern BASE64URL_ALLOWED =
			Pattern.compile("^[A-Za-z0-9_=-]*$");

	private static void requireBase32(String s) {
		if (!BASE32_ALLOWED.matcher(s).matches()) {
			throw new IllegalArgumentException(
					"Invalid Base32 input: contains characters outside alphabet [A-Z2-7=]");
		}
	}

	private static void requireBase64Url(String s) {
		if (!BASE64URL_ALLOWED.matcher(s).matches()) {
			throw new IllegalArgumentException(
					"Invalid Base64url input: contains characters outside alphabet [A-Za-z0-9_=-]");
		}
	}

	static {
		jsonMapper = new ObjectMapper();
		jsonMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
		jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
	}

	/**
	 * Encode an object as json.
	 * @param o object to encode
	 * @return json string
	 * @throws JsonProcessingException error
	 */
	public static String encodeToJson(Object o) throws JsonProcessingException {
		return jsonMapper.writeValueAsString(o);
	}

	/**
	 * Encode an object as json.
	 * @param input json string to decode
	 * @param tClass class to decode the json string into
	 * @return object specified by tClass
	 * @throws JsonProcessingException error
	 */
	public static <T> T decodeFromJson(String input, Class<T> tClass) throws IOException {
		return basicMapper.readerFor(tClass).readValue(input);
	}

	/**
	 * Convenience method for writing bytes as base32
	 * @param bytes input
	 * @return base32 string with stripped whitespace
	 */
	public static String encodeToBase32(byte[] bytes) {
		Base32 codec = new Base32((byte)BASE32_PAD_CHAR);
		String paddedStr = codec.encodeToString(bytes);
		return StringUtils.stripEnd(paddedStr, String.valueOf(BASE32_PAD_CHAR));
	}

	/**
	 * Convenience method for reading base32 back into bytes
	 * @param base32 input string with optional padding.
	 * @return bytes for base32 data
	 */
	public static byte[] decodeFromBase32(String base32) {
		Objects.requireNonNull(base32, "base32 must not be null");
		requireBase32(base32);
		Base32 codec = new Base32((byte)BASE32_PAD_CHAR);
		return codec.decode(base32);
	}

	/**
	 * Encode to base64 string. Does strip padding.
	 * @param bytes input
	 * @return base64 string with appropriate padding
	 */
	public static String encodeToBase64(byte[] bytes) {
		Base64 codec = new Base64(0, null, true);
		return codec.encodeToString(bytes);
	}

	/**
	 * Decode from base64 string.
	 * @param str input
	 * @return decoded bytes
	 */
	public static byte[] decodeFromBase64(String str) {
		Objects.requireNonNull(str, "str must not be null");
		requireBase64Url(str);
		Base64 codec = new Base64(0, null, true);
		return codec.decode(str);
	}
}
