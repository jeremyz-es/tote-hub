package com.edgestackers.pgi.common.datamodel.metadata;

import com.edgestackers.pgi.common.datamodel.PgiBetType;
import com.edgestackers.pgi.common.datamodel.PgiCountryCode;
import com.edgestackers.pgi.common.datamodel.PgiRaceType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PgiRaceMetadata(@JsonProperty("es_race_id") String esRaceId,
                              @JsonProperty("race_code") String raceCode,
                              @JsonProperty("track") String track,
                              @JsonProperty("country") String country,
                              @JsonProperty("date") String date,
                              @JsonProperty("race_number") int raceNumber,
                              @JsonProperty("program_name") String programName,
                              @JsonProperty("program_long_name") String programLongName,
                              @JsonProperty("abandoned") boolean abandoned,
                              @JsonProperty("live_runners") List<Integer> liveRunners,
                              @JsonProperty("scratched_runners") List<Integer> scratchedRunners,
                              @JsonProperty("open_bet_types") List<PgiBetType> openBetTypes,
                              @JsonProperty("pgi_race_type") PgiRaceType pgiRaceType,
                              @JsonProperty("pgi_country_code") PgiCountryCode pgiCountryCode) {}