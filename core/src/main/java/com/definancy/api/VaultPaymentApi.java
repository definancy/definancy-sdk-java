package com.definancy.api;

import com.definancy.ApiException;
import com.definancy.ApiClient;
import com.definancy.ApiResponse;
import com.definancy.Configuration;
import com.definancy.Pair;

import javax.ws.rs.core.GenericType;

import com.definancy.model.ContractAmountFormat;
import com.definancy.model.ErrorList;
import com.definancy.model.PaymentAcceptance;
import com.definancy.model.PaymentAcceptanceConfigFormat;
import com.definancy.model.PaymentEstimate;
import java.net.URI;
import java.util.UUID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.20.0")
public class VaultPaymentApi {
  private ApiClient apiClient;

  public VaultPaymentApi() {
    this(Configuration.getDefaultApiClient());
  }

  public VaultPaymentApi(ApiClient apiClient) {
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
   * Archive Payment Acceptance
   * Deactivates a payment acceptance while preserving historical records.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param paymentAcceptanceId Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. (required)
   * @return PaymentAcceptance
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Payment acceptance archived successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public PaymentAcceptance archivePaymentAcceptance(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID paymentAcceptanceId) throws ApiException {
    return archivePaymentAcceptanceWithHttpInfo(vaultId, paymentAcceptanceId).getData();
  }

  /**
   * Archive Payment Acceptance
   * Deactivates a payment acceptance while preserving historical records.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param paymentAcceptanceId Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. (required)
   * @return ApiResponse&lt;PaymentAcceptance&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Payment acceptance archived successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<PaymentAcceptance> archivePaymentAcceptanceWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID paymentAcceptanceId) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling archivePaymentAcceptance");
    }
    if (paymentAcceptanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'paymentAcceptanceId' when calling archivePaymentAcceptance");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId}"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()))
            .replaceAll("\\{paymentAcceptanceId}", apiClient.escapeString(paymentAcceptanceId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<PaymentAcceptance> localVarReturnType = new GenericType<PaymentAcceptance>() {};
    return apiClient.invokeAPI("VaultPaymentApi.archivePaymentAcceptance", localVarPath, "DELETE", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Create Payment Acceptance
   * Initiates a new payment acceptance process.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param paymentAcceptanceConfigFormat Payment acceptance configuration including required payment scenarios and optional order context. The &#39;price-scenarios&#39; list must contain at least one contract-amount pair and all contracts must be subscribed to the vault. Optional &#39;order&#39; provides commercial context, and &#39;documents&#39; can reference existing compliance documents. (required)
   * @return PaymentAcceptance
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 201 </td><td> Payment acceptance created successfully. </td><td>  * Location - URL of the submitted document. <br>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public PaymentAcceptance createPaymentAcceptance(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull PaymentAcceptanceConfigFormat paymentAcceptanceConfigFormat) throws ApiException {
    return createPaymentAcceptanceWithHttpInfo(vaultId, paymentAcceptanceConfigFormat).getData();
  }

  /**
   * Create Payment Acceptance
   * Initiates a new payment acceptance process.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param paymentAcceptanceConfigFormat Payment acceptance configuration including required payment scenarios and optional order context. The &#39;price-scenarios&#39; list must contain at least one contract-amount pair and all contracts must be subscribed to the vault. Optional &#39;order&#39; provides commercial context, and &#39;documents&#39; can reference existing compliance documents. (required)
   * @return ApiResponse&lt;PaymentAcceptance&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 201 </td><td> Payment acceptance created successfully. </td><td>  * Location - URL of the submitted document. <br>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<PaymentAcceptance> createPaymentAcceptanceWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull PaymentAcceptanceConfigFormat paymentAcceptanceConfigFormat) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling createPaymentAcceptance");
    }
    if (paymentAcceptanceConfigFormat == null) {
      throw new ApiException(400, "Missing the required parameter 'paymentAcceptanceConfigFormat' when calling createPaymentAcceptance");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}/payment/acceptance"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType("application/json");
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<PaymentAcceptance> localVarReturnType = new GenericType<PaymentAcceptance>() {};
    return apiClient.invokeAPI("VaultPaymentApi.createPaymentAcceptance", localVarPath, "PUT", new ArrayList<>(), paymentAcceptanceConfigFormat,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Get Payment Acceptance
   * Retrieves details of an existing payment acceptance.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param paymentAcceptanceId Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. (required)
   * @return PaymentAcceptance
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Payment acceptance details retrieved. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public PaymentAcceptance getPaymentAcceptance(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID paymentAcceptanceId) throws ApiException {
    return getPaymentAcceptanceWithHttpInfo(vaultId, paymentAcceptanceId).getData();
  }

  /**
   * Get Payment Acceptance
   * Retrieves details of an existing payment acceptance.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param paymentAcceptanceId Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. (required)
   * @return ApiResponse&lt;PaymentAcceptance&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Payment acceptance details retrieved. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<PaymentAcceptance> getPaymentAcceptanceWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID paymentAcceptanceId) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling getPaymentAcceptance");
    }
    if (paymentAcceptanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'paymentAcceptanceId' when calling getPaymentAcceptance");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId}"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()))
            .replaceAll("\\{paymentAcceptanceId}", apiClient.escapeString(paymentAcceptanceId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<PaymentAcceptance> localVarReturnType = new GenericType<PaymentAcceptance>() {};
    return apiClient.invokeAPI("VaultPaymentApi.getPaymentAcceptance", localVarPath, "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Link Document to Payment Acceptance
   * Associates a document with a payment acceptance. Supported document types: personal identification, entity documentation,  wallet verification data.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param paymentAcceptanceId Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. (required)
   * @param documentId Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. (required)
   * @return PaymentAcceptance
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Document successfully linked to payment acceptance. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public PaymentAcceptance linkPaymentAcceptanceDocument(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID paymentAcceptanceId, @javax.annotation.Nonnull UUID documentId) throws ApiException {
    return linkPaymentAcceptanceDocumentWithHttpInfo(vaultId, paymentAcceptanceId, documentId).getData();
  }

  /**
   * Link Document to Payment Acceptance
   * Associates a document with a payment acceptance. Supported document types: personal identification, entity documentation,  wallet verification data.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param paymentAcceptanceId Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. (required)
   * @param documentId Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. (required)
   * @return ApiResponse&lt;PaymentAcceptance&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Document successfully linked to payment acceptance. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<PaymentAcceptance> linkPaymentAcceptanceDocumentWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID paymentAcceptanceId, @javax.annotation.Nonnull UUID documentId) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling linkPaymentAcceptanceDocument");
    }
    if (paymentAcceptanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'paymentAcceptanceId' when calling linkPaymentAcceptanceDocument");
    }
    if (documentId == null) {
      throw new ApiException(400, "Missing the required parameter 'documentId' when calling linkPaymentAcceptanceDocument");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId}/document/{documentId}"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()))
            .replaceAll("\\{paymentAcceptanceId}", apiClient.escapeString(paymentAcceptanceId.toString()))
            .replaceAll("\\{documentId}", apiClient.escapeString(documentId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<PaymentAcceptance> localVarReturnType = new GenericType<PaymentAcceptance>() {};
    return apiClient.invokeAPI("VaultPaymentApi.linkPaymentAcceptanceDocument", localVarPath, "PUT", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Unlink Document from Payment Acceptance
   * Removes document association from active consideration in payment acceptance while retaining it for audit purposes.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param paymentAcceptanceId Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. (required)
   * @param documentId Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. (required)
   * @return PaymentAcceptance
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Document successfully unlinked from payment acceptance. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public PaymentAcceptance unlinkPaymentAcceptanceDocument(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID paymentAcceptanceId, @javax.annotation.Nonnull UUID documentId) throws ApiException {
    return unlinkPaymentAcceptanceDocumentWithHttpInfo(vaultId, paymentAcceptanceId, documentId).getData();
  }

  /**
   * Unlink Document from Payment Acceptance
   * Removes document association from active consideration in payment acceptance while retaining it for audit purposes.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param paymentAcceptanceId Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. (required)
   * @param documentId Unique identifier for a compliance document within a vault. Used for document retrieval, status checking, linking to payment acceptance, and managing the document verification lifecycle. (required)
   * @return ApiResponse&lt;PaymentAcceptance&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Document successfully unlinked from payment acceptance. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<PaymentAcceptance> unlinkPaymentAcceptanceDocumentWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID paymentAcceptanceId, @javax.annotation.Nonnull UUID documentId) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling unlinkPaymentAcceptanceDocument");
    }
    if (paymentAcceptanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'paymentAcceptanceId' when calling unlinkPaymentAcceptanceDocument");
    }
    if (documentId == null) {
      throw new ApiException(400, "Missing the required parameter 'documentId' when calling unlinkPaymentAcceptanceDocument");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId}/document/{documentId}"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()))
            .replaceAll("\\{paymentAcceptanceId}", apiClient.escapeString(paymentAcceptanceId.toString()))
            .replaceAll("\\{documentId}", apiClient.escapeString(documentId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<PaymentAcceptance> localVarReturnType = new GenericType<PaymentAcceptance>() {};
    return apiClient.invokeAPI("VaultPaymentApi.unlinkPaymentAcceptanceDocument", localVarPath, "DELETE", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Update Payment Acceptance
   * Modifies configuration of an existing payment acceptance.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param paymentAcceptanceId Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. (required)
   * @param paymentAcceptanceConfigFormat Partial payment acceptance configuration for updates. Can be used to modify the &#39;documents&#39; list to add or remove compliance document associations, or to update &#39;order&#39; information. Core price scenarios cannot be modified after  creation. (required)
   * @return PaymentAcceptance
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Payment acceptance updated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public PaymentAcceptance updatePaymentAcceptance(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID paymentAcceptanceId, @javax.annotation.Nonnull PaymentAcceptanceConfigFormat paymentAcceptanceConfigFormat) throws ApiException {
    return updatePaymentAcceptanceWithHttpInfo(vaultId, paymentAcceptanceId, paymentAcceptanceConfigFormat).getData();
  }

  /**
   * Update Payment Acceptance
   * Modifies configuration of an existing payment acceptance.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param paymentAcceptanceId Unique identifier for a payment acceptance within a vault. Used to track specific payment requests, monitor transaction status, manage associated documents, and retrieve payment history and compliance information. (required)
   * @param paymentAcceptanceConfigFormat Partial payment acceptance configuration for updates. Can be used to modify the &#39;documents&#39; list to add or remove compliance document associations, or to update &#39;order&#39; information. Core price scenarios cannot be modified after  creation. (required)
   * @return ApiResponse&lt;PaymentAcceptance&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Payment acceptance updated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<PaymentAcceptance> updatePaymentAcceptanceWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull UUID paymentAcceptanceId, @javax.annotation.Nonnull PaymentAcceptanceConfigFormat paymentAcceptanceConfigFormat) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling updatePaymentAcceptance");
    }
    if (paymentAcceptanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'paymentAcceptanceId' when calling updatePaymentAcceptance");
    }
    if (paymentAcceptanceConfigFormat == null) {
      throw new ApiException(400, "Missing the required parameter 'paymentAcceptanceConfigFormat' when calling updatePaymentAcceptance");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}/payment/acceptance/{paymentAcceptanceId}"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()))
            .replaceAll("\\{paymentAcceptanceId}", apiClient.escapeString(paymentAcceptanceId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType("application/json");
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<PaymentAcceptance> localVarReturnType = new GenericType<PaymentAcceptance>() {};
    return apiClient.invokeAPI("VaultPaymentApi.updatePaymentAcceptance", localVarPath, "PATCH", new ArrayList<>(), paymentAcceptanceConfigFormat,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
  /**
   * Generate Payment Estimate
   * Calculates payment scenarios for specified contracts and amounts. Requires at least one contract-amount pair in the request body.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param contractAmountFormat List of contract-amount pairs for which to generate payment estimates. Must contain at least one contract-amount pair. Each contract must be subscribed to the vault. The amounts should represent the desired payment values in the contract&#39;s native units. (required)
   * @return PaymentEstimate
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Payment estimate generated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public PaymentEstimate vaultGetPaymentEstimate(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull List<ContractAmountFormat> contractAmountFormat) throws ApiException {
    return vaultGetPaymentEstimateWithHttpInfo(vaultId, contractAmountFormat).getData();
  }

  /**
   * Generate Payment Estimate
   * Calculates payment scenarios for specified contracts and amounts. Requires at least one contract-amount pair in the request body.
   * @param vaultId Unique identifier for a vault container that manages payment acceptance, documents, and contract subscriptions. Used across all vault-related operations including payment processing, document management, and configuration updates. (required)
   * @param contractAmountFormat List of contract-amount pairs for which to generate payment estimates. Must contain at least one contract-amount pair. Each contract must be subscribed to the vault. The amounts should represent the desired payment values in the contract&#39;s native units. (required)
   * @return ApiResponse&lt;PaymentEstimate&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Payment estimate generated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<PaymentEstimate> vaultGetPaymentEstimateWithHttpInfo(@javax.annotation.Nonnull String vaultId, @javax.annotation.Nonnull List<ContractAmountFormat> contractAmountFormat) throws ApiException {
    // Check required parameters
    if (vaultId == null) {
      throw new ApiException(400, "Missing the required parameter 'vaultId' when calling vaultGetPaymentEstimate");
    }
    if (contractAmountFormat == null) {
      throw new ApiException(400, "Missing the required parameter 'contractAmountFormat' when calling vaultGetPaymentEstimate");
    }

    // Path parameters
    String localVarPath = "/v1/vault/{vaultId}/payment/estimate"
            .replaceAll("\\{vaultId}", apiClient.escapeString(vaultId.toString()));

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType("application/json");
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<PaymentEstimate> localVarReturnType = new GenericType<PaymentEstimate>() {};
    return apiClient.invokeAPI("VaultPaymentApi.vaultGetPaymentEstimate", localVarPath, "POST", new ArrayList<>(), contractAmountFormat,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
}
