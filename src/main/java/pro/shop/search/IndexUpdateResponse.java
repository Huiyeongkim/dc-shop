package pro.shop.search;

public record IndexUpdateResponse(boolean created, boolean settingsUpdated, boolean mappingUpdated) {
}
