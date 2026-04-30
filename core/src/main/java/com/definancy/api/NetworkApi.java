package com.definancy.api;

import com.definancy.ApiException;
import com.definancy.ApiClient;
import com.definancy.ApiResponse;
import com.definancy.Configuration;
import com.definancy.Pair;

import javax.ws.rs.core.GenericType;

import com.definancy.model.Contract;
import com.definancy.model.ErrorList;
import com.definancy.model.Network;
import com.definancy.model.NetworkConfig;
import com.definancy.model.NetworkExplorer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.20.0")
public class NetworkApi {
  private ApiClient apiClient;

  public NetworkApi() {
    this(Configuration.getDefaultApiClient());
  }

  public NetworkApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Get the API client
   *
   * @return API client
   */
  public ApiClient getApiClient() {
    return apiClient;
  }

  /**
   * Set the API client
   *
   * @param apiClient an instance of API client
   */
  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Configure Network
   * Updates configuration parameters for a network. This endpoint allows you to update configuration parameters for a specific network, which is essential for managing and maintaining networks for  payment processing.
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @param networkConfig Network configuration parameters to update. Currently supports only the &#39;enabled&#39;  field for controlling network availability. When setting &#39;enabled&#39; to false, the  network will be disabled for payment processing while preserving all existing  contracts and historical transaction data. (required)
   * @return Network
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Network configuration updated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Network configNetwork(@javax.annotation.Nonnull String networkId, @javax.annotation.Nonnull NetworkConfig networkConfig) throws ApiException {
    return configNetworkWithHttpInfo(networkId, networkConfig).getData();
  }

  /**
   * Configure Network
   * Updates configuration parameters for a network. This endpoint allows you to update configuration parameters for a specific network, which is essential for managing and maintaining networks for  payment processing.
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @param networkConfig Network configuration parameters to update. Currently supports only the &#39;enabled&#39;  field for controlling network availability. When setting &#39;enabled&#39; to false, the  network will be disabled for payment processing while preserving all existing  contracts and historical transaction data. (required)
   * @return ApiResponse&lt;Network&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Network configuration updated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Network> configNetworkWithHttpInfo(@javax.annotation.Nonnull String networkId, @javax.annotation.Nonnull NetworkConfig networkConfig) throws ApiException {
    // Check required parameters
    if (networkId == null) {
      throw new ApiException(400, "Missing the required parameter 'networkId' when calling configNetwork");
    }
    if (networkConfig == null) {
      throw new ApiException(400, "Missing the required parameter 'networkConfig' when calling configNetwork");
    }

    // Path parameters
    String localVarPath = "/v1/network/{networkId}"
            .replaceAll("\\{networkId}", apiClient.escapeString(networkId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType("application/json");
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Network> localVarReturnType = new GenericType<Network>() {};
    return apiClient.invokeAPI("NetworkApi.configNetwork", localVarPath, "PATCH", new ArrayList<>(), networkConfig,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Get Network
   * Retrieves configuration details for a specific network. This endpoint returns detailed configuration information for a specific network, which is essential for managing and maintaining networks for  payment processing.
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @return Network
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Network details retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Network getNetwork(@javax.annotation.Nonnull String networkId) throws ApiException {
    return getNetworkWithHttpInfo(networkId).getData();
  }

  /**
   * Get Network
   * Retrieves configuration details for a specific network. This endpoint returns detailed configuration information for a specific network, which is essential for managing and maintaining networks for  payment processing.
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @return ApiResponse&lt;Network&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Network details retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Network> getNetworkWithHttpInfo(@javax.annotation.Nonnull String networkId) throws ApiException {
    // Check required parameters
    if (networkId == null) {
      throw new ApiException(400, "Missing the required parameter 'networkId' when calling getNetwork");
    }

    // Path parameters
    String localVarPath = "/v1/network/{networkId}"
            .replaceAll("\\{networkId}", apiClient.escapeString(networkId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Network> localVarReturnType = new GenericType<Network>() {};
    return apiClient.invokeAPI("NetworkApi.getNetwork", localVarPath, "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Get Network Explorer
   * Returns URL templates for the network&#39;s block explorer. Templates use {value} as a placeholder for the address or transaction ID.
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @return NetworkExplorer
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Explorer URL templates retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public NetworkExplorer getNetworkExplorer(@javax.annotation.Nonnull String networkId) throws ApiException {
    return getNetworkExplorerWithHttpInfo(networkId).getData();
  }

  /**
   * Get Network Explorer
   * Returns URL templates for the network&#39;s block explorer. Templates use {value} as a placeholder for the address or transaction ID.
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @return ApiResponse&lt;NetworkExplorer&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Explorer URL templates retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<NetworkExplorer> getNetworkExplorerWithHttpInfo(@javax.annotation.Nonnull String networkId) throws ApiException {
    // Check required parameters
    if (networkId == null) {
      throw new ApiException(400, "Missing the required parameter 'networkId' when calling getNetworkExplorer");
    }

    // Path parameters
    String localVarPath = "/v1/network/{networkId}/explorer"
            .replaceAll("\\{networkId}", apiClient.escapeString(networkId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    GenericType<NetworkExplorer> localVarReturnType = new GenericType<NetworkExplorer>() {};
    return apiClient.invokeAPI("NetworkApi.getNetworkExplorer", localVarPath, "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               null, localVarReturnType, false);
  }
  /**
   * Get Network Native Asset
   * Retrieves the native asset of a specific network in a form of a contract. This endpoint returns the native asset of a specific network in the form of a contract,  which is essential for understanding and managing the native token of a network.
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @return Contract
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> The contract with the network&#39;s native asset details is retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Contract getNetworkNativeAsset(@javax.annotation.Nonnull String networkId) throws ApiException {
    return getNetworkNativeAssetWithHttpInfo(networkId).getData();
  }

  /**
   * Get Network Native Asset
   * Retrieves the native asset of a specific network in a form of a contract. This endpoint returns the native asset of a specific network in the form of a contract,  which is essential for understanding and managing the native token of a network.
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @return ApiResponse&lt;Contract&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> The contract with the network&#39;s native asset details is retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Contract> getNetworkNativeAssetWithHttpInfo(@javax.annotation.Nonnull String networkId) throws ApiException {
    // Check required parameters
    if (networkId == null) {
      throw new ApiException(400, "Missing the required parameter 'networkId' when calling getNetworkNativeAsset");
    }

    // Path parameters
    String localVarPath = "/v1/network/{networkId}/native"
            .replaceAll("\\{networkId}", apiClient.escapeString(networkId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Contract> localVarReturnType = new GenericType<Contract>() {};
    return apiClient.invokeAPI("NetworkApi.getNetworkNativeAsset", localVarPath, "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * List Networks
   * Retrieves all configured networks. This endpoint returns a list of all configured networks, which are essential for  setting up and maintaining networks for payment processing.
   * @return List&lt;Network&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Successful retrieval of network configurations. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public List<Network> getNetworks() throws ApiException {
    return getNetworksWithHttpInfo().getData();
  }

  /**
   * List Networks
   * Retrieves all configured networks. This endpoint returns a list of all configured networks, which are essential for  setting up and maintaining networks for payment processing.
   * @return ApiResponse&lt;List&lt;Network&gt;&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Successful retrieval of network configurations. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<List<Network>> getNetworksWithHttpInfo() throws ApiException {
    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<List<Network>> localVarReturnType = new GenericType<List<Network>>() {};
    return apiClient.invokeAPI("NetworkApi.getNetworks", "/v1/network", "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
}
