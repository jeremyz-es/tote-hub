package com.edgestackers.opticon.common.message;

import com.edgestackers.opticon.common.datamodel.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = OpticonMarketCycleUpdate.class, name = "OpticonMarketCycleUpdate"),
        @JsonSubTypes.Type(value = OpticonRunSummary.class, name = "OpticonRunSummary"),
        @JsonSubTypes.Type(value = OpticonWinPoolUpdate.class, name = "OpticonWinPoolUpdate"),
        @JsonSubTypes.Type(value = OpticonAccountBalanceSummary.class, name = "OpticonAccountBalanceSummary"),
        @JsonSubTypes.Type(value = OpticonDividendPredictionUpdate.class, name = "OpticonDividendPredictionUpdate"),
        @JsonSubTypes.Type(value = OpticonTheoUpdate.class, name = "OpticonTheoUpdate"),
        @JsonSubTypes.Type(value = OpticonOrderUpdate.class, name = "OpticonOrderUpdate"),
        @JsonSubTypes.Type(value = PacmanRaceFlucsUpdate.class, name = "PacmanRaceFlucsUpdate"),
        @JsonSubTypes.Type(value = OpticonStrategyContextSummary.class, name = "OpticonExoticContextSummary"),
})
public interface OpticonMessage {
}
