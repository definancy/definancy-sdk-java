package com.definancy.api;

import com.definancy.ApiException;
import com.definancy.ApiClient;
import com.definancy.ApiResponse;
import com.definancy.Configuration;
import com.definancy.Pair;

import javax.ws.rs.core.GenericType;

import com.definancy.model.ErrorList;
import com.definancy.model.Vault;
import com.definancy.model.VaultConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.20.0")
public class VaultApi {
  private ApiClient apiClient;

  public VaultApi() {
    this(Configuration.getDefaultApiClient());
  }

  public VaultApi(ApiClient apiClient) {
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
   * Archive Vault
   * Deactivates a vault while preserving historical data.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @return Vault
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Vault archived successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Vault archiveVault(@javax.annotation.Nonnull String vaultId) throws ApiException {
    return archiveVaultWithHttpInfo(vaultId).getData();
  }

  /**
   * Archive Vault
   * Deactivates a vault while preserving historical data.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @return ApiResponse&lt;Vault&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Vault archived successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Vault> archiveVaultWithHttpInfo(@javax.annotation.Nonnull String vaultId) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling archiveVault");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Vault> localVarReturnType = new GenericType<Vault>() {};
    return apiClient.invokeAPI("VaultApi.archiveVault", localVarPath, "DELETE", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Partially Update Vault Configuration
   * Updates specific fields of an existing vault configuration.  Only provided properties will be modified. Returns the updated vault configuration.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param vaultConfig Partial vault configuration containing only the fields to be updated. The &#39;contract-ids&#39; list can be used to modify vault subscriptions by adding or removing contract associations. The &#39;enabled&#39; field can be used to activate or deactivate the vault for new operations. Only the provided fields will be modified, preserving all other existing configuration settings. (required)
   * @return Vault
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Vault configuration partially updated successfully </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Vault configVault(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull VaultConfig vaultConfig) throws ApiException {
    return configVaultWithHttpInfo(vaultId, vaultConfig).getData();
  }

  /**
   * Partially Update Vault Configuration
   * Updates specific fields of an existing vault configuration.  Only provided properties will be modified. Returns the updated vault configuration.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param vaultConfig Partial vault configuration containing only the fields to be updated. The &#39;contract-ids&#39; list can be used to modify vault subscriptions by adding or removing contract associations. The &#39;enabled&#39; field can be used to activate or deactivate the vault for new operations. Only the provided fields will be modified, preserving all other existing configuration settings. (required)
   * @return ApiResponse&lt;Vault&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Vault configuration partially updated successfully </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Vault> configVaultWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull VaultConfig vaultConfig) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling configVault");
    }
    if (vaultConfig == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultConfig' when calling configVault");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType("application/json");
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Vault> localVarReturnType = new GenericType<Vault>() {};
    return apiClient.invokeAPI("VaultApi.configVault", localVarPath, "PATCH", new ArrayList<>(), vaultConfig,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Get Vault
   * Retrieves configuration details for a specific vault. This endpoint returns detailed configuration information for a specific vault, which is essential for organizing and processing payments while maintaining compliance.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @return Vault
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Vault details retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Vault getVault(@javax.annotation.Nonnull String vaultId) throws ApiException {
    return getVaultWithHttpInfo(vaultId).getData();
  }

  /**
   * Get Vault
   * Retrieves configuration details for a specific vault. This endpoint returns detailed configuration information for a specific vault, which is essential for organizing and processing payments while maintaining compliance.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @return ApiResponse&lt;Vault&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Vault details retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Vault> getVaultWithHttpInfo(@javax.annotation.Nonnull String vaultId) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling getVault");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Vault> localVarReturnType = new GenericType<Vault>() {};
    return apiClient.invokeAPI("VaultApi.getVault", localVarPath, "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * List Vaults
   * Retrieves all vault configurations. This endpoint returns a list of all vault configurations, which are essential for organizing and processing payments while maintaining compliance.
   * @return List&lt;Vault&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Vault list retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public List<Vault> getVaults() throws ApiException {
    return getVaultsWithHttpInfo().getData();
  }

  /**
   * List Vaults
   * Retrieves all vault configurations. This endpoint returns a list of all vault configurations, which are essential for organizing and processing payments while maintaining compliance.
   * @return ApiResponse&lt;List&lt;Vault&gt;&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Vault list retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<List<Vault>> getVaultsWithHttpInfo() throws ApiException {
    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<List<Vault>> localVarReturnType = new GenericType<List<Vault>>() {};
    return apiClient.invokeAPI("VaultApi.getVaults", "/v1/vault", "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Create or Update Vault
   * Updates or creates the configuration for a vault with {vaultId}. This operation  will fully replace the existing configuration if the vault exists, or create a new vault if it does not.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param vaultConfig Vault configuration with the fields to be updated with non-default values. All  non-specified fields will be set with the default values. The &#39;contract-ids&#39;  list can be used to add or remove contract subscriptions. The &#39;enabled&#39; field  can be used to activate or deactivate the vault. (required)
   * @return Vault
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Vault configuration updated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Vault setVault(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull VaultConfig vaultConfig) throws ApiException {
    return setVaultWithHttpInfo(vaultId, vaultConfig).getData();
  }

  /**
   * Create or Update Vault
   * Updates or creates the configuration for a vault with {vaultId}. This operation  will fully replace the existing configuration if the vault exists, or create a new vault if it does not.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param vaultConfig Vault configuration with the fields to be updated with non-default values. All  non-specified fields will be set with the default values. The &#39;contract-ids&#39;  list can be used to add or remove contract subscriptions. The &#39;enabled&#39; field  can be used to activate or deactivate the vault. (required)
   * @return ApiResponse&lt;Vault&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Vault configuration updated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Vault> setVaultWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull VaultConfig vaultConfig) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling setVault");
    }
    if (vaultConfig == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultConfig' when calling setVault");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType("application/json");
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Vault> localVarReturnType = new GenericType<Vault>() {};
    return apiClient.invokeAPI("VaultApi.setVault", localVarPath, "PUT", new ArrayList<>(), vaultConfig,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
}
