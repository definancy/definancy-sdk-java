# ProbeApi

All URIs are relative to *https://stub.definancy.com*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**healthyCheck**](ProbeApi.md#healthyCheck) | **GET** /v1/healthy | Health Check |
| [**readyCheck**](ProbeApi.md#readyCheck) | **GET** /v1/ready | Ready Check |



## healthyCheck

> Status healthyCheck()

Health Check

Verifies service liveness. Returns OK when operational.
This endpoint is used to check if the service is alive and ready to handle requests, 
ensuring that your applications can rely on the Definancy service for their operations.

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.model.*;
import com.definancy.api.ProbeApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://stub.definancy.com");

        ProbeApi apiInstance = new ProbeApi(defaultClient);
        try {
            Status result = apiInstance.healthyCheck();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ProbeApi#healthyCheck");
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

[**Status**](Status.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK. |  * Cache-Control -  <br>  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |


## readyCheck

> Status readyCheck()

Ready Check

Verifies service readiness. Returns OK when ready to accept traffic.
This endpoint checks if the service is ready to handle incoming requests, ensuring
that your applications can rely on the Definancy service for their operations.

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.model.*;
import com.definancy.api.ProbeApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://stub.definancy.com");

        ProbeApi apiInstance = new ProbeApi(defaultClient);
        try {
            Status result = apiInstance.readyCheck();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ProbeApi#readyCheck");
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

[**Status**](Status.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK. |  * Cache-Control -  <br>  |
| **0** | An unexpected server error occurred while processing the request. This indicates an internal system issue that prevented successful completion. The error details may provide additional context for debugging and support purposes. |  -  |

