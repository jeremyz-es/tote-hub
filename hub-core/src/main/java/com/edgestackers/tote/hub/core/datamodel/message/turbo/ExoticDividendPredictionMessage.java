package com.edgestackers.tote.hub.core.datamodel.message.turbo;

import com.edgestackers.tote.hub.core.datamodel.core.RaceCode;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.message.ToteMessage;
import com.edgestackers.tote.hub.core.datamodel.turbo.TurboExoticDividendModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record ExoticDividendPredictionMessage(@JsonProperty("generated_at_epoch_nanos") long generatedAtEpochNanos,
                                              @JsonProperty("cycle_pool_update_id") String cyclePoolUpdateId,
                                              @JsonProperty("race_code") RaceCode raceCode,
                                              @JsonProperty("jurisdiction") TotePoolJurisdiction jurisdiction,
                                              @JsonProperty("provider") ToteProvider provider,
                                              @JsonProperty("date") String date,
                                              @JsonProperty("es_track_id") String esTrackId,
                                              @JsonProperty("es_race_id") String esRaceId,
                                              @JsonProperty("market_pool_total") double marketPoolTotal,
                                              @JsonProperty("predicted_pool_total") double predictedPoolTotal,
                                              @JsonProperty("predicted_probabilities") Map<String, Double> predictedProbabilities,
                                              @JsonProperty("bet_type") ToteBetType betType,
                                              @JsonProperty("exotic_dividend_prediction_id") String exoticDividendPredictionId,
                                              @JsonProperty("calculation_time_millis") double calculationTimeMillis,
                                              @JsonProperty("dividend_model") TurboExoticDividendModel dividendModel,
                                              @JsonProperty("dividend_model_major_version") int dividendModelMajorVersion,
                                              @JsonProperty("dividend_model_minor_version") int dividendModelMinorVersion,
                                              @JsonProperty("pricing_model_source") String pricingModelSource) implements ToteMessage {

}

