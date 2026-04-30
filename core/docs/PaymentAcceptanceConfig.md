

# PaymentAcceptanceConfig

Complete payment acceptance configuration used in API responses. Provides full amount information with both display values and raw precision data for all payment scenarios.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**order** | [**PaymentAcceptanceOrder**](PaymentAcceptanceOrder.md) |  |  [optional] |
|**documents** | **List&lt;UUID&gt;** | Optional list of document IDs to associate with this payment  acceptance for compliance purposes. |  [optional] |
|**priceScenarios** | [**List&lt;ContractAmount&gt;**](ContractAmount.md) | Required payment obligations as contract-amount pairs. Must contain  at least one scenario when creating new payment acceptance. |  [optional] |



