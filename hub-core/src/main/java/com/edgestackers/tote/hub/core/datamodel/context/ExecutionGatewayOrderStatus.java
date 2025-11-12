package com.edgestackers.tote.hub.core.datamodel.context;

public enum ExecutionGatewayOrderStatus {
    AWAITING_ORDER("#1C68B5"),
    ORDER_INFLIGHT("#0000FF"),
    ORDER_ACCEPTED("#19800E"),
    ORDER_REJECTED("#D22E2E"),
    ERRONEOUS_ORDER("#FFA500"),
    ;

    ExecutionGatewayOrderStatus(String rgbIndicator) {
        this.rgbIndicator = rgbIndicator;
    }

    private final String rgbIndicator;

    public String getRgbIndicator() {
        return rgbIndicator;
    }
}
