package com.edgestackers.tote.hub.core.datamodel.context;

public enum ToteMarketCycleStatus {
    AWAITING("#1C68B5"),
    HEALTHY("#19800E");

    private final String rgbIndicator;

    ToteMarketCycleStatus(String rgbIndicator) {
        this.rgbIndicator = rgbIndicator;
    }

    public String getRgbIndicator() {
        return rgbIndicator;
    }
}
