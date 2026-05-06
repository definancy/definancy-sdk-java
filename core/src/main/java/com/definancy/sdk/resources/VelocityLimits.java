package com.definancy.sdk.resources;

import com.definancy.api.VelocityApi;
import com.definancy.model.VelocityLimitFormat;
import com.definancy.sdk.internal.Invoker;

import java.util.List;

/**
 * Velocity limits — rolling-window payment caps. Two scopes:
 *
 * <ul>
 *   <li>{@link #account()} — caps applied across all vaults owned by
 *       the caller's account.</li>
 *   <li>{@link #vault()} — per-vault caps, evaluated in addition to
 *       account-scope caps.</li>
 * </ul>
 *
 * <p>The {@code windowMinutes} parameter identifies a specific limit
 * (zero means "single payment cap"); {@code set} upserts; {@code delete}
 * removes the limit for that window (idempotent — returns 204 even if
 * absent).
 */
public final class VelocityLimits {
    private final Account account;
    private final Vault vault;

    public VelocityLimits(VelocityApi api, Invoker invoker) {
        this.account = new Account(api, invoker);
        this.vault = new Vault(api, invoker);
    }

    public Account account() {
        return account;
    }

    public Vault vault() {
        return vault;
    }

    /** Account-scope velocity-limit operations (apply across every vault). */
    public static final class Account {
        private final VelocityApi api;
        private final Invoker invoker;

        Account(VelocityApi api, Invoker invoker) {
            this.api = api;
            this.invoker = invoker;
        }

        public List<VelocityLimitFormat> list() {
            return invoker.invoke(api::getAccountVelocityLimits);
        }

        public VelocityLimitFormat set(VelocityLimitFormat config) {
            return invoker.invoke(() -> api.setAccountVelocityLimit(config));
        }

        public void delete(int windowMinutes) {
            invoker.invokeVoid(() -> api.deleteAccountVelocityLimit(windowMinutes));
        }
    }

    /** Vault-scope velocity-limit operations (apply to a specific vault). */
    public static final class Vault {
        private final VelocityApi api;
        private final Invoker invoker;

        Vault(VelocityApi api, Invoker invoker) {
            this.api = api;
            this.invoker = invoker;
        }

        public List<VelocityLimitFormat> list(String vaultId) {
            return invoker.invoke(() -> api.getVaultVelocityLimits(vaultId));
        }

        public VelocityLimitFormat set(String vaultId, VelocityLimitFormat config) {
            return invoker.invoke(() -> api.setVaultVelocityLimit(vaultId, config));
        }

        public void delete(String vaultId, int windowMinutes) {
            invoker.invokeVoid(() -> api.deleteVaultVelocityLimit(vaultId, windowMinutes));
        }
    }
}
