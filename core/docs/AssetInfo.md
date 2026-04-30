

# AssetInfo

Digital asset metadata.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**name** | **String** | Human-readable resource identifier that follows standard naming conventions for display purposes. Used for network names, asset names, and other user-facing identifiers that need to be presentable in user interfaces and documentation. |  |
|**media** | [**AssetMediaMap**](AssetMediaMap.md) |  |  |
|**website** | **URI** | Valid HTTP or HTTPS URL pointing to external resources such as asset websites, network documentation, or media assets. Must include the protocol scheme and be properly formatted for web access. |  |
|**status** | **AssetStatus** |  |  |
|**networks** | **List&lt;String&gt;** | Supported networks for this asset. |  |
|**version** | [**Version**](Version.md) |  |  |



