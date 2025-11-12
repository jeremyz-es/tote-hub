package com.edgestackers.tote.hub.core.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public enum TimeUtil {
    ;
    public static final DateTimeFormatter YMDHMS_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter HM_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter HMS_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter YMD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static long generateEpochNanosTimestamp() {
        Instant instant = Instant.now();
        return instant.getEpochSecond() * 1_000_000_000L + instant.getNano();
    }

    public static String sydTimeFor(long utcNanosTimestamp, DateTimeFormatter outputFormatter) {
        long utcMillis = utcNanosTimestamp / 1_000_000;
        Instant instant = Instant.ofEpochMilli(utcMillis);
        ZoneId qldZoneId = ZoneId.of("Australia/Sydney");
        ZonedDateTime qldTime = instant.atZone(qldZoneId);
        return qldTime.format(outputFormatter);
    }

    public static String qldTimeFor(long utcNanosTimestamp, DateTimeFormatter outputFormatter) {
        long utcMillis = utcNanosTimestamp / 1_000_000;
        Instant instant = Instant.ofEpochMilli(utcMillis);
        ZoneId qldZoneId = ZoneId.of("Australia/Brisbane");
        ZonedDateTime qldTime = instant.atZone(qldZoneId);
        return qldTime.format(outputFormatter);
    }
}
