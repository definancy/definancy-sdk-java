

# PaymentAcceptanceConfigBase

Base configuration for payment acceptance operations containing optional commercial context and document associations. Serves as the foundation for both request and response payment acceptance structures.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**order** | [**PaymentAcceptanceOrder**](PaymentAcceptanceOrder.md) | Optional commercial order context providing business information  about the transaction being processed. |  [optional] |
|**documents** | **List&lt;UUID&gt;** | Optional list of document IDs to associate with this payment  acceptance for compliance purposes. |  [optional] |



