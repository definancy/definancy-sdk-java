# VaultPaymentApi

All URIs are relative to *https://stub.definancy.com*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**archivePaymentAcceptance**](VaultPaymentApi.md#archivePaymentAcceptance) | **DELETE** /v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId} | Archive Payment Acceptance |
| [**createPaymentAcceptance**](VaultPaymentApi.md#createPaymentAcceptance) | **PUT** /v1/vault/{vaultId}/payment/acceptance | Create Payment Acceptance |
| [**getPaymentAcceptance**](VaultPaymentApi.md#getPaymentAcceptance) | **GET** /v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId} | Get Payment Acceptance |
| [**linkPaymentAcceptanceDocument**](VaultPaymentApi.md#linkPaymentAcceptanceDocument) | **PUT** /v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId}/document/{documentId} | Link Document to Payment Acceptance |
| [**unlinkPaymentAcceptanceDocument**](VaultPaymentApi.md#unlinkPaymentAcceptanceDocument) | **DELETE** /v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId}/document/{documentId} | Unlink Document from Payment Acceptance |
| [**updatePaymentAcceptance**](VaultPaymentApi.md#updatePaymentAcceptance) | **PATCH** /v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId} | Update Payment Acceptance |
| [**vaultGetPaymentEstimate**](VaultPaymentApi.md#vaultGetPaymentEstimate) | **POST** /v1/vault/{vaultId}/payment/estimate | Generate Payment Estimate |



## archivePaymentAcceptance

> PaymentAcceptance archivePaymentAcceptance(vaultId, paymentAcceptanceId)

Archive Payment Acceptance

Deactivates a payment acceptance while preserving historical records.

### Example

```java
import java.util.UUID;
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.VaultPaymentApi;

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

        VaultPaymentApi apiInstance = new VaultPaymentApi(defaultClient);
        String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
        UUID paymentAcceptanceId = UUID.randomUUID(); // UUID | Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information.
        try {
            PaymentAcceptance result = apiInstance.archivePaymentAcceptance(vaultId, paymentAcceptanceId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VaultPaymentApi#archivePaymentAcceptance");
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
| **vaultId** | **String**| Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. | |
| **paymentAcceptanceId** | **UUID**| Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. | |

### Return type

[**PaymentAcceptance**](PaymentAcceptance.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Payment acceptance archived successfully. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## createPaymentAcceptance

> PaymentAcceptance createPaymentAcceptance(vaultId, paymentAcceptanceConfigFormat)

Create Payment Acceptance

Initiates a new payment acceptance process.

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.VaultPaymentApi;

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

        VaultPaymentApi apiInstance = new VaultPaymentApi(defaultClient);
        String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
        PaymentAcceptanceConfigFormat paymentAcceptanceConfigFormat = new PaymentAcceptanceConfigFormat(); // PaymentAcceptanceConfigFormat | Payment acceptance configuration including required payment scenarios and optional order context. The 'price-scenarios' list must contain at least one contract-amount pair and all contracts must be subscribed to the vault. Optional 'order' provides commercial context, and 'documents' can reference existing compliance documents.
        try {
            PaymentAcceptance result = apiInstance.createPaymentAcceptance(vaultId, paymentAcceptanceConfigFormat);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VaultPaymentApi#createPaymentAcceptance");
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
| **vaultId** | **String**| Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. | |
| **paymentAcceptanceConfigFormat** | [**PaymentAcceptanceConfigFormat**](PaymentAcceptanceConfigFormat.md)| Payment acceptance configuration including required payment scenarios and optional order context. The &#39;price-scenarios&#39; list must contain at least one contract-amount pair and all contracts must be subscribed to the vault. Optional &#39;order&#39; provides commercial context, and &#39;documents&#39; can reference existing compliance documents. | |

### Return type

[**PaymentAcceptance**](PaymentAcceptance.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Payment acceptance created successfully. |  * Location - URL of the submitted document. <br>  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## getPaymentAcceptance

> PaymentAcceptance getPaymentAcceptance(vaultId, paymentAcceptanceId)

Get Payment Acceptance

Retrieves details of an existing payment acceptance.

### Example

```java
import java.util.UUID;
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.VaultPaymentApi;

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

        VaultPaymentApi apiInstance = new VaultPaymentApi(defaultClient);
        String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
        UUID paymentAcceptanceId = UUID.randomUUID(); // UUID | Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information.
        try {
            PaymentAcceptance result = apiInstance.getPaymentAcceptance(vaultId, paymentAcceptanceId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VaultPaymentApi#getPaymentAcceptance");
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
| **vaultId** | **String**| Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. | |
| **paymentAcceptanceId** | **UUID**| Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. | |

### Return type

[**PaymentAcceptance**](PaymentAcceptance.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Payment acceptance details retrieved. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## linkPaymentAcceptanceDocument

> PaymentAcceptance linkPaymentAcceptanceDocument(vaultId, paymentAcceptanceId, documentId)

Link Document to Payment Acceptance

Associates a document with a payment acceptance.
Supported document types: personal identification, entity documentation, 
wallet verification data.

### Example

```java
import java.util.UUID;
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.VaultPaymentApi;

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

        VaultPaymentApi apiInstance = new VaultPaymentApi(defaultClient);
        String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
        UUID paymentAcceptanceId = UUID.randomUUID(); // UUID | Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information.
        UUID documentId = UUID.randomUUID(); // UUID | Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle.
        try {
            PaymentAcceptance result = apiInstance.linkPaymentAcceptanceDocument(vaultId, paymentAcceptanceId, documentId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VaultPaymentApi#linkPaymentAcceptanceDocument");
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
| **vaultId** | **String**| Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. | |
| **paymentAcceptanceId** | **UUID**| Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. | |
| **documentId** | **UUID**| Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. | |

### Return type

[**PaymentAcceptance**](PaymentAcceptance.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Document successfully linked to payment acceptance. |  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## unlinkPaymentAcceptanceDocument

> PaymentAcceptance unlinkPaymentAcceptanceDocument(vaultId, paymentAcceptanceId, documentId)

Unlink Document from Payment Acceptance

Removes document association from active consideration in payment acceptance
while retaining it for audit purposes.

### Example

```java
import java.util.UUID;
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.VaultPaymentApi;

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

        VaultPaymentApi apiInstance = new VaultPaymentApi(defaultClient);
        String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
        UUID paymentAcceptanceId = UUID.randomUUID(); // UUID | Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information.
        UUID documentId = UUID.randomUUID(); // UUID | Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle.
        try {
            PaymentAcceptance result = apiInstance.unlinkPaymentAcceptanceDocument(vaultId, paymentAcceptanceId, documentId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VaultPaymentApi#unlinkPaymentAcceptanceDocument");
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
| **vaultId** | **String**| Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. | |
| **paymentAcceptanceId** | **UUID**| Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. | |
| **documentId** | **UUID**| Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. | |

### Return type

[**PaymentAcceptance**](PaymentAcceptance.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Document successfully unlinked from payment acceptance. |  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## updatePaymentAcceptance

> PaymentAcceptance updatePaymentAcceptance(vaultId, paymentAcceptanceId, paymentAcceptanceConfigFormat)

Update Payment Acceptance

Modifies configuration of an existing payment acceptance.

### Example

```java
import java.util.UUID;
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.VaultPaymentApi;

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

        VaultPaymentApi apiInstance = new VaultPaymentApi(defaultClient);
        String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
        UUID paymentAcceptanceId = UUID.randomUUID(); // UUID | Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information.
        PaymentAcceptanceConfigFormat paymentAcceptanceConfigFormat = new PaymentAcceptanceConfigFormat(); // PaymentAcceptanceConfigFormat | Partial payment acceptance configuration for updates. Can be used to modify the 'documents' list to add or remove compliance document associations, or to update 'order' information. Core price scenarios cannot be modified after  creation.
        try {
            PaymentAcceptance result = apiInstance.updatePaymentAcceptance(vaultId, paymentAcceptanceId, paymentAcceptanceConfigFormat);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VaultPaymentApi#updatePaymentAcceptance");
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
| **vaultId** | **String**| Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. | |
| **paymentAcceptanceId** | **UUID**| Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. | |
| **paymentAcceptanceConfigFormat** | [**PaymentAcceptanceConfigFormat**](PaymentAcceptanceConfigFormat.md)| Partial payment acceptance configuration for updates. Can be used to modify the &#39;documents&#39; list to add or remove compliance document associations, or to update &#39;order&#39; information. Core price scenarios cannot be modified after  creation. | |

### Return type

[**PaymentAcceptance**](PaymentAcceptance.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Payment acceptance updated successfully. |  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## vaultGetPaymentEstimate

> PaymentEstimate vaultGetPaymentEstimate(vaultId, contractAmountFormat)

Generate Payment Estimate

Calculates payment scenarios for specified contracts and amounts.
Requires at least one contract-amount pair in the request body.

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.VaultPaymentApi;

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

        VaultPaymentApi apiInstance = new VaultPaymentApi(defaultClient);
        String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
        List<ContractAmountFormat> contractAmountFormat = Arrays.asList(); // List<ContractAmountFormat> | List of contract-amount pairs for which to generate payment estimates. Must contain at least one contract-amount pair. Each contract must be subscribed to the vault. The amounts should represent the desired payment values in the contract's native units.
        try {
            PaymentEstimate result = apiInstance.vaultGetPaymentEstimate(vaultId, contractAmountFormat);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VaultPaymentApi#vaultGetPaymentEstimate");
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
| **vaultId** | **String**| Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. | |
| **contractAmountFormat** | [**List&lt;ContractAmountFormat&gt;**](ContractAmountFormat.md)| List of contract-amount pairs for which to generate payment estimates. Must contain at least one contract-amount pair. Each contract must be subscribed to the vault. The amounts should represent the desired payment values in the contract&#39;s native units. | |

### Return type

[**PaymentEstimate**](PaymentEstimate.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Payment estimate generated successfully. |  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

