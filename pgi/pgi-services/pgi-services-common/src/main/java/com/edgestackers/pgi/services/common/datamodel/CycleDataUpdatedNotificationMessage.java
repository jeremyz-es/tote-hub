package com.edgestackers.pgi.services.common.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CycleDataUpdatedNotificationMessage(@JsonProperty("toteId") PgiTotePoolId toteId,
                                                  @JsonProperty("programName") String programName,
                                                  @JsonProperty("race") int race) implements PgiEventServiceNotificationMessage {

}
