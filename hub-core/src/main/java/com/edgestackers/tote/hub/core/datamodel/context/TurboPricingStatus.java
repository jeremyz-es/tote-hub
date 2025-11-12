package com.edgestackers.tote.hub.core.datamodel.context;

public enum TurboPricingStatus {
    AWAITING("#1C68B5"),
    PACMAN_FLUCS_MISSING("#D22E2E"),
    WIN_THEOS_MISSING("#D22E2E"),
    STALE_BASE_RACE_SUMMARY("#D22E2E"),
    OK("#19800E");

    private final String rgbIndicator;

    TurboPricingStatus(String rgbIndicator) {
        this.rgbIndicator = rgbIndicator;
    }

    public String getRgbIndicator() {
        return rgbIndicator;
    }
}
