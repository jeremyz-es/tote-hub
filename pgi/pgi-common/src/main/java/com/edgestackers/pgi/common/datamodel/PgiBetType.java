package com.edgestackers.pgi.common.datamodel;

import jakarta.annotation.Nullable;

public enum PgiBetType {
    WN,
    PL,
    SH,
    WP,
    WS,
    PS,
    WPS,
    EX,
    QU,
    TR,
    SU,
    DB,
    P4,
    SW,
    QU3,
    AD,
    P3,
    P6,
    ;

    @Nullable
    public static PgiBetType convertFromString(String string) {
        try {
            return PgiBetType.valueOf(string);
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }
}
