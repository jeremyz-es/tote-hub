package com.edgestackers.tote.hub.core.datamodel.message.scratching;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ToteScratchingMessage(@JsonProperty("is_live_scratching") boolean isLiveScratching,
                                    @JsonProperty("epoch_nanos_timestamp") long epochNanosTimestamp,
                                    @JsonProperty("es_race_id") String esRaceId,
                                    @JsonProperty("runner_number") int runnerNumber,
                                    @JsonProperty("source") String source) {
}
