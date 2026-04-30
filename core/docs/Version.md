

# Version

Version tracking metadata for resources that change over time. Provides sequence numbering and timestamps for creation and modification to support optimistic locking and change tracking.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**sequence** | **Integer** | Monotonically increasing version number that increments with each update. Used for optimistic concurrency control and change detection. |  |
|**createdAt** | **Integer** | Timestamp when this resource was initially created. |  |
|**updatedAt** | **Integer** | Timestamp when this resource was last modified. |  |



