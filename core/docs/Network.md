

# Network

Complete network definition combining identification, metadata, and configuration. Represents a supported network.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **String** | Unique lowercase alphanumeric identifier for a network. Examples include &#39;ethereum&#39; for Ethereum, &#39;algorand&#39; for Algorand, or &#39;bitcoin&#39; for Bitcoin. Used throughout the API to specify which  network operations should target. |  |
|**info** | [**NetworkInfo**](NetworkInfo.md) |  |  |
|**config** | [**NetworkConfig**](NetworkConfig.md) |  |  |



