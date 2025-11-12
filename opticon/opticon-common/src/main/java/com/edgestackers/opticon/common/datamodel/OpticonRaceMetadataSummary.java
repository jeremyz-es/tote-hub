package com.edgestackers.opticon.common.datamodel;

import com.edgestackers.tote.hub.core.datamodel.core.RaceType;

public record OpticonRaceMetadataSummary(RaceType raceType,
                                         String date,
                                         String esTrackId,
                                         String esRaceId,
                                         long raceStartTimeEpochNanos) {
}
