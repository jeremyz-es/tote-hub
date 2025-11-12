package com.edgestackers.tote.hub.core.datamodel.context;

public enum OrderGatewayExecutionStatus {
    AWAITING_ORDER("#B8860B"),
    ORDER_ACCEPTED("#33D22E"),
    RACE_CLOSED("#6600CC"),
    SCRATCHED_RUNNERS("#CDD22E"),
    ORDER_REJECTION("#D22E2E"),
    ERRONEOUS_ORDER("#D22E2E");

    private final String rgbIndicator;

    OrderGatewayExecutionStatus(String rgbIndicator) {
        this.rgbIndicator = rgbIndicator;
    }

    public String getRgbIndicator() {
        return rgbIndicator;
    }
}
