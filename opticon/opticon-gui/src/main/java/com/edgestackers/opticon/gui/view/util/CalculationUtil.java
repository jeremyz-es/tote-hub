package com.edgestackers.opticon.gui.view.util;

import java.time.Instant;

public enum CalculationUtil {
    ;

    public static String timeUntilRace(long raceStartTimeUtcNanos) {
        long currentTimeUtcNanos = Instant.now().toEpochMilli() * 1_000_000;
        long timeRemainingNanos = raceStartTimeUtcNanos - currentTimeUtcNanos;
        long timeRemainingSeconds = timeRemainingNanos / 1_000_000_000;
        boolean isNegative = timeRemainingSeconds < 0;
        timeRemainingSeconds = Math.abs(timeRemainingSeconds);
        long hours = timeRemainingSeconds / 3600;
        long minutes = (timeRemainingSeconds % 3600) / 60;
        long seconds = timeRemainingSeconds % 60;
        return (isNegative ? "-" : "") + String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
