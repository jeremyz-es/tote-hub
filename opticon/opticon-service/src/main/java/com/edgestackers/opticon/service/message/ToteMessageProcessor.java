package com.edgestackers.opticon.service.message;


import com.edgestackers.tote.hub.core.datamodel.message.*;
import com.edgestackers.tote.hub.core.datamodel.message.nitro.NitroOrderExecutionMessage;
import com.edgestackers.tote.hub.core.datamodel.message.nitro.NitroOrderGeneratorStateMessage;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteOrderResponseMessage;
import com.edgestackers.tote.hub.core.datamodel.message.pacman.PacmanRaceFlucsMessage;
import com.edgestackers.tote.hub.core.datamodel.message.turbo.ExoticDividendPredictionMessage;
import com.edgestackers.tote.hub.core.datamodel.message.turbo.ExoticTheoMessage;

public class ToteMessageProcessor {
    private final ToteMarketCycleUpdateProcessor toteMarketCycleUpdateProcessor;
    private final RaceEventUpdateMessageProcessor raceEventUpdateMessageProcessor;
    private final ToteWinMarketCycleUpdateProcessor toteWinMarketCycleUpdateProcessor;
    private final ToteAccountBalanceSummaryProcessor toteAccountBalanceSummaryProcessor;
    private final ExoticDividendMessageProcessor exoticDividendMessageProcessor;
    private final ExoticTheoMessageProcessor exoticTheoMessageProcessor;
    private final ToteOrderResponseMessageProcessor toteOrderResponseMessageProcessor;
    private final NitroOrderExecutionMessageProcessor nitroOrderExecutionMessageProcessor;
    private final PacmanRaceFlucsMessageProcessor pacmanRaceFlucsMessageProcessor;
    private final NitroOrderGeneratorStateMessageProcessor nitroOrderGeneratorStateMessageProcessor;

    public ToteMessageProcessor(ToteMarketCycleUpdateProcessor toteMarketCycleUpdateProcessor,
                                RaceEventUpdateMessageProcessor raceEventUpdateMessageProcessor,
                                ToteWinMarketCycleUpdateProcessor toteWinMarketCycleUpdateProcessor,
                                ToteAccountBalanceSummaryProcessor toteAccountBalanceSummaryProcessor,
                                ExoticDividendMessageProcessor exoticDividendMessageProcessor,
                                ExoticTheoMessageProcessor exoticTheoMessageProcessor,
                                ToteOrderResponseMessageProcessor toteOrderResponseMessageProcessor,
                                NitroOrderExecutionMessageProcessor nitroOrderExecutionMessageProcessor,
                                PacmanRaceFlucsMessageProcessor pacmanRaceFlucsMessageProcessor,
                                NitroOrderGeneratorStateMessageProcessor nitroOrderGeneratorStateMessageProcessor)
    {
        this.toteMarketCycleUpdateProcessor = toteMarketCycleUpdateProcessor;
        this.raceEventUpdateMessageProcessor = raceEventUpdateMessageProcessor;
        this.toteWinMarketCycleUpdateProcessor = toteWinMarketCycleUpdateProcessor;
        this.toteAccountBalanceSummaryProcessor = toteAccountBalanceSummaryProcessor;
        this.exoticDividendMessageProcessor = exoticDividendMessageProcessor;
        this.exoticTheoMessageProcessor = exoticTheoMessageProcessor;
        this.toteOrderResponseMessageProcessor = toteOrderResponseMessageProcessor;
        this.nitroOrderExecutionMessageProcessor = nitroOrderExecutionMessageProcessor;
        this.pacmanRaceFlucsMessageProcessor = pacmanRaceFlucsMessageProcessor;
        this.nitroOrderGeneratorStateMessageProcessor = nitroOrderGeneratorStateMessageProcessor;
    }

    public void process(ToteMessage toteMessage) {
        if (toteMessage instanceof ToteMarketCycleUpdate update) toteMarketCycleUpdateProcessor.process(update);
        if (toteMessage instanceof RaceEventUpdateMessage msg) raceEventUpdateMessageProcessor.process(msg);
        if (toteMessage instanceof ToteWinMarketCycleUpdate msg) toteWinMarketCycleUpdateProcessor.process(msg);
        if (toteMessage instanceof ToteAccountBalanceSummary msg) toteAccountBalanceSummaryProcessor.process(msg);
        if (toteMessage instanceof ExoticDividendPredictionMessage msg) exoticDividendMessageProcessor.process(msg);
        if (toteMessage instanceof ExoticTheoMessage msg) exoticTheoMessageProcessor.process(msg);
        if (toteMessage instanceof ToteOrderResponseMessage msg) toteOrderResponseMessageProcessor.process(msg);
        if (toteMessage instanceof NitroOrderExecutionMessage msg) nitroOrderExecutionMessageProcessor.process(msg);
        if (toteMessage instanceof PacmanRaceFlucsMessage msg) pacmanRaceFlucsMessageProcessor.process(msg);
        if (toteMessage instanceof NitroOrderGeneratorStateMessage msg) nitroOrderGeneratorStateMessageProcessor.process(msg);
    }
}
