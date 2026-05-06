package com.definancy.sdk.resources;

import com.definancy.api.QrCodeApi;
import com.definancy.model.QrCode;
import com.definancy.model.QrCodeTransactionRequest;
import com.definancy.sdk.internal.Invoker;

import java.util.List;

/**
 * QR codes — generate wallet-payable QR codes for blockchain
 * transactions. Currently only the wallet-transaction shape is
 * supported.
 */
public final class QrCodes {
    private final QrCodeApi api;
    private final Invoker invoker;

    public QrCodes(QrCodeApi api, Invoker invoker) {
        this.api = api;
        this.invoker = invoker;
    }

    /**
     * Generate one or more wallet-payable QR codes for the supplied
     * transaction request.
     */
    public List<QrCode> transaction(QrCodeTransactionRequest request) {
        return invoker.invoke(() -> api.generateWalletQrCodes(request));
    }
}
