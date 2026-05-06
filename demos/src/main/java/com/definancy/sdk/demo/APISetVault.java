package com.definancy.sdk.demo;

import com.definancy.model.Asset;
import com.definancy.model.AssetConfig;
import com.definancy.model.Contract;
import com.definancy.model.ContractConfig;
import com.definancy.model.ContractId;
import com.definancy.model.Network;
import com.definancy.model.NetworkConfig;
import com.definancy.model.Vault;
import com.definancy.model.VaultConfig;
import com.definancy.sdk.DefinancyApiException;
import com.definancy.sdk.DefinancyClient;

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
        try (DefinancyClient definancy = Config.newClient()) {
            int enabledNetworks = 0;
            for (Network network : definancy.networks().list()) {
                if (Boolean.FALSE.equals(network.getConfig().getEnabled())) {
                    NetworkConfig cfg = new NetworkConfig();
                    cfg.setEnabled(true);
                    definancy.networks().configure(network.getId(), cfg);
                    enabledNetworks++;
                }
            }
            System.out.printf("Enabled %d networks%n", enabledNetworks);

            int enabledAssets = 0;
            for (Asset asset : definancy.assets().list()) {
                if (Boolean.FALSE.equals(asset.getConfig().getEnabled())) {
                    AssetConfig cfg = new AssetConfig();
                    cfg.setEnabled(true);
                    definancy.assets().configure(asset.getUnit(), cfg);
                    enabledAssets++;
                }
            }
            System.out.printf("Enabled %d assets%n", enabledAssets);

            List<Contract> contracts = definancy.contracts().list();
            int enabledContracts = 0;
            for (Contract contract : contracts) {
                if (Boolean.FALSE.equals(contract.getConfig().getEnabled())) {
                    ContractConfig cfg = new ContractConfig();
                    cfg.setEnabled(true);
                    definancy.contracts().configure(
                            contract.getId().getAssetUnit(),
                            contract.getId().getNetworkId(),
                            cfg);
                    enabledContracts++;
                }
            }
            System.out.printf("Enabled %d contracts%n", enabledContracts);

            List<ContractId> allContractIds = contracts.stream()
                    .map(c -> Utils.createContractId(
                            c.getId().getNetworkId(),
                            c.getId().getAssetUnit()))
                    .collect(Collectors.toCollection(ArrayList::new));

            VaultConfig vaultConfig = new VaultConfig();
            vaultConfig.setEnabled(true);
            vaultConfig.setContractIds(allContractIds);

            Vault vault = definancy.vaults().set(Config.vaultId, vaultConfig);
            System.out.printf("Vault '%s' set with %d contracts%n",
                    vault.getId(), vault.getConfig().getContractIds().size());
        } catch (DefinancyApiException e) {
            Utils.printException(e, "vaults", "set");
        } catch (Exception e) {
            Utils.printException(e, "vaults", "set");
        }
    }
}
