package com.edgestackers.tote.hub.core.datamodel.message.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ToteBetResponse(@JsonProperty("client_bet_id") String clientBetId,
                              @JsonProperty("tote_bet_result") ToteBetResult toteBetResult,
                              @JsonProperty("selections") String selections,
                              @JsonProperty("accepted_stake") double acceptedStake,
                              @JsonProperty("requested_stake") double requestedStake) {
}
