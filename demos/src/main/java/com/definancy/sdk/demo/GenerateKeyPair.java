package com.definancy.sdk.demo;

import com.definancy.sdk.DID;
import com.definancy.sdk.ID;
import com.definancy.sdk.crypto.KeyPair;

public class GenerateKeyPair {
    public static void main( String[] args ) throws Exception {
        String network = Config.network;

        System.out.println("=== MASTER ===");
        KeyPair master = KeyPair.generateKeyPair();
        printKeyPairInfo(master, network);

        System.out.println();

        System.out.println("=== RECOVERY ===");
        String secret = master.export();
        KeyPair recover = KeyPair.generateKeyPairFromSecret(secret);
        printKeyPairInfo(recover, network);
    }

    public static void printKeyPairInfo(KeyPair pair, String network) throws Exception {
        ID id = new ID(pair.publicKey());
        printKeyValue("Definancy ID", id.toString());

        String secret = pair.export();
        printKeyValue("Definancy ID secret", secret);

        DID did = new DID(network, id);
        printKeyValue("Definancy DID on dev network", did.toString());
    }

    public static void printKeyValue(String key, String value) {
        System.out.printf("%s:\n  %s\n", key, value);
    }
}
