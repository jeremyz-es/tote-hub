package com.edgestackers.tote.hub.core.datamodel.message.theos;

import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import com.edgestackers.tote.hub.core.datamodel.message.ToteMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RaceTheosUpdateMessage(@JsonProperty("race_code") RaceCode esRaceCode,
                                     @JsonProperty("date") String date,
                                     @JsonProperty("track") String track,
                                     @JsonProperty("race_number") int race,
                                     @JsonProperty("price_type") EsPricingType priceType,
                                     @JsonProperty("model_name") ToteTheoType theoType,
                                     @JsonProperty("es_race_id") String esRaceId,
                                     @JsonProperty("run_theos") List<RunTheoUpdate> runTheoUpdates,
                                     @JsonProperty("theo_id") String theoId) implements ToteMessage {
}


