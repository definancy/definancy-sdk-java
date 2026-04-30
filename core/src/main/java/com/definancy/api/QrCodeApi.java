package com.definancy.api;

import com.definancy.ApiException;
import com.definancy.ApiClient;
import com.definancy.ApiResponse;
import com.definancy.Configuration;
import com.definancy.Pair;

import javax.ws.rs.core.GenericType;

import com.definancy.model.ErrorList;
import com.definancy.model.QrCode;
import com.definancy.model.QrCodeTransactionRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.20.0")
public class QrCodeApi {
  private ApiClient apiClient;

  public QrCodeApi() {
    this(Configuration.getDefaultApiClient());
  }

  public QrCodeApi(ApiClient apiClient) {
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
   * Generate QR-Codes for wallets
   * Generates the QR-Codes to be used by a wallet to help on the creation of a transaction request.
   * @param qrCodeTransactionRequest Contract-amount pair for which to generate the qr-codes. The amount should represent the desired payment value in the contract&#39;s unit. (required)
   * @return List&lt;QrCode&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> QR-Code generated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public List<QrCode> generateWalletQrCodes(@javax.annotation.Nonnull QrCodeTransactionRequest qrCodeTransactionRequest) throws ApiException {
    return generateWalletQrCodesWithHttpInfo(qrCodeTransactionRequest).getData();
  }

  /**
   * Generate QR-Codes for wallets
   * Generates the QR-Codes to be used by a wallet to help on the creation of a transaction request.
   * @param qrCodeTransactionRequest Contract-amount pair for which to generate the qr-codes. The amount should represent the desired payment value in the contract&#39;s unit. (required)
   * @return ApiResponse&lt;List&lt;QrCode&gt;&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> QR-Code generated successfully. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 400 </td><td> The request contains malformed data, invalid parameters, or violates API constraints. This includes validation errors, incorrect data types, missing required fields, or values outside acceptable ranges. Check the error details for specific issues. </td><td>  -  </td></tr>
       <tr><td> 401 </td><td> Authentication credentials are missing, invalid, or expired. The request lacks proper authorization headers or tokens. Clients should verify their authentication setup and ensure valid credentials are provided in subsequent requests. </td><td>  -  </td></tr>
       <tr><td> 403 </td><td> The authenticated user lacks sufficient permissions to perform this operation. While authentication was successful, the user&#39;s role or access level does not permit the requested action. Contact an administrator for access rights. </td><td>  -  </td></tr>
       <tr><td> 404 </td><td> The requested resource does not exist or has been removed. This may indicate an incorrect ID, a resource that was deleted, or a path that doesn&#39;t match any configured endpoints. Verify the resource identifier and try again. </td><td>  -  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<List<QrCode>> generateWalletQrCodesWithHttpInfo(@javax.annotation.Nonnull QrCodeTransactionRequest qrCodeTransactionRequest) throws ApiException {
    // Check required parameters
    if (qrCodeTransactionRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'qrCodeTransactionRequest' when calling generateWalletQrCodes");
    }

    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType("application/json");
    String[] localVarAuthNames = new String[] {"dpop-auth", "dpop-proof"};
    GenericType<List<QrCode>> localVarReturnType = new GenericType<List<QrCode>>() {};
    return apiClient.invokeAPI("QrCodeApi.generateWalletQrCodes", "/v1/qrcode/transaction", "POST", new ArrayList<>(), qrCodeTransactionRequest,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               localVarAuthNames, localVarReturnType, false);
  }
}
