package com.edgestackers.tote.hub.core.metadata;

import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MasterField(@JsonProperty("race_code") RaceCode esRaceCode,
                          @JsonProperty("es_run_id") String esRunId,
                          @JsonProperty("es_race_id") String esRaceId,
                          @JsonProperty("es_runner_id") String esRunnerId,
                          @JsonProperty("date") String date,
                          @JsonProperty("track_name") String track,
                          @JsonProperty("race_number") int race,
                          @JsonProperty("runner_number") int tab,
                          @JsonProperty("runner_name_clean") String runnerName,
                          @JsonProperty("field_size") int fieldSize) {
}
