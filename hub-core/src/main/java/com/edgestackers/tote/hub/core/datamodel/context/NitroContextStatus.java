package com.edgestackers.tote.hub.core.datamodel.context;

public enum NitroContextStatus {
    AWAITING_DIVIDEND("#CC0066"),
    AWAITING_THEO("#994C00"),
    AWAITING_PRICING("#1C68B5"),
    AWAITING_TRIGGER("#B8860B"),
    EXEC_TRIGGER_MISSING("#D22E2E"),
    DIVIDEND_MISSING("#D22E2E"),
    THEO_MISSING("#D22E2E"),
    PRICING_MISSING("#D22E2E"),
    DIVIDEND_STALE("#CDD22E"),
    THEO_STALE("#CDD22E"),
    PRICING_STALE("#CDD22E"),
    NO_BETS("#6600CC"),
    NITRO_TRIGGERED("#19800E"),
    ;

    NitroContextStatus(String rgbIndicator) {
        this.rgbIndicator = rgbIndicator;
    }

    private final String rgbIndicator;

    public String getRgbIndicator() {
        return rgbIndicator;
    }
}
