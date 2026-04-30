

# VelocityWarning

Emitted in the response of estimate/create endpoints when a warn-mode velocity limit is exceeded but the request was still allowed to proceed.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**scope** | **VelocityScope** |  |  |
|**windowMinutes** | **Integer** |  |  |
|**limit** | [**ContractAmount**](ContractAmount.md) | The configured limit (in its original contract). |  |
|**used** | [**Amount**](Amount.md) | Total exposure used so far, normalized to the account reference contract. |  |



