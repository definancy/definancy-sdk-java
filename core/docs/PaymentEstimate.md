

# PaymentEstimate

Comprehensive payment estimation result providing calculated scenarios and compliance requirements for requested contract amounts. Generated in real-time to reflect current network conditions and fee structures.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**ts** | **Integer** | UNIX timestamp in milliseconds representing a specific point in time. Used for tracking creation dates, expiration times, transaction timestamps, and other time-sensitive operations throughout the payment lifecycle. |  |
|**scenarios** | [**List&lt;PaymentEstimateScenario&gt;**](PaymentEstimateScenario.md) | Generated payment scenarios. |  |
|**compliance** | [**Compliance**](Compliance.md) |  |  |



