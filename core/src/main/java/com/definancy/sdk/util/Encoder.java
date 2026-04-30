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

public class Encoder {
	private final static ObjectMapper jsonMapper;
	private final static ObjectMapper basicMapper = new ObjectMapper();

	private static final char BASE32_PAD_CHAR = '=';

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
		Base64 codec = new Base64(0, null, true);
		return codec.decode(str);
	}
}
