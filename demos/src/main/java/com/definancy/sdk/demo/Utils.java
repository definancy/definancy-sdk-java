package com.definancy.sdk.demo;

import com.definancy.ApiException;
import com.definancy.model.AmountFormat;
import com.definancy.model.AmountValue;
import com.definancy.model.ContractAmountFormat;
import com.definancy.model.ContractId;

public class Utils {
    public static void printException(Exception e, String api, String method) {
        System.err.printf("[GENERIC] Exception when calling %s#%s\n", api, method);
        e.printStackTrace();
    }

    public static void printException(ApiException e, String api, String method) {
        System.err.printf("[API] ApiException when calling %s#%s\n", api, method);
        System.err.println("Status code: " + e.getCode());
        System.err.println("Reason: " + e.getResponseBody());
        System.err.println("Response headers: " + e.getResponseHeaders());
        e.printStackTrace();
    }

    public static ContractId createContractId(String networkId, String assetUnit) {
        ContractId contractId = new ContractId();
        contractId.setNetworkId(networkId);
        contractId.setAssetUnit(assetUnit);

        return contractId;
    }

    public static ContractAmountFormat createContractAmount(String networkId, String assetUnit, String amountString) {
        ContractId contractId = Utils.createContractId(networkId, assetUnit);

        AmountValue amountValue = new AmountValue();
        amountValue.setValue(amountString);

        AmountFormat amount = new AmountFormat(amountValue);

        ContractAmountFormat contractAmount = new ContractAmountFormat();
        contractAmount.setContractId(contractId);
        contractAmount.setAmount(amount);

        return contractAmount;
    }
}
