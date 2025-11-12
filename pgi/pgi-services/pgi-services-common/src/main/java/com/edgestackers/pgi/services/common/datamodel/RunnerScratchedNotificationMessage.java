package com.edgestackers.pgi.services.common.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RunnerScratchedNotificationMessage(@JsonProperty("toteId") PgiTotePoolId toteId,
                                                 @JsonProperty("programName") String programName,
                                                 @JsonProperty("race") int race,
                                                 @JsonProperty("runner_number") int runnerNumber) implements PgiEventServiceNotificationMessage {
}
