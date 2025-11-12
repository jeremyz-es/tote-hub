package com.edgestackers.tote.hub.core.metadata;

import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import com.fasterxml.jackson.annotation.JsonProperty;

public record EsRaceMetadata(@JsonProperty("race_code") RaceCode raceCode,
                             @JsonProperty("date") String raceDateYmd,
                             @JsonProperty("es_track_id") String esTrackId,
                             @JsonProperty("es_race_id") String esRaceId,
                             @JsonProperty("track_name") String track,
                             @JsonProperty("race_number") int race,
                             @JsonProperty("race_type") String raceType,
                             @JsonProperty("non_tab") boolean nonTab,
                             @JsonProperty("race_start_time_utc_nanos") long raceStartTimeUtcNanos,
                             @JsonProperty("abandoned") boolean abandoned) {}
