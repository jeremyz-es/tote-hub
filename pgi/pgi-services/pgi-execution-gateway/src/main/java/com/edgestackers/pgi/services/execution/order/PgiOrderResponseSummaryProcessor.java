package com.edgestackers.pgi.services.execution.order;

import com.edgestackers.pgi.common.datamodel.message.PgiOrderResponse;
import com.edgestackers.pgi.services.execution.nats.NatsPublisher;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteOrderResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.edgestackers.pgi.services.execution.order.ToteOrderResponseFactory.createToteOrderResponse;

public class PgiOrderResponseSummaryProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiOrderResponseSummaryProcessor.class);
    private final NatsPublisher natsPublisher;

    public PgiOrderResponseSummaryProcessor(NatsPublisher natsPublisher) {
        this.natsPublisher = natsPublisher;
    }

    public void process(PgiOrderResponse orderResponseSummary) {
        natsPublisher.publish(orderResponseSummary);
        ToteOrderResponseMessage toteOrderResponseMessage = createToteOrderResponse(orderResponseSummary);
        natsPublisher.publish(toteOrderResponseMessage);
    }
}
