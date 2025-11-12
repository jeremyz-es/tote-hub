package com.edgestackers.pgi.common.datamodel;


import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;

public enum PgiBetTypeConverter {
    ;

    public static PgiBetType convertToteBetType(ToteBetType toteBetType) {
        return switch (toteBetType) {
            case WIN             -> PgiBetType.WN;
            case PLACE           -> PgiBetType.PL;
            case QUINELLA        -> PgiBetType.QU;
            case EXACTA          -> PgiBetType.EX;
            case TRIFECTA        -> PgiBetType.TR;
            case FIRST_FOUR      -> PgiBetType.SU;
            case EARLY_QUADRELLA -> PgiBetType.P4;
            case RUNNING_DOUBLE  -> PgiBetType.DB;
            case TREBLE          -> PgiBetType.P3;
            case QUADRELLA       -> PgiBetType.P4;
            case DAILY_DOUBLE    -> PgiBetType.AD;
            case BIG6            -> PgiBetType.P6;
        };
    }
}
