package com.edgestackers.opticon.service.message;

import com.edgestackers.opticon.common.datamodel.OpticonAccountBalanceSummary;
import com.edgestackers.opticon.service.control.OpticonAccountBalanceSummaryCache;
import com.edgestackers.opticon.service.control.OpticonAccountBalanceSummaryFactory;
import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.message.ToteAccountBalanceSummary;

import static com.edgestackers.opticon.service.control.OpticonAccountBalanceSummaryFactory.createAccountBalanceSummary;

public class ToteAccountBalanceSummaryProcessor {
    private final OpticonAccountBalanceSummaryFactory opticonAccountBalanceSummaryFactory;
    private final OpticonAccountBalanceSummaryCache opticonAccountBalanceSummaryCache;
    private final OpticonMessageWebsocketPublisher messageWebsocketPublisher;

    public ToteAccountBalanceSummaryProcessor(OpticonAccountBalanceSummaryFactory opticonAccountBalanceSummaryFactory,
                                              OpticonAccountBalanceSummaryCache opticonAccountBalanceSummaryCache,
                                              OpticonMessageWebsocketPublisher messageWebsocketPublisher)
    {
        this.opticonAccountBalanceSummaryFactory = opticonAccountBalanceSummaryFactory;
        this.opticonAccountBalanceSummaryCache = opticonAccountBalanceSummaryCache;
        this.messageWebsocketPublisher = messageWebsocketPublisher;
    }

    public void process(ToteAccountBalanceSummary summary) {
        OpticonAccountBalanceSummary balanceSummary = createAccountBalanceSummary(summary);
        handle(balanceSummary);
        createAndPublishTotalBalanceSummary(balanceSummary.jurisdiction());
    }

    private void createAndPublishTotalBalanceSummary(TotePoolJurisdiction jurisdiction) {
        OpticonAccountBalanceSummary summary = opticonAccountBalanceSummaryFactory.createTotalBalanceSummary(ToteProvider.PGI, jurisdiction);
        handle(summary);
    }

    private void handle(OpticonAccountBalanceSummary summary) {
        opticonAccountBalanceSummaryCache.put(summary);
        messageWebsocketPublisher.publish(summary);
    }
}
