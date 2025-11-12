package com.edgestackers.tote.hub.core.datamodel.message.pacman;

import com.edgestackers.tote.hub.core.datamodel.message.ToteMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record PacmanRaceFlucsMessage(@JsonProperty("es_race_id") String esRaceId,
                                     @JsonProperty("run_flucs") Map<String, PacmanRunFlucsMessage> runFlucs,
                                     @JsonProperty("epoch_nanos_timestamp") long epochNanosTimestamp) implements ToteMessage {
}
