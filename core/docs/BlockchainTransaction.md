

# BlockchainTransaction

Complete blockchain transaction record including addresses, amounts, timestamps, and confirmation status for comprehensive payment tracking and verification throughout the transaction lifecycle.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **String** | Unique blockchain transaction hash or identifier as recorded on the  blockchain network. Used to track and verify transactions on the network.  This hash serves as the definitive reference for locating transactions  in blockchain explorers and verification systems. |  |
|**ts** | **Integer** | UNIX timestamp in milliseconds representing a specific point in time. Used for tracking creation dates, expiration times, transaction timestamps, and other time-sensitive operations throughout the payment lifecycle. |  |
|**sender** | **String** | Blockchain-specific address format used for cryptocurrency transactions. The exact format varies by network (e.g., Ethereum uses hexadecimal addresses starting with 0x, Bitcoin uses base58 encoding). Used for contract addresses, wallet addresses, and payment destinations. |  |
|**receiver** | **String** | Blockchain-specific address format used for cryptocurrency transactions. The exact format varies by network (e.g., Ethereum uses hexadecimal addresses starting with 0x, Bitcoin uses base58 encoding). Used for contract addresses, wallet addresses, and payment destinations. |  |
|**amount** | [**ContractAmount**](ContractAmount.md) |  |  |
|**status** | **BlockchainTransactionStatus** |  |  |
|**confirmationStats** | [**BlockchainConfirmationStats**](BlockchainConfirmationStats.md) |  |  |



