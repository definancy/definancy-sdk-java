package com.definancy.sdk.resources;

import com.definancy.api.VaultDocumentApi;
import com.definancy.model.Document;
import com.definancy.model.DocumentConfig;
import com.definancy.sdk.internal.Invoker;

import java.util.UUID;

/**
 * Documents — compliance + KYC artefacts attached to a vault. Submit
 * to assign a server-generated ID; reference that ID via
 * {@link PaymentAcceptances#linkDocument(String, UUID, UUID)} to attach
 * to a specific payment acceptance.
 */
public final class Documents {
    private final VaultDocumentApi api;
    private final Invoker invoker;

    public Documents(VaultDocumentApi api, Invoker invoker) {
        this.api = api;
        this.invoker = invoker;
    }

    /** Submit a new document. Server assigns the document ID in the response. */
    public Document submit(String vaultId, DocumentConfig body) {
        return invoker.invoke(() -> api.submitDocument(vaultId, body));
    }

    public Document get(String vaultId, UUID documentId) {
        return invoker.invoke(() -> api.getDocument(vaultId, documentId));
    }

    public Document archive(String vaultId, UUID documentId) {
        return invoker.invoke(() -> api.archiveDocument(vaultId, documentId));
    }
}
