

# Amount

Precise representation of a amount combining a raw value with decimal precision information. This structure ensures accurate handling of token amounts across  different assets with varying decimal places.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**value** | **String** | Amount value in the unit of the asset (e.g., btc). |  |
|**raw** | **String** | Raw amount value in the smallest unit of the asset (e.g., satoshi for btc). |  |
|**decimals** | **Integer** | Number of decimal places to apply when displaying the amount to users. |  |



