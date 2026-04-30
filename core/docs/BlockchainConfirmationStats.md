

# BlockchainConfirmationStats

Confirmation progress information for transactions in the confirming state, providing current status and estimated completion time for full confirmation.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**confirmed** | **Integer** | Number of confirmations currently observed for this transaction. |  |
|**remaining** | **Integer** | Number of additional confirmations needed to reach required threshold. |  |
|**eta** | **Integer** | Estimated timestamp when the transaction will reach full confirmation based on network block time averages. |  |



