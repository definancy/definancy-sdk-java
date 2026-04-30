

# NetworkInfo

Comprehensive metadata about a network including display information, operational status, supported assets, and version tracking. Provides all information needed to understand network capabilities.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**name** | **String** | Human-readable resource identifier that follows standard naming conventions for display purposes. Used for network names, asset names, and other user-facing identifiers that need to be presentable in user interfaces and documentation. |  |
|**media** | [**NetworkMediaMap**](NetworkMediaMap.md) |  |  |
|**website** | **URI** | Valid HTTP or HTTPS URL pointing to external resources such as asset websites, network documentation, or media assets. Must include the protocol scheme and be properly formatted for web access. |  |
|**status** | **NetworkStatus** |  |  |
|**assets** | **List&lt;String&gt;** | Supported assets on this network. |  |
|**timeToSettle** | **Integer** | Time in milliseconds representing an amount of time. Used for expiration cut-offs, and other time-sensitive operations throughout  the payment lifecycle. |  |
|**version** | [**Version**](Version.md) |  |  |



