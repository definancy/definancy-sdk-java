

# ComplianceScenario

Compliance requirements and status for a specific contract within a  payment acceptance. Tracks required document types, submitted documents,  and overall compliance fulfillment status.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**contractId** | [**ContractId**](ContractId.md) |  |  |
|**required** | **List&lt;DocumentType&gt;** | List of document types that must be submitted and validated  for compliance with this contract&#39;s requirements. |  |
|**submitted** | **List&lt;UUID&gt;** | List of document IDs that have been submitted for compliance  verification for this contract. |  [optional] |
|**status** | **ComplianceScenarioStatus** |  |  |



