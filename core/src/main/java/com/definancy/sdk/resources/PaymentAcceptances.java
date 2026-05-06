package com.definancy.sdk.resources;

import com.definancy.api.VaultPaymentApi;
import com.definancy.model.ContractAmountFormat;
import com.definancy.model.PaymentAcceptance;
import com.definancy.model.PaymentAcceptanceConfigFormat;
import com.definancy.model.PaymentEstimate;
import com.definancy.sdk.internal.Invoker;

import java.util.List;
import java.util.UUID;

/**
 * Payment acceptances — the lifecycle for partner-initiated payments
 * against a vault. Stages: {@code estimate} (preview) → {@code create}
 * → {@code get}/{@code update} → {@code archive}.
 * {@code linkDocument}/{@code unlinkDocument} attach compliance docs.
 */
public final class PaymentAcceptances {
    private final VaultPaymentApi api;
    private final Invoker invoker;

    public PaymentAcceptances(VaultPaymentApi api, Invoker invoker) {
        this.api = api;
        this.invoker = invoker;
    }

    /**
     * Preview the cost of a payment without committing. Each entry in
     * {@code contractAmounts} is a {@code {contract-id, amount}} pair.
     */
    public PaymentEstimate estimate(String vaultId, List<ContractAmountFormat> contractAmounts) {
        return invoker.invoke(() -> api.vaultGetPaymentEstimate(vaultId, contractAmounts));
    }

    public PaymentAcceptance create(String vaultId, PaymentAcceptanceConfigFormat body) {
        return invoker.invoke(() -> api.createPaymentAcceptance(vaultId, body));
    }

    public PaymentAcceptance get(String vaultId, UUID paymentAcceptanceId) {
        return invoker.invoke(() -> api.getPaymentAcceptance(vaultId, paymentAcceptanceId));
    }

    public PaymentAcceptance update(
            String vaultId, UUID paymentAcceptanceId, PaymentAcceptanceConfigFormat body) {
        return invoker.invoke(() ->
                api.updatePaymentAcceptance(vaultId, paymentAcceptanceId, body));
    }

    public PaymentAcceptance archive(String vaultId, UUID paymentAcceptanceId) {
        return invoker.invoke(() -> api.archivePaymentAcceptance(vaultId, paymentAcceptanceId));
    }

    public PaymentAcceptance linkDocument(
            String vaultId, UUID paymentAcceptanceId, UUID documentId) {
        return invoker.invoke(() ->
                api.linkPaymentAcceptanceDocument(vaultId, paymentAcceptanceId, documentId));
    }

    public PaymentAcceptance unlinkDocument(
            String vaultId, UUID paymentAcceptanceId, UUID documentId) {
        return invoker.invoke(() ->
                api.unlinkPaymentAcceptanceDocument(vaultId, paymentAcceptanceId, documentId));
    }
}
