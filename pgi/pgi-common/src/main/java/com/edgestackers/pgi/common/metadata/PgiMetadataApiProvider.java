package com.edgestackers.pgi.common.metadata;


import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;

enum PgiMetadataApiProvider {
    ;

    static String pgiRaceMetadataEndpointFor(String baseEndpoint, RaceCode raceType, String date) {
        return String.format("%s/v1/metadata/pgi/%s/AU/%s", baseEndpoint, raceType, date);
    }
}
