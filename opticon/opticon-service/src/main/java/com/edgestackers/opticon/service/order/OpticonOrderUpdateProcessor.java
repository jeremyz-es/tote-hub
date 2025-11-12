package com.edgestackers.opticon.service.order;

import com.edgestackers.opticon.common.datamodel.OpticonOrderUpdate;
import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;

public class OpticonOrderUpdateProcessor {
    private final OpticonOrderUpdateCache cache;
    private final OpticonMessageWebsocketPublisher messageWebsocketPublisher;

    public OpticonOrderUpdateProcessor(OpticonOrderUpdateCache cache,
                                       OpticonMessageWebsocketPublisher messageWebsocketPublisher)
    {
        this.cache = cache;
        this.messageWebsocketPublisher = messageWebsocketPublisher;
    }

    public void process(OpticonOrderUpdate opticonOrderUpdate) {
        cache.put(opticonOrderUpdate);
        messageWebsocketPublisher.publish(opticonOrderUpdate);
    }
}
