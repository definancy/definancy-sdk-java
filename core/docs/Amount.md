

# Amount

Precise representation of a amount combining a raw value with decimal precision information. This structure ensures accurate handling of token amounts across  different assets with varying decimal places.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**value** | **String** | String representation of a decimal number used for precise handling of large  numbers without floating-point precision issues. Supports values up to 78 digits to accommodate various asset denominations and high-precision calculations. |  |
|**raw** | **String** | String representation of a non-negative integer used for precise handling of large numbers without floating-point precision issues. Supports values up to 78 digits to accommodate various asset denominations and high-precision calculations. |  |
|**decimals** | **Integer** | Number of decimal places used for displaying amounts. For example, USDC typically uses 6 decimals, while Ethereum uses 18. This determines how the raw value should be formatted for user presentation. |  |



