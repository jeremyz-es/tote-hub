package com.edgestackers.tote.hub.core.datamodel.strategy;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StrategySelection(@JsonProperty("tab") int runnerNumber,
                                @JsonProperty("ranking") int position) {
}
