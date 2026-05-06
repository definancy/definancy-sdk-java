package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.api.AuthApi;
import com.definancy.sdk.DID;

public class APIRegisterDid {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = Config.GetApiClient();
        AuthApi api = new AuthApi(apiClient);

        try {
            DID did = Config.getDID();
            api.registerAuth(did.getId().toString());
            System.out.println("DID registered successfully");
        } catch (ApiException e) {
            Utils.printException(e, "AuthApi", "registerAuth");
        } catch (Exception e) {
            Utils.printException(e, "AuthApi", "registerAuth");
        }
    }
}
