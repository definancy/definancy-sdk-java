package com.definancy.sdk.resources;

import com.definancy.api.VaultApi;
import com.definancy.api.VaultContractSubscriptionApi;
import com.definancy.model.Vault;
import com.definancy.model.VaultConfig;
import com.definancy.sdk.internal.Invoker;

import java.util.List;

/**
 * Vaults — partner-owned containers that hold subscribed contracts and
 * carry payment-acceptance + document state. Lifecycle: {@code set} →
 * {@code configure} (partial) → {@code archive};
 * {@code subscribeContract}/{@code unsubscribeContract} manage the
 * contract subscription set.
 */
public final class Vaults {
    private final VaultApi api;
    private final VaultContractSubscriptionApi subscriptionApi;
    private final Invoker invoker;

    public Vaults(VaultApi api, VaultContractSubscriptionApi subscriptionApi, Invoker invoker) {
        this.api = api;
        this.subscriptionApi = subscriptionApi;
        this.invoker = invoker;
    }

    public List<Vault> list() {
        return invoker.invoke(api::getVaults);
    }

    public Vault get(String vaultId) {
        return invoker.invoke(() -> api.getVault(vaultId));
    }

    /** Full-replace upsert (PUT). */
    public Vault set(String vaultId, VaultConfig config) {
        return invoker.invoke(() -> api.setVault(vaultId, config));
    }

    /** Partial update (PATCH). */
    public Vault configure(String vaultId, VaultConfig config) {
        return invoker.invoke(() -> api.configVault(vaultId, config));
    }

    /** Archive (soft-delete). Idempotent. */
    public Vault archive(String vaultId) {
        return invoker.invoke(() -> api.archiveVault(vaultId));
    }

    /** Subscribe a contract to this vault. Idempotent. */
    public Vault subscribeContract(String vaultId, String assetUnit, String networkId) {
        return invoker.invoke(() ->
                subscriptionApi.vaultSubscribeContract(vaultId, assetUnit, networkId));
    }

    /** Unsubscribe a contract from this vault. Idempotent. */
    public Vault unsubscribeContract(String vaultId, String assetUnit, String networkId) {
        return invoker.invoke(() ->
                subscriptionApi.vaultUnsubscribeContract(vaultId, assetUnit, networkId));
    }
}
