package com.edgestackers.opticon.service.order;

import com.edgestackers.opticon.common.datamodel.OpticonBetStatus;
import com.edgestackers.opticon.common.datamodel.OpticonBetUpdate;
import com.edgestackers.tote.hub.core.datamodel.message.nitro.NitroBetExecution;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteBetResponse;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteBetResult;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.edgestackers.opticon.common.datamodel.OpticonBetStatus.convertFromToteBetResult;

public enum OpticonBetUpdateFactory {
    ;


    static List<OpticonBetUpdate> createOpticonBetUpdates(List<NitroBetExecution> nitroBetExecutions, List<ToteBetResponse> toteBetResponses) {
        Map<String, ToteBetResult> betResultsByBetId = toteBetResponses.stream().collect(Collectors.toMap(ToteBetResponse::clientBetId, ToteBetResponse::toteBetResult));
        List<OpticonBetUpdate> opticonBetUpdates = new ArrayList<>();
        for (NitroBetExecution betExecution : nitroBetExecutions) {
            String clientBetId = betExecution.clientBetId();
            @Nullable ToteBetResult betResult = betResultsByBetId.get(clientBetId);
            OpticonBetStatus betStatus = convertFromToteBetResult(betResult);
            opticonBetUpdates.add(new OpticonBetUpdate(
                    clientBetId,
                    betExecution.selections(),
                    betExecution.betAmount(),
                    betExecution.predictedMarketProb(),
                    1 / betExecution.predictedMarketProb(),
                    betExecution.theoProb(),
                    1 / betExecution.theoProb(),
                    betStatus
            ));
        }
        return opticonBetUpdates;
    }

    static List<OpticonBetUpdate> createOpticonBetUpdatesUnknownStatus(List<NitroBetExecution> nitroBetExecutions) {
        List<OpticonBetUpdate> opticonBetUpdates = new ArrayList<>();
        for (NitroBetExecution betExecution : nitroBetExecutions) {
            String clientBetId = betExecution.clientBetId();
            opticonBetUpdates.add(new OpticonBetUpdate(
                    clientBetId,
                    betExecution.selections(),
                    betExecution.betAmount(),
                    betExecution.predictedMarketProb(),
                    1 / betExecution.predictedMarketProb(),
                    betExecution.theoProb(),
                    1 / betExecution.theoProb(),
                    OpticonBetStatus.UNKNOWN
            ));
        }
        return opticonBetUpdates;
    }
}
