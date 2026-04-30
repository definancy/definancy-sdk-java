# QrCodeApi

All URIs are relative to *https://stub.definancy.com*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**generateWalletQrCodes**](QrCodeApi.md#generateWalletQrCodes) | **POST** /v1/qrcode/transaction | Generate QR-Codes for wallets |



## generateWalletQrCodes

> List&lt;QrCode&gt; generateWalletQrCodes(qrCodeTransactionRequest)

Generate QR-Codes for wallets

Generates the QR-Codes to be used by a wallet to help on the creation of a
transaction request.

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.QrCodeApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://stub.definancy.com");
        
        // Configure API key authorization: dpop-auth
        ApiKeyAuth dpop-auth = (ApiKeyAuth) defaultClient.getAuthentication("dpop-auth");
        dpop-auth.setApiKey("YOUR API KEY");
        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
        //dpop-auth.setApiKeyPrefix("Token");

        // Configure API key authorization: dpop-proof
        ApiKeyAuth dpop-proof = (ApiKeyAuth) defaultClient.getAuthentication("dpop-proof");
        dpop-proof.setApiKey("YOUR API KEY");
        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
        //dpop-proof.setApiKeyPrefix("Token");

        QrCodeApi apiInstance = new QrCodeApi(defaultClient);
        QrCodeTransactionRequest qrCodeTransactionRequest = new QrCodeTransactionRequest(); // QrCodeTransactionRequest | Contract-amount pair for which to generate the qr-codes. The amount should represent the desired payment value in the contract's unit.
        try {
            List<QrCode> result = apiInstance.generateWalletQrCodes(qrCodeTransactionRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling QrCodeApi#generateWalletQrCodes");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **qrCodeTransactionRequest** | [**QrCodeTransactionRequest**](QrCodeTransactionRequest.md)| Contract-amount pair for which to generate the qr-codes. The amount should represent the desired payment value in the contract&#39;s unit. | |

### Return type

[**List&lt;QrCode&gt;**](QrCode.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | QR-Code generated successfully. |  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

