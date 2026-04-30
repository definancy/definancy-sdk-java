

# ContractInfo

Comprehensive metadata about a contract including deployment details, subscribed vaults, operational status, and version tracking. Provides all information needed to understand contract capabilities and current usage.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**vaults** | **List&lt;String&gt;** | List of vault identifiers that are currently subscribed to this contract and can process payments using it. |  |
|**address** | **String** | Blockchain-specific address format used for cryptocurrency transactions. The exact format varies by network (e.g., Ethereum uses hexadecimal addresses starting with 0x, Bitcoin uses base58 encoding). Used for contract addresses, wallet addresses, and payment destinations. |  |
|**media** | [**ContractMediaMap**](ContractMediaMap.md) |  |  |
|**minorUnit** | [**Amount**](Amount.md) |  |  |
|**traits** | [**ContractTraits**](ContractTraits.md) |  |  |
|**note** | **String** | Optional free-form text field for additional context, remarks, or annotations. Provides flexibility for storing supplementary information that doesn&#39;t fit into structured fields. |  [optional] |
|**status** | **ContractStatus** |  |  |
|**version** | [**Version**](Version.md) |  |  |



