

# BlockchainTransaction

Complete blockchain transaction record including addresses, amounts, timestamps, and confirmation status for comprehensive payment tracking and verification throughout the transaction lifecycle.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **String** | Unique blockchain transaction identifier or hash. |  |
|**ts** | **Integer** | Timestamp when transaction was first detected or confirmed. |  |
|**sender** | **String** | Blockchain address that sent the funds for this transaction. |  |
|**receiver** | **String** | Blockchain address that received the funds from this transaction. |  |
|**amount** | [**ContractAmount**](ContractAmount.md) | Precise amount transferred in this transaction with contract context. |  |
|**status** | **BlockchainTransactionStatus** | Current confirmation and processing status of this transaction. |  |
|**confirmationStats** | [**BlockchainConfirmationStats**](BlockchainConfirmationStats.md) | Detailed confirmation progress and timing information. |  |



