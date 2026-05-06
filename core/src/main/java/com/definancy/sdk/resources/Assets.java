package com.definancy.sdk.resources;

import com.definancy.api.AssetApi;
import com.definancy.model.Asset;
import com.definancy.model.AssetConfig;
import com.definancy.sdk.internal.Invoker;

import java.util.List;

/** Assets — fungible asset definitions (currencies / tokens). */
public final class Assets {
    private final AssetApi api;
    private final Invoker invoker;

    public Assets(AssetApi api, Invoker invoker) {
        this.api = api;
        this.invoker = invoker;
    }

    public List<Asset> list() {
        return invoker.invoke(api::getAssets);
    }

    public Asset get(String assetUnit) {
        return invoker.invoke(() -> api.getAsset(assetUnit));
    }

    public Asset configure(String assetUnit, AssetConfig config) {
        return invoker.invoke(() -> api.configAsset(assetUnit, config));
    }
}
