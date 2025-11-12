package com.edgestackers.pgi.common.datamodel.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PgiScratchingMessage(@JsonProperty("is_live_scratching") boolean isLiveScratching,
                                   @JsonProperty("epoch_nanos_timestamp") long epochNanosTimestamp,
                                   @JsonProperty("program_name") String programName,
                                   @JsonProperty("race_number") int raceNumber,
                                   @JsonProperty("runner_number") int runnerNumber) {
}
