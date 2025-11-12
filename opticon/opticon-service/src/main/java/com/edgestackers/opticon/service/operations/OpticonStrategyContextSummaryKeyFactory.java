package com.edgestackers.opticon.service.operations;

import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummary;
import com.edgestackers.opticon.common.datamodel.OpticonStrategyContextSummaryKey;
import com.edgestackers.tote.hub.core.datamodel.message.nitro.NitroOrderExecutionMessage;
import com.edgestackers.tote.hub.core.datamodel.message.nitro.NitroOrderGeneratorStateMessage;
import com.edgestackers.tote.hub.core.parameters.ToteStrategyParameters;

public enum OpticonStrategyContextSummaryKeyFactory {
    ;

    public static OpticonStrategyContextSummaryKey createKey(OpticonStrategyContextSummary summary) {
        return new OpticonStrategyContextSummaryKey(
                summary.getStrategyId(),
                summary.getEsRaceId(),
                summary.getToteBetType(),
                summary.getTotePoolJurisdiction()
        );
    }

    public static OpticonStrategyContextSummaryKey createKey(ToteStrategyParameters parameters, String esRaceId) {
        return new OpticonStrategyContextSummaryKey(
                parameters.strategyId(),
                esRaceId,
                parameters.toteBetType(),
                parameters.jurisdiction()
        );
    }

    public static OpticonStrategyContextSummaryKey createKey(NitroOrderExecutionMessage nitroOrderExecutionMessage) {
        return new OpticonStrategyContextSummaryKey(
                nitroOrderExecutionMessage.strategyId(),
                nitroOrderExecutionMessage.esRaceId(),
                nitroOrderExecutionMessage.betType(),
                nitroOrderExecutionMessage.jurisdiction()
        );
    }

    public static OpticonStrategyContextSummaryKey createKey(NitroOrderGeneratorStateMessage message) {
        return new OpticonStrategyContextSummaryKey(
                message.strategyId(),
                message.esRaceId(),
                message.betType(),
                message.jurisdiction()
        );
    }
}
