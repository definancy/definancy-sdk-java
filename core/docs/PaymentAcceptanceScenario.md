

# PaymentAcceptanceScenario

Individual payment execution scenario within a payment acceptance.  Contains all information needed for payment processing including amounts,  blockchain addresses, expiration times, and transaction tracking.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**price** | [**ContractAmount**](ContractAmount.md) | Original requested payment amount as specified in the payment acceptance. |  |
|**pay** | [**ContractAmount**](ContractAmount.md) | Calculated required payment amount including fees and adjustments. |  |
|**address** | **String** | Blockchain address where payment should be sent for this scenario. |  |
|**expire** | [**Expire**](Expire.md) |  |  |
|**status** | **PaymentAcceptanceScenarioStatus** | Current execution state of this payment scenario. |  |
|**received** | [**ContractAmount**](ContractAmount.md) | Amount currently received for this payment scenario. |  |
|**transactionList** | [**List&lt;BlockchainTransaction&gt;**](BlockchainTransaction.md) | List of blockchain transactions detected for this payment scenario. |  |



