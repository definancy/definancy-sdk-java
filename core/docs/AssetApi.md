# AssetApi

All URIs are relative to *https://stub.definancy.com*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**configAsset**](AssetApi.md#configAsset) | **PATCH** /v1/asset/{assetUnit} | Configure Asset |
| [**getAsset**](AssetApi.md#getAsset) | **GET** /v1/asset/{assetUnit} | Get Asset |
| [**getAssets**](AssetApi.md#getAssets) | **GET** /v1/asset | List Assets |



## configAsset

> Asset configAsset(assetUnit, assetConfig)

Configure Asset

Updates configuration parameters for a digital asset.
This endpoint allows you to update configuration parameters for a specific digital asset, 
which is essential for managing and handling various assets in payment operations.

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.AssetApi;

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

        AssetApi apiInstance = new AssetApi(defaultClient);
        String assetUnit = "assetUnit_example"; // String | Ticker symbol for a digital asset (e.g., 'EUR', 'USDC', 'ETH', 'BTC'). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset.
        AssetConfig assetConfig = new AssetConfig(); // AssetConfig | Asset configuration parameters to update. Currently supports only the 'enabled'  field for controlling asset availability. When setting 'enabled' to false, the  asset will be disabled for payment processing while preserving all existing  contracts and historical transaction data.
        try {
            Asset result = apiInstance.configAsset(assetUnit, assetConfig);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AssetApi#configAsset");
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
| **assetUnit** | **String**| Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. | |
| **assetConfig** | [**AssetConfig**](AssetConfig.md)| Asset configuration parameters to update. Currently supports only the &#39;enabled&#39;  field for controlling asset availability. When setting &#39;enabled&#39; to false, the  asset will be disabled for payment processing while preserving all existing  contracts and historical transaction data. | |

### Return type

[**Asset**](Asset.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Asset configuration updated successfully. |  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## getAsset

> Asset getAsset(assetUnit)

Get Asset

Retrieves configuration details for a specific digital asset.
This endpoint returns detailed configuration information for a specific digital asset,
which is essential for managing and handling various assets in payment operations.

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.AssetApi;

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

        AssetApi apiInstance = new AssetApi(defaultClient);
        String assetUnit = "assetUnit_example"; // String | Ticker symbol for a digital asset (e.g., 'EUR', 'USDC', 'ETH', 'BTC'). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset.
        try {
            Asset result = apiInstance.getAsset(assetUnit);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AssetApi#getAsset");
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
| **assetUnit** | **String**| Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. | |

### Return type

[**Asset**](Asset.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Asset details retrieved successfully. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## getAssets

> List&lt;Asset&gt; getAssets()

List Assets

Retrieves all configured digital assets.
This endpoint returns a list of all configured digital assets, which are essential
for handling various assets in payment operations.

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.AssetApi;

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

        AssetApi apiInstance = new AssetApi(defaultClient);
        try {
            List<Asset> result = apiInstance.getAssets();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AssetApi#getAssets");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**List&lt;Asset&gt;**](Asset.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Asset list retrieved successfully. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

