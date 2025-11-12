package com.edgestackers.tote.hub.core.datamodel.core;

public enum ToteBetType {
    WIN("Win"),
    PLACE("Place"),
    QUINELLA("Quinella"),
    EXACTA("Exacta"),
    TRIFECTA("Trifecta"),
    FIRST_FOUR("FirstFour"),
    EARLY_QUADRELLA("EarlyQuadrella"),
    RUNNING_DOUBLE("RunningDouble"),
    TREBLE("Treble"),
    QUADRELLA("Quadrella"),
    DAILY_DOUBLE("DailyDouble"),
    BIG6("Big6"),
    ;

    ToteBetType(String prettyName) {
        this.prettyName = prettyName;
    }

    private final String prettyName;

    public String getPrettyName() {
        return prettyName;
    }
}
