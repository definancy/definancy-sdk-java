

# BlockchainConfirmationStats

Confirmation progress information for transactions in the confirming state, providing current status and estimated completion time for full confirmation.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**confirmed** | **Integer** | Number of blockchain confirmations observed for a transaction, indicating the level of network consensus and security. Higher numbers provide greater confidence in transaction finality. |  |
|**remaining** | **Integer** | Number of blockchain confirmations observed for a transaction, indicating the level of network consensus and security. Higher numbers provide greater confidence in transaction finality. |  |
|**eta** | **Integer** | UNIX timestamp in milliseconds representing a specific point in time. Used for tracking creation dates, expiration times, transaction timestamps, and other time-sensitive operations throughout the payment lifecycle. |  |



