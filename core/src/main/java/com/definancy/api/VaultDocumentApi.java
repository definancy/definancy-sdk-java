package com.definancy.api;

import com.definancy.ApiException;
import com.definancy.ApiClient;
import com.definancy.ApiResponse;
import com.definancy.Configuration;
import com.definancy.Pair;

import javax.ws.rs.core.GenericType;

import com.definancy.model.Document;
import com.definancy.model.DocumentConfig;
import com.definancy.model.ErrorList;
import java.net.URI;
import java.util.UUID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.20.0")
public class VaultDocumentApi {
  private ApiClient apiClient;

  public VaultDocumentApi() {
    this(Configuration.getDefaultApiClient());
  }

  public VaultDocumentApi(ApiClient apiClient) {
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
   * Archive Document
   * Deactivates document from active consideration while  retaining it for audit purposes.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param documentId Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. (required)
   * @return Document
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Document archived successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Document archiveDocument(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID documentId) throws ApiException {
    return archiveDocumentWithHttpInfo(vaultId, documentId).getData();
  }

  /**
   * Archive Document
   * Deactivates document from active consideration while  retaining it for audit purposes.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param documentId Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. (required)
   * @return ApiResponse&lt;Document&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Document archived successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Document> archiveDocumentWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID documentId) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling archiveDocument");
    }
    if (documentId == null) {
      throw new ApiException(400, "Missing the required parameter 'documentId' when calling archiveDocument");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}/document/{documentId}"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()))
            .replaceAll("\\{documentId}", apiClient.escapeString(documentId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Document> localVarReturnType = new GenericType<Document>() {};
    return apiClient.invokeAPI("VaultDocumentApi.archiveDocument", localVarPath, "DELETE", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Get Document
   * Retrieves metadata and content of a document  including validation status and reviewer comments.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param documentId Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. (required)
   * @return Document
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Document retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Document getDocument(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID documentId) throws ApiException {
    return getDocumentWithHttpInfo(vaultId, documentId).getData();
  }

  /**
   * Get Document
   * Retrieves metadata and content of a document  including validation status and reviewer comments.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param documentId Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. (required)
   * @return ApiResponse&lt;Document&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Document retrieved successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Document> getDocumentWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID documentId) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling getDocument");
    }
    if (documentId == null) {
      throw new ApiException(400, "Missing the required parameter 'documentId' when calling getDocument");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}/document/{documentId}"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()))
            .replaceAll("\\{documentId}", apiClient.escapeString(documentId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Document> localVarReturnType = new GenericType<Document>() {};
    return apiClient.invokeAPI("VaultDocumentApi.getDocument", localVarPath, "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Submit Document
   * Submits a new document for verification. Supported types: personal identification, entity documentation,  wallet verification data.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param documentConfig Complete document configuration including document type and content. The &#39;type&#39; field determines the document category (PersonV1, CustodialV1, or NonCustodialV1), and the &#39;data&#39; field contains the corresponding document data structure with all required fields. (required)
   * @return Document
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 201 </td><td> Document submitted successfully. </td><td>  * Location - URL of the submitted document. <br>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Document submitDocument(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull DocumentConfig documentConfig) throws ApiException {
    return submitDocumentWithHttpInfo(vaultId, documentConfig).getData();
  }

  /**
   * Submit Document
   * Submits a new document for verification. Supported types: personal identification, entity documentation,  wallet verification data.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param documentConfig Complete document configuration including document type and content. The &#39;type&#39; field determines the document category (PersonV1, CustodialV1, or NonCustodialV1), and the &#39;data&#39; field contains the corresponding document data structure with all required fields. (required)
   * @return ApiResponse&lt;Document&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 201 </td><td> Document submitted successfully. </td><td>  * Location - URL of the submitted document. <br>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Document> submitDocumentWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull DocumentConfig documentConfig) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling submitDocument");
    }
    if (documentConfig == null) {
      throw new ApiException(400, "Missing the required parameter 'documentConfig' when calling submitDocument");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}/document"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType("application/json");
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<Document> localVarReturnType = new GenericType<Document>() {};
    return apiClient.invokeAPI("VaultDocumentApi.submitDocument", localVarPath, "PUT", new ArrayList<>(), documentConfig,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
}
