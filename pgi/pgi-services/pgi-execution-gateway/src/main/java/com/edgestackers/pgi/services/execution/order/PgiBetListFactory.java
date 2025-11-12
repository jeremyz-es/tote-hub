package com.edgestackers.pgi.services.execution.order;

import com.edgestackers.pgi.common.datamodel.metadata.PgiRaceMetadata;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.strategy.StrategyBetRequest;
import com.edgestackers.tote.hub.core.datamodel.strategy.StrategyOrderRequest;
import com.edgestackers.tote.hub.core.datamodel.strategy.StrategySelection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.edgestackers.pgi.common.datamodel.PgiBetTypeConverter.convertToteBetType;

public enum PgiBetListFactory {
    ;
    private static final String RUNNERS_DELIMITER = ",";

    public static PgiBetList generateBetsFile(StrategyOrderRequest orderRequest, PgiRaceMetadata raceMetadata)
    {
        String bets = convertStrategyBetRequests(orderRequest.strategyBetRequests(), raceMetadata);
        StringBuilder descBuilder = new StringBuilder();
        try {
            for (StrategyBetRequest strategyBetRequest : orderRequest.strategyBetRequests()) {
                if (strategyBetRequest.toteBetType() == ToteBetType.WIN) {
                    descBuilder.append(String.format("Runner: %s | Stake: %s", strategyBetRequest.strategySelections().getFirst().runnerNumber(), strategyBetRequest.stake()));
                }
            }
        }
        catch (Exception ignored) {}
        return new PgiBetList(bets, descBuilder.toString().isEmpty() ? null : descBuilder.toString());
    }

    public static String convertStrategyBetRequests(List<StrategyBetRequest> strategyBetRequests, PgiRaceMetadata raceMetadata) {
        StringBuilder file = new StringBuilder();
        for (StrategyBetRequest strategyBetRequest : strategyBetRequests) {
            String betLine = convertStrategyBetRequest(strategyBetRequest, raceMetadata);
            file.append(String.format("%s\n", betLine));
        }
        return file.toString();
    }

    private static String convertStrategyBetRequest(StrategyBetRequest strategyBetRequest, PgiRaceMetadata raceMetadata) {
        double stake = strategyBetRequest.stake();
        if (strategyBetRequest.toteBetType() == ToteBetType.WIN) {
            stake = Math.floor(stake * 10.0) / 10.0;
        }
        return String.format("%s|%s|%s|%.2f|%s|%s",
                strategyBetRequest.clientBetId(),
                raceMetadata.programName(),
                raceMetadata.raceNumber(),
                stake,
                convertToteBetType(strategyBetRequest.toteBetType()),
                runnersStringFor(strategyBetRequest)
        );
    }

    private static String runnersStringFor(StrategyBetRequest strategyBetRequest) {
        Map<Integer, Integer> selections = strategyBetRequest.strategySelections().stream().collect(Collectors.toMap(StrategySelection::runnerNumber, StrategySelection::position));
        List<Integer> sortedSelections = selections.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .toList();
        return String.join(RUNNERS_DELIMITER, sortedSelections.stream().map(String::valueOf).toList());
    }
}
