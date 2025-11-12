package com.edgestackers.pgi.services.common.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RaceOfficialNotificationMessage(@JsonProperty("tote_id") PgiTotePoolId toteId,
                                              @JsonProperty("program_name") String programName,
                                              @JsonProperty("race") int race) implements PgiEventServiceNotificationMessage {
}
