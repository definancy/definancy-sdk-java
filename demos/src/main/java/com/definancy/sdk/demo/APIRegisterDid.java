package com.definancy.sdk.demo;

import com.definancy.sdk.DID;
import com.definancy.sdk.DefinancyApiException;
import com.definancy.sdk.DefinancyClient;

public class APIRegisterDid {
    public static void main(String[] args) throws Exception {
        try (DefinancyClient definancy = Config.newClient()) {
            DID did = Config.getDID();
            definancy.auth().register(did.getId().toString());
            System.out.println("DID registered successfully");
        } catch (DefinancyApiException e) {
            Utils.printException(e, "auth", "register");
        } catch (Exception e) {
            Utils.printException(e, "auth", "register");
        }
    }
}
