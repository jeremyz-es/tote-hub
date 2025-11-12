package com.edgestackers.pgi.common.util;

import com.edgestackers.pgi.common.datamodel.PgiBetType;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import jakarta.annotation.Nullable;

public enum PgiBetTypeConverter {
    ;

    @Nullable
    public static ToteBetType convertToToteBetType(PgiBetType pgiBetType) {
        return switch (pgiBetType) {
            case WN -> ToteBetType.WIN;
            case PL -> ToteBetType.PLACE;
            case EX -> ToteBetType.EXACTA;
            case QU -> ToteBetType.QUINELLA;
            case TR -> ToteBetType.TRIFECTA;
            case SU -> ToteBetType.FIRST_FOUR;
            case DB -> ToteBetType.RUNNING_DOUBLE;
            case P4 -> ToteBetType.QUADRELLA;
            case P3 -> ToteBetType.TREBLE;
            case P6 -> ToteBetType.BIG6;
            default -> null;
        };
    }
}
