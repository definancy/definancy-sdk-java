package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.api.ExperimentalApi;
import com.definancy.sdk.DID;

public class APIRegisterDid {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = Config.GetApiClient();
        ExperimentalApi api = new ExperimentalApi(apiClient);

        try {
            DID did = Config.getDID();
            api.registerDid(did.getId().toString());
            System.out.println("DID registered successfully");
        } catch (ApiException e) {
            Utils.printException(e, "ExperimentalApi", "registerDid");
        } catch (Exception e) {
            Utils.printException(e, "ExperimentalApi", "registerDid");
        }
    }
}
