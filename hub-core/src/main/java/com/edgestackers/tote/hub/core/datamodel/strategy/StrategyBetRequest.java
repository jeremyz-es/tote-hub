package com.edgestackers.tote.hub.core.datamodel.strategy;

import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StrategyBetRequest(@JsonProperty("client_bet_id") String clientBetId,
                                 @JsonProperty("bet_type") ToteBetType toteBetType,
                                 @JsonProperty("strategy_selections") List<StrategySelection> strategySelections,
                                 @JsonProperty("stake") double stake) {
}
