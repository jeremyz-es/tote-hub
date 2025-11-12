package com.edgestackers.opticon.service.message;

import com.edgestackers.opticon.common.datamodel.OpticonRunSummary;
import com.edgestackers.opticon.common.datamodel.OpticonWinMarketSummary;
import com.edgestackers.opticon.common.datamodel.OpticonWinPoolUpdate;
import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.cycles.OpticonWinPoolUpdateCache;
import com.edgestackers.opticon.service.run.OpticonRunSummaryCache;
import com.edgestackers.opticon.service.run.OpticonRunSummaryKey;
import com.edgestackers.opticon.service.run.OpticonWinMarketSummaryCache;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.message.ToteWinMarketCycleUpdate;
import jakarta.annotation.Nullable;

import java.util.Map;

import static com.edgestackers.opticon.service.cycles.OpticonWinPoolUpdateFactory.createOpticonWinPoolUpdate;

public class ToteWinMarketCycleUpdateProcessor {
    private final OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher;
    private final OpticonRunSummaryCache runSummaryCache;
    private final OpticonWinMarketSummaryCache winMarketSummaryCache;
    private final OpticonWinPoolUpdateCache winPoolUpdateCache;

    public ToteWinMarketCycleUpdateProcessor(OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher,
                                             OpticonRunSummaryCache runSummaryCache,
                                             OpticonWinMarketSummaryCache winMarketSummaryCache,
                                             OpticonWinPoolUpdateCache winPoolUpdateCache)
    {
        this.opticonMessageWebsocketPublisher = opticonMessageWebsocketPublisher;
        this.runSummaryCache = runSummaryCache;
        this.winMarketSummaryCache = winMarketSummaryCache;
        this.winPoolUpdateCache = winPoolUpdateCache;
    }

    public void process(ToteWinMarketCycleUpdate toteWinMarketCycleUpdate) {
        String esRaceId = toteWinMarketCycleUpdate.esRaceId();
        Map<Integer, Double> marketPrices = toteWinMarketCycleUpdate.marketPrices();
        TotePoolJurisdiction jurisdiction = toteWinMarketCycleUpdate.totePoolJurisdiction();
        double totalPool = toteWinMarketCycleUpdate.poolTotal();
        for (Map.Entry<Integer, Double> marketPrice : marketPrices.entrySet()) {
            int tab = marketPrice.getKey();
            double price = marketPrice.getValue();
            OpticonRunSummaryKey runSummaryKey = new OpticonRunSummaryKey(esRaceId, tab);
            @Nullable OpticonRunSummary opticonRunSummary = runSummaryCache.get(runSummaryKey);
            if (opticonRunSummary == null) continue;
            OpticonWinMarketSummary winMarketSummary = new OpticonWinMarketSummary(price, totalPool / price, totalPool);
            opticonRunSummary.put(jurisdiction, winMarketSummary);
            winMarketSummaryCache.put(runSummaryKey, jurisdiction, winMarketSummary);
            opticonMessageWebsocketPublisher.publish(opticonRunSummary);
        }
        OpticonWinPoolUpdate winPoolUpdate = createOpticonWinPoolUpdate(toteWinMarketCycleUpdate);
        winPoolUpdateCache.put(winPoolUpdate);
        opticonMessageWebsocketPublisher.publish(winPoolUpdate);
    }
}
