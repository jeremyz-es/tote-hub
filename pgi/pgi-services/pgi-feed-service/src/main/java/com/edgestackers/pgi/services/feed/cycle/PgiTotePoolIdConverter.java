package com.edgestackers.pgi.services.feed.cycle;

import com.edgestackers.pgi.services.common.datamodel.PgiTotePoolId;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;

public enum PgiTotePoolIdConverter {
    ;

    public static TotePoolJurisdiction convertPgiTotePoolId(PgiTotePoolId pgiTotePoolId) {
        return switch (pgiTotePoolId) {
            case BTNV_NSW -> TotePoolJurisdiction.NSW;
            case BTNV_QLD -> TotePoolJurisdiction.QLD;
            case BTNV_VIC -> TotePoolJurisdiction.VIC;
        };
    }
}
