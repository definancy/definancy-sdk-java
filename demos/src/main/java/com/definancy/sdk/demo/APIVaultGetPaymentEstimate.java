package com.definancy.sdk.demo;

import com.definancy.model.ContractAmount;
import com.definancy.model.ContractAmountFormat;
import com.definancy.model.ContractId;
import com.definancy.model.PaymentEstimate;
import com.definancy.model.PaymentEstimateScenario;
import com.definancy.sdk.DefinancyApiException;
import com.definancy.sdk.DefinancyClient;

import java.util.Arrays;
import java.util.List;

public class APIVaultGetPaymentEstimate {
    public static void main(String[] args) throws Exception {
        List<ContractAmountFormat> contractAmounts = Arrays.asList(
            Utils.createContractAmount("target", "EUR", "1.23")
        );

        try (DefinancyClient definancy = Config.newClient()) {
            PaymentEstimate estimate = definancy.paymentAcceptances()
                    .estimate(Config.vaultId, contractAmounts);

            List<PaymentEstimateScenario> scenarios = estimate.getScenarios();
            if (scenarios.isEmpty()) {
                System.out.println("No payment scenarios found");
                return;
            }

            System.out.println("Payment scenarios:");
            for (PaymentEstimateScenario scenario : scenarios) {
                ContractAmount pay = scenario.getPay();
                ContractId contractId = pay.getContractId();
                String value = pay.getAmount().getValue();
                System.out.printf("\tPay %s %s on %s%n",
                        value, contractId.getAssetUnit(), contractId.getNetworkId());
            }
            System.out.println("Estimate generated successfully");
        } catch (DefinancyApiException e) {
            Utils.printException(e, "paymentAcceptances", "estimate");
        } catch (Exception e) {
            Utils.printException(e, "paymentAcceptances", "estimate");
        }
    }
}
