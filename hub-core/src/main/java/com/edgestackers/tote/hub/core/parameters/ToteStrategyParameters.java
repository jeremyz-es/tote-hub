package com.edgestackers.tote.hub.core.parameters;

import com.edgestackers.tote.hub.core.datamodel.core.*;
import com.edgestackers.tote.hub.core.datamodel.turbo.TurboExoticDividendModel;
import com.edgestackers.tote.hub.core.datamodel.turbo.TurboExoticTheoModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public record ToteStrategyParameters(@JsonProperty("strategy_id") String strategyId,
                                     @JsonProperty("strategy_name") String strategyName,
                                     @JsonProperty("created_at_epoch_nanos") long createdAtUtcNanos,
                                     @JsonProperty("last_updated_at_epoch_nanos") long lastUpdatedAtEpochNanos,
                                     @JsonProperty("race_type") RaceType raceType,
                                     @JsonProperty("tote_provider") ToteProvider toteProvider,
                                     @JsonProperty("bet_type") ToteBetType toteBetType,
                                     @JsonProperty("pool_jurisdiction") TotePoolJurisdiction jurisdiction,
                                     @JsonProperty("max_race_bet") double maxRaceBet,
                                     @JsonProperty("rebate") double rebate,
                                     @JsonProperty("takeout") double takeout,
                                     @JsonProperty("discount") double discount,
                                     @JsonProperty("min_bet") double minBet,
                                     @JsonProperty("min_pool_size") double minPoolSize,
                                     @JsonProperty("max_pool_size") double maxPoolSize,
                                     @JsonProperty("max_dividend_age_seconds") int maxDividendAgeSeconds,
                                     @JsonProperty("max_theo_age_seconds") int maxTheoAgeSeconds,
                                     @JsonProperty("dividend_model") TurboExoticDividendModel turboExoticDividendModel,
                                     @JsonProperty("dividend_model_major_version") int dividendModelMajorVersion,
                                     @JsonProperty("dividend_model_minor_version") int dividendModelMinorVersion,
                                     @JsonProperty("theo_model") TurboExoticTheoModel turboExoticTheoModel,
                                     @JsonProperty("theo_model_major_version") int theoModelMajorVersion,
                                     @JsonProperty("theo_model_minor_version") int theoModelMinorVersion,
                                     @JsonProperty("trigger_race_events") List<ToteRaceEvent> triggerRaceEvents) implements CaesarParametersMessage {
}
