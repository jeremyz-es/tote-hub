package com.edgestackers.tote.hub.core.datamodel.message.nitro;

import com.edgestackers.tote.hub.core.datamodel.message.ToteMessage;
import com.edgestackers.tote.hub.core.datamodel.core.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record NitroOrderExecutionMessage(@JsonProperty("client_order_id") String clientOrderId,
                                         @JsonProperty("jurisdiction") TotePoolJurisdiction jurisdiction,
                                         @JsonProperty("bet_type") ToteBetType betType,
                                         @JsonProperty("es_race_id") String esRaceId,
                                         @JsonProperty("bet_updates") List<NitroBetExecution> betUpdates,
                                         @JsonProperty("market_total_pool_size") double marketTotalPoolSize,
                                         @JsonProperty("predicted_total_pool_size") double predictedTotalPoolSize,
                                         @JsonProperty("epoch_millis_timestamp") long orderGenerationEpochMillisTimestamp,
                                         @JsonProperty("race_code") RaceCode raceCode,
                                         @JsonProperty("track") String track,
                                         @JsonProperty("race_number") int race,
                                         @JsonProperty("exec_trigger_race_event") ToteRaceEvent raceTrigger,
                                         @JsonProperty("provider") ToteProvider toteProvider,
                                         @JsonProperty("exotic_theo_id") String exoticTheoId,
                                         @JsonProperty("exotic_dividend_prediction_id") String exoticDividendPredictionId,
                                         @JsonProperty("strategy_id") String strategyId,
                                         @JsonProperty("strategy_name") String strategyName) implements ToteMessage {
}
