

# VelocityLimitFormat

Velocity limit configuration. The unique key per scope is `windowMinutes`. Set `windowMinutes` to 0 for \"single payment cap\" — no time aggregation; the check compares the requested amount alone against the limit. The limit's `amount` may be denominated in any contract; at check time it is converted to the account's velocity reference contract via a market quote.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**windowMinutes** | **Integer** | Rolling window length in minutes. 0 means single payment cap. |  |
|**amount** | [**ContractAmountFormat**](ContractAmountFormat.md) | Limit amount, denominated in any contract. |  |
|**mode** | **VelocityMode** |  |  |



