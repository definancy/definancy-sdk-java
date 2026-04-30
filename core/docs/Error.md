

# Error

Standardized error response structure providing categorized error codes and human-readable messages. Used consistently across all API endpoints to help clients understand and handle different types of failures.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**code** | **String** | Structured error code in AAA-999 format where AAA represents the error category (e.g., AUT for authentication, VAL for validation) and 999 is a specific error number. Used for programmatic error handling. |  |
|**message** | **String** | Human-readable error description explaining what went wrong and potentially how to resolve the issue. Suitable for displaying to end users or logging for debugging purposes. |  |



