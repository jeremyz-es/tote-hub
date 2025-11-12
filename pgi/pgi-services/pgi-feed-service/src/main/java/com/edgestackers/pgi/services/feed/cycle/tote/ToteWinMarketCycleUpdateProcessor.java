package com.edgestackers.pgi.services.feed.cycle.tote;

import com.edgestackers.pgi.services.feed.publisher.PgiMessageNatsPublisher;
import com.edgestackers.tote.hub.core.datamodel.message.ToteWinMarketCycleUpdate;

public class ToteWinMarketCycleUpdateProcessor {
    private final PgiMessageNatsPublisher natsPublisher;

    public ToteWinMarketCycleUpdateProcessor(PgiMessageNatsPublisher natsPublisher) {
        this.natsPublisher = natsPublisher;
    }

    public void process(ToteWinMarketCycleUpdate toteWinMarketCycleUpdate) {
        natsPublisher.publish(toteWinMarketCycleUpdate);
    }
}
