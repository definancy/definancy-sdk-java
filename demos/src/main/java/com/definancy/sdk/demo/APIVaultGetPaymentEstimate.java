package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.api.VaultPaymentApi;
import com.definancy.model.*;

import java.util.Arrays;
import java.util.List;

public class APIVaultGetPaymentEstimate {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = Config.GetApiClient();
        VaultPaymentApi api = new VaultPaymentApi(apiClient);

        List<ContractAmountFormat> contractAmounts = Arrays.asList(
            Utils.createContractAmount("target", "EUR", "1.23")
        );

        try {
            PaymentEstimate paymentEstimate = api.vaultGetPaymentEstimate(Config.vaultId, contractAmounts);
            List<PaymentEstimateScenario> scenarios = paymentEstimate.getScenarios();

            if (scenarios.isEmpty()) {
                System.out.println("No payment scenarios found");
                return;
            }

            System.out.println("Payment scenarios:");
            for(PaymentEstimateScenario scenario : scenarios) {
                ContractAmount pay = scenario.getPay();
                ContractId contractId = pay.getContractId();
                String value = pay.getAmount().getValue();
                System.out.printf("\tPay %s %s on %s\n", value, contractId.getAssetUnit(), contractId.getNetworkId());
            }
            System.out.println("Estimate generated successfully");
        } catch (ApiException e) {
            Utils.printException(e, "VaultPaymentApi", "vaultGetPaymentEstimate");
        } catch (Exception e) {
            Utils.printException(e, "VaultPaymentApi", "vaultGetPaymentEstimate");
        }
    }
}
