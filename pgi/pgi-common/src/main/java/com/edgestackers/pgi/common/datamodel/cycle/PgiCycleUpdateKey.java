package com.edgestackers.pgi.common.datamodel.cycle;

import com.edgestackers.pgi.common.datamodel.PgiBetType;

public record PgiCycleUpdateKey(String programName,
                                int race,
                                PgiBetType pgiBetType) {
}
