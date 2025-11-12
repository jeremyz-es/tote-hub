package com.edgestackers.tote.hub.core.datamodel.message.turbo;

import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.message.ToteMessage;
import com.edgestackers.tote.hub.core.datamodel.turbo.TurboExoticTheoModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record ExoticTheoMessage(@JsonProperty("generated_at_epoch_nanos") long generatedAtEpochNanos,
                                @JsonProperty("es_race_id") String esRaceId,
                                @JsonProperty("probabilities") Map<String, Double> probabilities,
                                @JsonProperty("bet_type") ToteBetType betType,
                                @JsonProperty("race_code") RaceCode esRaceCode,
                                @JsonProperty("exotic_theo_id") String exoticTheoId,
                                @JsonProperty("exotic_theo_model") TurboExoticTheoModel exoticTheoModel,
                                @JsonProperty("model_major_version") int theoModelMajorVersion,
                                @JsonProperty("model_minor_version") int theoModelMinorVersion,
                                @JsonProperty("calculation_time_millis") double calculationTimeMillis,
                                @JsonProperty("pricing_model_source") String pricingModelSource) implements ToteMessage {
}

