

# QrCodeTransactionRequest

Request object containing the essential payment parameters needed to generate a blockchain transaction QR code. Specifies the destination, amount, and token contract details that will be encoded into a scannable QR format for cryptocurrency wallet applications.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**contractId** | [**ContractId**](ContractId.md) |  |  |
|**amount** | [**AmountFormat**](AmountFormat.md) |  |  |
|**address** | **String** | Blockchain-specific address format used for cryptocurrency transactions. The exact format varies by network (e.g., Ethereum uses hexadecimal addresses starting with 0x, Bitcoin uses base58 encoding). Used for contract addresses, wallet addresses, and payment destinations. |  |



