package com.definancy.api;

import com.definancy.ApiException;
import com.definancy.ApiClient;
import com.definancy.ApiResponse;
import com.definancy.Configuration;
import com.definancy.Pair;

import javax.ws.rs.core.GenericType;

import com.definancy.model.ErrorList;
import com.definancy.model.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.20.0")
public class ProbeApi {
  private ApiClient apiClient;

  public ProbeApi() {
    this(Configuration.getDefaultApiClient());
  }

  public ProbeApi(ApiClient apiClient) {
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
   * Health Check
   * Verifies service liveness. Returns OK when operational. This endpoint is used to check if the service is alive and ready to handle requests,  ensuring that your applications can rely on the Definancy service for their operations.
   * @return Status
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> OK. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Status healthyCheck() throws ApiException {
    return healthyCheckWithHttpInfo().getData();
  }

  /**
   * Health Check
   * Verifies service liveness. Returns OK when operational. This endpoint is used to check if the service is alive and ready to handle requests,  ensuring that your applications can rely on the Definancy service for their operations.
   * @return ApiResponse&lt;Status&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> OK. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Status> healthyCheckWithHttpInfo() throws ApiException {
    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    GenericType<Status> localVarReturnType = new GenericType<Status>() {};
    return apiClient.invokeAPI("ProbeApi.healthyCheck", "/v1/healthy", "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               null, localVarReturnType, false);
  }
  /**
   * Ready Check
   * Verifies service readiness. Returns OK when ready to accept traffic. This endpoint checks if the service is ready to handle incoming requests, ensuring that your applications can rely on the Definancy service for their operations.
   * @return Status
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> OK. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public Status readyCheck() throws ApiException {
    return readyCheckWithHttpInfo().getData();
  }

  /**
   * Ready Check
   * Verifies service readiness. Returns OK when ready to accept traffic. This endpoint checks if the service is ready to handle incoming requests, ensuring that your applications can rely on the Definancy service for their operations.
   * @return ApiResponse&lt;Status&gt;
   * @throws ApiException if fails to make API call
   * @http.response.details
     <table border="1">
       <caption>Response Details</caption>
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> OK. </td><td>  * Cache-Control -  <br>  </td></tr>
       <tr><td> 0 </td><td> An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. </td><td>  -  </td></tr>
     </table>
   */
  public ApiResponse<Status> readyCheckWithHttpInfo() throws ApiException {
    String localVarAccept = apiClient.selectHeaderAccept("application/json");
    String localVarContentType = apiClient.selectHeaderContentType();
    GenericType<Status> localVarReturnType = new GenericType<Status>() {};
    return apiClient.invokeAPI("ProbeApi.readyCheck", "/v1/ready", "GET", new ArrayList<>(), null,
                               new LinkedHashMap<>(), new LinkedHashMap<>(), new LinkedHashMap<>(), localVarAccept, localVarContentType,
                               null, localVarReturnType, false);
  }
}
