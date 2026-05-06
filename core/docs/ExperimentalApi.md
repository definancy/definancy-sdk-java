# ExperimentalApi

All URIs are relative to *https://stub.definancy.com*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**experimentalPing**](ExperimentalApi.md#experimentalPing) | **GET** /v1/experimental/ping | Experimental connectivity probe |



## experimentalPing

> Status experimentalPing()

Experimental connectivity probe

Connectivity probe for the Experimental tag surface. Returns OK when
the daemon's experimental endpoints are reachable. Reserved for
future development-environment endpoints (DPoP testing aids,
fixture seeding, etc.).

### Example

```java
// Import classes:
import com.definancy.ApiClient;
import com.definancy.ApiException;
import com.definancy.Configuration;
import com.definancy.model.*;
import com.definancy.api.ExperimentalApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://stub.definancy.com");

        ExperimentalApi apiInstance = new ExperimentalApi(defaultClient);
        try {
            Status result = apiInstance.experimentalPing();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExperimentalApi#experimentalPing");
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

