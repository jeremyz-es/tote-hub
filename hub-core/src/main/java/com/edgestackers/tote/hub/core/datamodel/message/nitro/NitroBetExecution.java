package com.edgestackers.tote.hub.core.datamodel.message.nitro;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record NitroBetExecution(@JsonProperty("predicted_market_prob") double predictedMarketProb,
                                @JsonProperty("selections") List<Integer> selections,
                                @JsonProperty("bet_amount") double betAmount,
                                @JsonProperty("theo_prob") double theoProb,
                                @JsonProperty("client_bet_id") String clientBetId) {
}
