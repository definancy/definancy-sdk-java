package com.definancy.api;

import com.definancy.ApiException;
import com.definancy.ApiClient;
import com.definancy.ApiResponse;
import com.definancy.Configuration;
import com.definancy.Pair;

import javax.ws.rs.core.GenericType;

import com.definancy.model.Asset;
import com.definancy.model.AssetConfig;
import com.definancy.model.ErrorList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.20.0")
public class AssetApi {
  private ApiClient apiClient;

  public AssetApi() {
    this(Configuration.getDefaultApiClient());
  }

  public AssetApi(ApiClient apiClient) {
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
   * Configure Asset
   * Updates configuration parameters for a digital asset. This endpoint allows you to update configuration parameters for a specific digital asset,  which is essential for managing and handling various assets in payment operations.
   * @param assetUnit Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. (required)
   * @param assetConfig Asset configuration parameters to update. Currently supports only the &#39;enabled&#39;  field for controlling asset availability. When setting &#39;enabled&#39; to false, the  asset will be disabled for payment processing while preserving all existing  contracts and historical transaction data. (required)
   * @return Asset
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Asset configuration updated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Asset configAsset(@javax.annotation.Nonnull String assetUnit, @javax.annotation.Nonnull AssetConfig assetConfig) throws ApiException {
    return configAssetWithHttpInfo(assetUnit, assetConfig).getData();
  }

  /**
   * Configure Asset
   * Updates configuration parameters for a digital asset. This endpoint allows you to update configuration parameters for a specific digital asset,  which is essential for managing and handling various assets in payment operations.
   * @param assetUnit Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. (required)
   * @param assetConfig Asset configuration parameters to update. Currently supports only the &#39;enabled&#39;  field for controlling asset availability. When setting &#39;enabled&#39; to false, the  asset will be disabled for payment processing while preserving all existing  contracts and historical transaction data. (required)
   * @return ApiResponse&lt;Asset&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Asset configuration updated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Asset> configAssetWithHttpInfo(@javax.annotation.Nonnull String assetUnit, @javax.annotation.Nonnull AssetConfig assetConfig) throws ApiException {
    // Check required parameters
    if (assetUnit == null) {
      throw new ApiException(400, "Missing the required parameter 'assetUnit' when calling configAsset");
    }
    if (assetConfig == null) {
      throw new ApiException(400, "Missing the required parameter 'assetConfig' when calling configAsset");
    }

    // Path parameters
    String localVarPath = "/v1/asset/{assetUnit}"
            .replaceAll("\\{assetUnit}", apiClient.escapeString(assetUnit.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType("application/json");
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Asset> localVarReturnType = new GenericType<Asset>() {};
    return apiClient.invokeAPI("AssetApi.configAsset", localVarPath, "PATCH", new ArrayList<>(), assetConfig,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Get Asset
   * Retrieves configuration details for a specific digital asset. This endpoint returns detailed configuration information for a specific digital asset, which is essential for managing and handling various assets in payment operations.
   * @param assetUnit Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. (required)
   * @return Asset
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Asset details retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Asset getAsset(@javax.annotation.Nonnull String assetUnit) throws ApiException {
    return getAssetWithHttpInfo(assetUnit).getData();
  }

  /**
   * Get Asset
   * Retrieves configuration details for a specific digital asset. This endpoint returns detailed configuration information for a specific digital asset, which is essential for managing and handling various assets in payment operations.
   * @param assetUnit Ticker symbol for a digital asset (e.g., &#39;EUR&#39;, &#39;USDC&#39;, &#39;ETH&#39;, &#39;BTC&#39;). Used to identify the specific asset for contract operations, payment processing, and vault management. Must match an existing configured asset. (required)
   * @return ApiResponse&lt;Asset&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Asset details retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Asset> getAssetWithHttpInfo(@javax.annotation.Nonnull String assetUnit) throws ApiException {
    // Check required parameters
    if (assetUnit == null) {
      throw new ApiException(400, "Missing the required parameter 'assetUnit' when calling getAsset");
    }

    // Path parameters
    String localVarPath = "/v1/asset/{assetUnit}"
            .replaceAll("\\{assetUnit}", apiClient.escapeString(assetUnit.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Asset> localVarReturnType = new GenericType<Asset>() {};
    return apiClient.invokeAPI("AssetApi.getAsset", localVarPath, "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * List Assets
   * Retrieves all configured digital assets. This endpoint returns a list of all configured digital assets, which are essential for handling various assets in payment operations.
   * @return List&lt;Asset&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Asset list retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public List<Asset> getAssets() throws ApiException {
    return getAssetsWithHttpInfo().getData();
  }

  /**
   * List Assets
   * Retrieves all configured digital assets. This endpoint returns a list of all configured digital assets, which are essential for handling various assets in payment operations.
   * @return ApiResponse&lt;List&lt;Asset&gt;&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Asset list retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<List<Asset>> getAssetsWithHttpInfo() throws ApiException {
    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<List<Asset>> localVarReturnType = new GenericType<List<Asset>>() {};
    return apiClient.invokeAPI("AssetApi.getAssets", "/v1/asset", "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
}
