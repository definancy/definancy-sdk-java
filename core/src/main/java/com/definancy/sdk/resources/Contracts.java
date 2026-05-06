package com.definancy.sdk.resources;

import com.definancy.api.ContractApi;
import com.definancy.model.Contract;
import com.definancy.model.ContractConfig;
import com.definancy.sdk.internal.Invoker;

import java.util.List;

/**
 * Contracts — {@code (asset-unit, network-id)} tuples representing the
 * asset's deployment on a specific network.
 */
public final class Contracts {
    private final ContractApi api;
    private final Invoker invoker;

    public Contracts(ContractApi api, Invoker invoker) {
        this.api = api;
        this.invoker = invoker;
    }

    public List<Contract> list() {
        return invoker.invoke(api::getContracts);
    }

    public Contract get(String assetUnit, String networkId) {
        return invoker.invoke(() -> api.getContract(assetUnit, networkId));
    }

    public Contract configure(String assetUnit, String networkId, ContractConfig config) {
        return invoker.invoke(() -> api.configContract(assetUnit, networkId, config));
    }
}
