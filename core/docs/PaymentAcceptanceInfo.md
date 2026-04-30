

# PaymentAcceptanceInfo

Payment acceptance operational state and lifecycle information. Contains  current processing status, payment scenarios, compliance state, and  version tracking for audit and monitoring purposes.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**status** | [**PaymentAcceptanceStatus**](PaymentAcceptanceStatus.md) | Current processing state of the payment acceptance. |  |
|**version** | [**Version**](Version.md) | Version tracking information for audit and synchronization purposes. |  |
|**scenarios** | [**List&lt;PaymentAcceptanceScenario&gt;**](PaymentAcceptanceScenario.md) | Active payment execution scenarios with blockchain addresses,  amounts, and transaction tracking information. |  |
|**compliance** | [**Compliance**](Compliance.md) | Current compliance status and requirements for all contracts in this payment acceptance. |  |
|**warnings** | [**List&lt;VelocityWarning&gt;**](VelocityWarning.md) | Velocity warnings that fired during the payment creation check. Present only when warn-mode limits were exceeded. |  [optional] |



