package com.edgestackers.tote.hub.core.datamodel.strategy;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StrategyOrderRequest(@JsonProperty("client_order_id") String clientOrderId,
                                   @JsonProperty("es_race_id") String esRaceId,
                                   @JsonProperty("strategy_bet_requests") List<StrategyBetRequest> strategyBetRequests,
                                   @JsonProperty("provider_alias") String providerAlias) {
}
