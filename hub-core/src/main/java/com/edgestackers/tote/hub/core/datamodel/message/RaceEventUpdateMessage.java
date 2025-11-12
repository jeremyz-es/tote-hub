package com.edgestackers.tote.hub.core.datamodel.message;

import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import com.edgestackers.tote.hub.core.datamodel.core.ToteRaceEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RaceEventUpdateMessage(@JsonProperty("race_code") RaceCode esRaceCode,
                                     @JsonProperty("es_race_id") String esRaceId,
                                     @JsonProperty("track_name") String trackName,
                                     @JsonProperty("race_number") int race,
                                     @JsonProperty("event") ToteRaceEvent ToteRaceEvent,
                                     @JsonProperty("server_epoch_nanos_ts") long serverEpochNanoTs,
                                     @JsonProperty("received_epoch_nanos_ts") long receivedEpochNanoTs) implements ToteMessage {
}
