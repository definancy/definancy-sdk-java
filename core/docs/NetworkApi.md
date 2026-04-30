# NetworkApi

All URIs are relative to *https://stub.definancy.com*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**configNetwork**](NetworkApi.md#configNetwork) | **PATCH** /v1/network/{networkId} | Configure Network |
| [**getNetwork**](NetworkApi.md#getNetwork) | **GET** /v1/network/{networkId} | Get Network |
| [**getNetworkNativeAsset**](NetworkApi.md#getNetworkNativeAsset) | **GET** /v1/network/{networkId}/native | Get Network Native Asset |
| [**getNetworks**](NetworkApi.md#getNetworks) | **GET** /v1/network | List Networks |



## configNetwork

> Network configNetwork(networkId, networkConfig)

Configure Network

Updates configuration parameters for a network.
This endpoint allows you to update configuration parameters for a specific
network, which is essential for managing and maintaining networks for 
payment processing.

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.NetworkApi;

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

        NetworkApi apiInstance = new NetworkApi(defaultClient);
        String networkId = "networkId_example"; // String | Unique identifier for a specific blockchain network (e.g., 'ethereum', 'algorand'). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network.
        NetworkConfig networkConfig = new NetworkConfig(); // NetworkConfig | Network configuration parameters to update. Currently supports only the 'enabled'  field for controlling network availability. When setting 'enabled' to false, the  network will be disabled for payment processing while preserving all existing  contracts and historical transaction data.
        try {
            Network result = apiInstance.configNetwork(networkId, networkConfig);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NetworkApi#configNetwork");
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
| **networkId** | **String**| Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. | |
| **networkConfig** | [**NetworkConfig**](NetworkConfig.md)| Network configuration parameters to update. Currently supports only the &#39;enabled&#39;  field for controlling network availability. When setting &#39;enabled&#39; to false, the  network will be disabled for payment processing while preserving all existing  contracts and historical transaction data. | |

### Return type

[**Network**](Network.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Network configuration updated successfully. |  * Cache-Control -  <br>  |
| **400** | The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. |  -  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## getNetwork

> Network getNetwork(networkId)

Get Network

Retrieves configuration details for a specific network.
This endpoint returns detailed configuration information for a specific
network, which is essential for managing and maintaining networks for 
payment processing.

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.NetworkApi;

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

        NetworkApi apiInstance = new NetworkApi(defaultClient);
        String networkId = "networkId_example"; // String | Unique identifier for a specific blockchain network (e.g., 'ethereum', 'algorand'). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network.
        try {
            Network result = apiInstance.getNetwork(networkId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NetworkApi#getNetwork");
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
| **networkId** | **String**| Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. | |

### Return type

[**Network**](Network.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Network details retrieved successfully. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## getNetworkNativeAsset

> Contract getNetworkNativeAsset(networkId)

Get Network Native Asset

Retrieves the native asset of a specific network in a form of a contract.
This endpoint returns the native asset of a specific network in the form of a contract, 
which is essential for understanding and managing the native token of a network.

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.NetworkApi;

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

        NetworkApi apiInstance = new NetworkApi(defaultClient);
        String networkId = "networkId_example"; // String | Unique identifier for a specific blockchain network (e.g., 'ethereum', 'algorand'). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network.
        try {
            Contract result = apiInstance.getNetworkNativeAsset(networkId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NetworkApi#getNetworkNativeAsset");
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
| **networkId** | **String**| Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. | |

### Return type

[**Contract**](Contract.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The contract with the network&#39;s native asset details is retrieved successfully. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **404** | The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## getNetworks

> List&lt;Network&gt; getNetworks()

List Networks

Retrieves all configured networks.
This endpoint returns a list of all configured networks, which are essential for 
setting up and maintaining networks for payment processing.

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.auth.*;
import com.definancy.model.*;
import com.definancy.api.NetworkApi;

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

        NetworkApi apiInstance = new NetworkApi(defaultClient);
        try {
            List<Network> result = apiInstance.getNetworks();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NetworkApi#getNetworks");
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

[**List&lt;Network&gt;**](Network.md)

### Authorization

[dpop-auth](../README.md#dpop-auth), [dpop-proof](../README.md#dpop-proof)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful retrieval of network configurations. |  * Cache-Control -  <br>  |
| **401** | Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. |  -  |
| **403** | The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. |  -  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

