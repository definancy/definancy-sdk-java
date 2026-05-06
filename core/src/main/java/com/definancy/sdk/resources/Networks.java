package com.definancy.sdk.resources;

import com.definancy.api.NetworkApi;
import com.definancy.model.Contract;
import com.definancy.model.Network;
import com.definancy.model.NetworkConfig;
import com.definancy.model.NetworkExplorer;
import com.definancy.sdk.internal.Invoker;

import java.util.List;

/**
 * Networks — blockchain networks supported by the daemon. List/inspect
 * available networks, configure their {@code enabled} state, and query
 * per-network derived data (native asset contract, block-explorer URL
 * templates).
 */
public final class Networks {
    private final NetworkApi api;
    private final Invoker invoker;

    public Networks(NetworkApi api, Invoker invoker) {
        this.api = api;
        this.invoker = invoker;
    }

    public List<Network> list() {
        return invoker.invoke(api::getNetworks);
    }

    public Network get(String networkId) {
        return invoker.invoke(() -> api.getNetwork(networkId));
    }

    public Network configure(String networkId, NetworkConfig config) {
        return invoker.invoke(() -> api.configNetwork(networkId, config));
    }

    /** Get the native-asset contract for this network (e.g. ETH on ethereum-*). */
    public Contract getNative(String networkId) {
        return invoker.invoke(() -> api.getNetworkNativeAsset(networkId));
    }

    public NetworkExplorer getExplorer(String networkId) {
        return invoker.invoke(() -> api.getNetworkExplorer(networkId));
    }
}
