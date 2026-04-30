

# PaymentAcceptanceOrder

Commercial order context and metadata associated with a payment acceptance.  Provides business context for payment processing including order details,  descriptions, and structured metadata for integration with external systems.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **String** | External order reference identifier from the originating system  or business process. |  [optional] |
|**description** | **String** | Human-readable description of the order or commercial transaction  being processed. |  [optional] |
|**schema** | **Map&lt;String, Object&gt;** | Metadata schema definition describing the structure and validation  rules for the order data payload. |  [optional] |
|**data** | **Map&lt;String, Object&gt;** | Order-specific data payload containing business information relevant  to the commercial transaction. |  [optional] |



