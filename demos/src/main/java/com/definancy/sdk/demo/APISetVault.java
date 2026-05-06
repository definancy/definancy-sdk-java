package com.definancy.sdk.demo;

import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.api.AssetApi;
import com.definancy.api.ContractApi;
import com.definancy.api.NetworkApi;
import com.definancy.api.VaultApi;
import com.definancy.model.AssetConfig;
import com.definancy.model.Contract;
import com.definancy.model.ContractConfig;
import com.definancy.model.ContractId;
import com.definancy.model.NetworkConfig;
import com.definancy.model.Vault;
import com.definancy.model.VaultConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bootstrap demo: enables every disabled network, asset and contract on the
 * server, then sets a vault that includes every available contract.
 *
 * Mirrors the gateway-demo's setup.ts pattern. Idempotent against the stub —
 * re-runnable without manual cleanup.
 */
public class APISetVault {
    public static void main(String[] args) throws Exception {
        ApiClient apiClient = Config.GetApiClient();
        NetworkApi networkApi = new NetworkApi(apiClient);
        AssetApi assetApi = new AssetApi(apiClient);
        ContractApi contractApi = new ContractApi(apiClient);
        VaultApi vaultApi = new VaultApi(apiClient);

        try {
            // 1. Enable all disabled networks
            int enabledNetworks = 0;
            for (com.definancy.model.Network network : networkApi.getNetworks()) {
                if (Boolean.FALSE.equals(network.getConfig().getEnabled())) {
                    NetworkConfig cfg = new NetworkConfig();
                    cfg.setEnabled(true);
                    networkApi.configNetwork(network.getId(), cfg);
                    enabledNetworks++;
                }
            }
            System.out.printf("Enabled %d networks%n", enabledNetworks);

            // 2. Enable all disabled assets
            int enabledAssets = 0;
            for (com.definancy.model.Asset asset : assetApi.getAssets()) {
                if (Boolean.FALSE.equals(asset.getConfig().getEnabled())) {
                    AssetConfig cfg = new AssetConfig();
                    cfg.setEnabled(true);
                    assetApi.configAsset(asset.getUnit(), cfg);
                    enabledAssets++;
                }
            }
            System.out.printf("Enabled %d assets%n", enabledAssets);

            // 3. Enable all disabled contracts
            List<Contract> contracts = contractApi.getContracts();
            int enabledContracts = 0;
            for (Contract contract : contracts) {
                if (Boolean.FALSE.equals(contract.getConfig().getEnabled())) {
                    ContractConfig cfg = new ContractConfig();
                    cfg.setEnabled(true);
                    contractApi.configContract(
                            contract.getId().getAssetUnit(),
                            contract.getId().getNetworkId(),
                            cfg);
                    enabledContracts++;
                }
            }
            System.out.printf("Enabled %d contracts%n", enabledContracts);

            // 4. Set the vault with every available contract
            List<ContractId> allContractIds = contracts.stream()
                    .map(c -> Utils.createContractId(
                            c.getId().getNetworkId(),
                            c.getId().getAssetUnit()))
                    .collect(Collectors.toCollection(ArrayList::new));

            VaultConfig vaultConfig = new VaultConfig();
            vaultConfig.setEnabled(true);
            vaultConfig.setContractIds(allContractIds);

            Vault vault = vaultApi.setVault(Config.vaultId, vaultConfig);
            System.out.printf("Vault '%s' set with %d contracts%n",
                    vault.getId(), vault.getConfig().getContractIds().size());
        } catch (ApiException e) {
            Utils.printException(e, "VaultApi", "setVault");
        } catch (Exception e) {
            Utils.printException(e, "VaultApi", "setVault");
        }
    }
}
