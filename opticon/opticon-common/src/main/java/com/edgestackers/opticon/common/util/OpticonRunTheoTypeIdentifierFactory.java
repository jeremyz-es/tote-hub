package com.edgestackers.opticon.common.util;

import com.edgestackers.tote.hub.core.datamodel.turbo.TurboExoticTheoModel;

public enum OpticonRunTheoTypeIdentifierFactory {
    ;

    public static String createRunTheoTypeIdentifier(TurboExoticTheoModel theoModel, int majorVersion, int minorVersion) {
        return String.format("%s_V%s.%s", theoModel, majorVersion, minorVersion);
    }
}
