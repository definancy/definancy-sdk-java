

# PaymentAcceptanceInfo

Payment acceptance operational state and lifecycle information. Contains  current processing status, payment scenarios, compliance state, and  version tracking for audit and monitoring purposes.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | [**PaymentAcceptanceStatus**](PaymentAcceptanceStatus.md) |  |  |
|**version** | [**Version**](Version.md) |  |  |
|**scenarios** | [**List&lt;PaymentAcceptanceScenario&gt;**](PaymentAcceptanceScenario.md) | Active payment execution scenarios with blockchain addresses,  amounts, and transaction tracking information. |  |
|**compliance** | [**Compliance**](Compliance.md) |  |  |



