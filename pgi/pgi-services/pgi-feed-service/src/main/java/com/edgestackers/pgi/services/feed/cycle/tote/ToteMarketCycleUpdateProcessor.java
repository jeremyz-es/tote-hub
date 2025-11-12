package com.edgestackers.pgi.services.feed.cycle.tote;

import com.edgestackers.pgi.services.feed.publisher.PgiMessageNatsPublisher;
import com.edgestackers.tote.hub.core.datamodel.message.ToteMarketCycleUpdate;

public class ToteMarketCycleUpdateProcessor {
    private final PgiMessageNatsPublisher natsPublisher;

    public ToteMarketCycleUpdateProcessor(PgiMessageNatsPublisher natsPublisher) {
        this.natsPublisher = natsPublisher;
    }

    public void process(ToteMarketCycleUpdate toteMarketCycleUpdate) {
        natsPublisher.publish(toteMarketCycleUpdate);
    }
}
