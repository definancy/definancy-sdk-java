package com.definancy.api;

import com.definancy.ApiException;
import com.definancy.ApiClient;
import com.definancy.ApiResponse;
import com.definancy.Configuration;
import com.definancy.Pair;

import javax.ws.rs.core.GenericType;

import com.definancy.model.ErrorList;
import com.definancy.model.Vault;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.20.0")
public class VaultContractSubscriptionApi {
  private ApiClient apiClient;

  public VaultContractSubscriptionApi() {
    this(Configuration.getDefaultApiClient());
  }

  public VaultContractSubscriptionApi(ApiClient apiClient) {
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
   * Subscribe Contract to Vault
   * Associates a smart contract with a vault.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param assetUnit Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. (required)
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @return Vault
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Contract successfully subscribed to vault. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Vault vaultSubscribeContract(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull String assetUnit, @javax.annotation.Nonnull String networkId) throws ApiException {
    return vaultSubscribeContractWithHttpInfo(vaultId, assetUnit, networkId).getData();
  }

  /**
   * Subscribe Contract to Vault
   * Associates a smart contract with a vault.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param assetUnit Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. (required)
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @return ApiResponse&lt;Vault&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Contract successfully subscribed to vault. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Vault> vaultSubscribeContractWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull String assetUnit, @javax.annotation.Nonnull String networkId) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling vaultSubscribeContract");
    }
    if (assetUnit == null) {
      throw new ApiException(400, "Missing the required parameter 'assetUnit' when calling vaultSubscribeContract");
    }
    if (networkId == null) {
      throw new ApiException(400, "Missing the required parameter 'networkId' when calling vaultSubscribeContract");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}/contract/{assetUnit}/{networkId}"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()))
            .replaceAll("\\{assetUnit}", apiClient.escapeString(assetUnit.toString()))
            .replaceAll("\\{networkId}", apiClient.escapeString(networkId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Vault> localVarReturnType = new GenericType<Vault>() {};
    return apiClient.invokeAPI("VaultContractSubscriptionApi.vaultSubscribeContract", localVarPath, "PUT", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Unsubscribe Contract from Vault
   * Removes association between a contract and vault.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param assetUnit Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. (required)
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @return Vault
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Contract successfully unsubscribed from vault. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Vault vaultUnsubscribeContract(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull String assetUnit, @javax.annotation.Nonnull String networkId) throws ApiException {
    return vaultUnsubscribeContractWithHttpInfo(vaultId, assetUnit, networkId).getData();
  }

  /**
   * Unsubscribe Contract from Vault
   * Removes association between a contract and vault.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param assetUnit Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. (required)
   * @param networkId Unique identifier for a specific blockchain network (e.g., &#39;ethereum&#39;, &#39;algorand&#39;). Used to target operations on a particular network when managing contracts, assets, or vault subscriptions. Must match an existing configured network. (required)
   * @return ApiResponse&lt;Vault&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Contract successfully unsubscribed from vault. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Vault> vaultUnsubscribeContractWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull String assetUnit, @javax.annotation.Nonnull String networkId) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling vaultUnsubscribeContract");
    }
    if (assetUnit == null) {
      throw new ApiException(400, "Missing the required parameter 'assetUnit' when calling vaultUnsubscribeContract");
    }
    if (networkId == null) {
      throw new ApiException(400, "Missing the required parameter 'networkId' when calling vaultUnsubscribeContract");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}/contract/{assetUnit}/{networkId}"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()))
            .replaceAll("\\{assetUnit}", apiClient.escapeString(assetUnit.toString()))
            .replaceAll("\\{networkId}", apiClient.escapeString(networkId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Vault> localVarReturnType = new GenericType<Vault>() {};
    return apiClient.invokeAPI("VaultContractSubscriptionApi.vaultUnsubscribeContract", localVarPath, "DELETE", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
}
