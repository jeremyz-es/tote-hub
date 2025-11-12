package com.edgestackers.tote.hub.core.datamodel.message.nitro;

import com.edgestackers.tote.hub.core.datamodel.context.NitroContextStatus;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.message.ToteMessage;
import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import com.fasterxml.jackson.annotation.JsonProperty;

public record NitroOrderGeneratorStateMessage(@JsonProperty("strategy_id") String strategyId,
                                              @JsonProperty("strategy_name") String strategyName,
                                              @JsonProperty("race_code") RaceCode raceCode,
                                              @JsonProperty("date") String date,
                                              @JsonProperty("bet_type") ToteBetType betType,
                                              @JsonProperty("jurisdiction") TotePoolJurisdiction jurisdiction,
                                              @JsonProperty("track") String track,
                                              @JsonProperty("race") int race,
                                              @JsonProperty("es_race_id") String esRaceId,
                                              @JsonProperty("state") NitroContextStatus nitroContextStatus,
                                              @JsonProperty("epoch_millis_timestamp") long epochMillisTimestamp) implements ToteMessage {
}
