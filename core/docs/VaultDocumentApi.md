# VaultDocumentApi

All URIs are relative to *https://stub.definancy.com*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**archiveDocument**](VaultDocumentApi.md#archiveDocument) | **DELETE** /v1/vault/{vaultId}/document/{documentId} | Archive Document |
| [**getDocument**](VaultDocumentApi.md#getDocument) | **GET** /v1/vault/{vaultId}/document/{documentId} | Get Document |
| [**submitDocument**](VaultDocumentApi.md#submitDocument) | **PUT** /v1/vault/{vaultId}/document | Submit Document |



## archiveDocument

> Document archiveDocument(vaultId, documentId)

Archive Document

Deactivates document from active consideration while 
retaining it for audit purposes.

### Example

```java
import java.util.UUID;
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.VaultDocumentApi;

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

        VaultDocumentApi apiInstance = new VaultDocumentApi(defaultClient);
        String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
        UUID documentId = UUID.randomUUID(); // UUID | Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle.
        try {
            Document result = apiInstance.archiveDocument(vaultId, documentId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VaultDocumentApi#archiveDocument");
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
| **documentId** | **UUID**| Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. | |

### Return type

[**Document**](Document.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Document archived successfully. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## getDocument

> Document getDocument(vaultId, documentId)

Get Document

Retrieves metadata and content of a document 
including validation status and reviewer comments.

### Example

```java
import java.util.UUID;
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.VaultDocumentApi;

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

        VaultDocumentApi apiInstance = new VaultDocumentApi(defaultClient);
        String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
        UUID documentId = UUID.randomUUID(); // UUID | Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle.
        try {
            Document result = apiInstance.getDocument(vaultId, documentId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VaultDocumentApi#getDocument");
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
| **documentId** | **UUID**| Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. | |

### Return type

[**Document**](Document.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Document retrieved successfully. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## submitDocument

> Document submitDocument(vaultId, documentConfig)

Submit Document

Submits a new document for verification.
Supported types: personal identification, entity documentation, 
wallet verification data.

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.VaultDocumentApi;

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

        VaultDocumentApi apiInstance = new VaultDocumentApi(defaultClient);
        String vaultId = "vaultId_example"; // String | Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates.
        DocumentConfig documentConfig = new DocumentConfig(); // DocumentConfig | Complete document configuration including document type and content. The 'type' field determines the document category (PersonV1, CustodialV1, or NonCustodialV1), and the 'data' field contains the corresponding document data structure with all required fields.
        try {
            Document result = apiInstance.submitDocument(vaultId, documentConfig);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VaultDocumentApi#submitDocument");
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
| **documentConfig** | [**DocumentConfig**](DocumentConfig.md)| Complete document configuration including document type and content. The &#39;type&#39; field determines the document category (PersonV1, CustodialV1, or NonCustodialV1), and the &#39;data&#39; field contains the corresponding document data structure with all required fields. | |

### Return type

[**Document**](Document.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Document submitted successfully. |  * Location - URL of the submitted document. <br>  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

