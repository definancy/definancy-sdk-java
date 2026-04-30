package com.definancy.sdk.demo;

import com.definancy.sdk.DID;
import com.definancy.sdk.ID;
import com.definancy.sdk.crypto.KeyPair;

public class GenerateDIDFromPublicKey {
    public static void main( String[] args ) throws Exception {
		KeyPair master = KeyPair.generateKeyPair();

		String network = Config.network;

		ID id = new ID(master.publicKey());
		printKeyValue("Definancy ID", id.toString());

		byte[] pubBytes = id.toBytes();
		id = new ID(pubBytes);
		printKeyValue("Definancy ID (imported bytes)", id.toString());

		String pubStr = id.toString();
		id = new ID(pubStr);
		printKeyValue("Definancy ID (imported string)", id.toString());

		DID did = new DID(network, id);
		printKeyValue("Definancy DID on dev network", did.toString());
    }

	public static void printKeyValue(String key, String value) {
		System.out.printf("%s:\n  %s\n", key, value);
	}
}
