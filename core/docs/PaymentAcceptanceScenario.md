

# PaymentAcceptanceScenario

Individual payment execution scenario within a payment acceptance.  Contains all information needed for payment processing including amounts,  blockchain addresses, expiration times, and transaction tracking.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**price** | [**ContractAmount**](ContractAmount.md) |  |  |
|**pay** | [**ContractAmount**](ContractAmount.md) |  |  |
|**address** | **String** | Blockchain-specific address format used for cryptocurrency transactions. The exact format varies by network (e.g., Ethereum uses hexadecimal addresses starting with 0x, Bitcoin uses base58 encoding). Used for contract addresses, wallet addresses, and payment destinations. |  |
|**expire** | [**Expire**](Expire.md) |  |  |
|**status** | **PaymentAcceptanceScenarioStatus** |  |  |
|**received** | [**ContractAmount**](ContractAmount.md) |  |  |
|**transactionList** | [**List&lt;BlockchainTransaction&gt;**](BlockchainTransaction.md) | List of blockchain transactions detected for this payment scenario. |  |



