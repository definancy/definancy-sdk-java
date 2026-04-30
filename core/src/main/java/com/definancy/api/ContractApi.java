package com.definancy.api;

import com.definancy.ApiException;
import com.definancy.ApiClient;
import com.definancy.ApiResponse;
import com.definancy.Configuration;
import com.definancy.Pair;

import javax.ws.rs.core.GenericType;

import com.definancy.model.Contract;
import com.definancy.model.ContractConfig;
import com.definancy.model.ErrorList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.20.0")
public class ContractApi {
  private ApiClient apiClient;

  public ContractApi() {
    this(Configuration.getDefaultApiClient());
  }

  public ContractApi(ApiClient apiClient) {
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
   * Configure Contract
   * Updates configuration parameters for a contract. This endpoint allows you to update configuration parameters for a specific contract, which is essential for executing and validating payments on networks.
   * @param assetUnit Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. (required)
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @param contractConfig Contract configuration parameters to update. Currently supports only the &#39;enabled&#39;  field for controlling contract availability. When setting &#39;enabled&#39; to false, the  contract will be disabled for payment processing while preserving all existing  vault associations, payment acceptance, and historical transaction data. (required)
   * @return Contract
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Contract configuration updated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Contract configContract(@javax.annotation.Nonnull String assetUnit, @javax.annotation.Nonnull String networkId, @javax.annotation.Nonnull ContractConfig contractConfig) throws ApiException {
    return configContractWithHttpInfo(assetUnit, networkId, contractConfig).getData();
  }

  /**
   * Configure Contract
   * Updates configuration parameters for a contract. This endpoint allows you to update configuration parameters for a specific contract, which is essential for executing and validating payments on networks.
   * @param assetUnit Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. (required)
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @param contractConfig Contract configuration parameters to update. Currently supports only the &#39;enabled&#39;  field for controlling contract availability. When setting &#39;enabled&#39; to false, the  contract will be disabled for payment processing while preserving all existing  vault associations, payment acceptance, and historical transaction data. (required)
   * @return ApiResponse&lt;Contract&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Contract configuration updated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Contract> configContractWithHttpInfo(@javax.annotation.Nonnull String assetUnit, @javax.annotation.Nonnull String networkId, @javax.annotation.Nonnull ContractConfig contractConfig) throws ApiException {
    // Check required parameters
    if (assetUnit == null) {
      throw new ApiException(400, "Missing the required parameter 'assetUnit' when calling configContract");
    }
    if (networkId == null) {
      throw new ApiException(400, "Missing the required parameter 'networkId' when calling configContract");
    }
    if (contractConfig == null) {
      throw new ApiException(400, "Missing the required parameter 'contractConfig' when calling configContract");
    }

    // Path parameters
    String localVarPath = "/v1/contract/{assetUnit}/{networkId}"
            .replaceAll("\\{assetUnit}", apiClient.escapeString(assetUnit.toString()))
            .replaceAll("\\{networkId}", apiClient.escapeString(networkId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType("application/json");
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Contract> localVarReturnType = new GenericType<Contract>() {};
    return apiClient.invokeAPI("ContractApi.configContract", localVarPath, "PATCH", new ArrayList<>(), contractConfig,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Get Contract
   * Retrieves configuration details for a specific smart contract. This endpoint returns detailed configuration information for a specific contract, which is essential for executing and validating payments on networks.
   * @param assetUnit Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. (required)
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @return Contract
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Contract information retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Contract getContract(@javax.annotation.Nonnull String assetUnit, @javax.annotation.Nonnull String networkId) throws ApiException {
    return getContractWithHttpInfo(assetUnit, networkId).getData();
  }

  /**
   * Get Contract
   * Retrieves configuration details for a specific smart contract. This endpoint returns detailed configuration information for a specific contract, which is essential for executing and validating payments on networks.
   * @param assetUnit Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. (required)
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @return ApiResponse&lt;Contract&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Contract information retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Contract> getContractWithHttpInfo(@javax.annotation.Nonnull String assetUnit, @javax.annotation.Nonnull String networkId) throws ApiException {
    // Check required parameters
    if (assetUnit == null) {
      throw new ApiException(400, "Missing the required parameter 'assetUnit' when calling getContract");
    }
    if (networkId == null) {
      throw new ApiException(400, "Missing the required parameter 'networkId' when calling getContract");
    }

    // Path parameters
    String localVarPath = "/v1/contract/{assetUnit}/{networkId}"
            .replaceAll("\\{assetUnit}", apiClient.escapeString(assetUnit.toString()))
            .replaceAll("\\{networkId}", apiClient.escapeString(networkId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Contract> localVarReturnType = new GenericType<Contract>() {};
    return apiClient.invokeAPI("ContractApi.getContract", localVarPath, "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * List Contracts
   * Retrieves all contract configurations. This endpoint returns a list of all configured contracts, which are essential for executing and validating payments on networks.
   * @return List&lt;Contract&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Contract list retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public List<Contract> getContracts() throws ApiException {
    return getContractsWithHttpInfo().getData();
  }

  /**
   * List Contracts
   * Retrieves all contract configurations. This endpoint returns a list of all configured contracts, which are essential for executing and validating payments on networks.
   * @return ApiResponse&lt;List&lt;Contract&gt;&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Contract list retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<List<Contract>> getContractsWithHttpInfo() throws ApiException {
    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<List<Contract>> localVarReturnType = new GenericType<List<Contract>>() {};
    return apiClient.invokeAPI("ContractApi.getContracts", "/v1/contract", "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
}
