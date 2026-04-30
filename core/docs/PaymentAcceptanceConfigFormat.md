

# PaymentAcceptanceConfigFormat

Payment acceptance configuration for API requests. Accepts amount data in flexible formats, allowing callers to provide either display values or raw precision data for payment scenarios.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**order** | [**PaymentAcceptanceOrder**](PaymentAcceptanceOrder.md) |  |  [optional] |
|**documents** | **List&lt;UUID&gt;** | Optional list of document IDs to associate with this payment  acceptance for compliance purposes. |  [optional] |
|**priceScenarios** | [**List&lt;ContractAmountFormat&gt;**](ContractAmountFormat.md) | Required payment obligations as contract-amount pairs. Must contain  at least one scenario when creating new payment acceptance. |  [optional] |



