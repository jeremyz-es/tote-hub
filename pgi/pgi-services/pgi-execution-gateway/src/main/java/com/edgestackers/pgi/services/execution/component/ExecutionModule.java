package com.edgestackers.pgi.services.execution.component;

import com.amtote.filebet.IService;
import com.edgestackers.pgi.services.execution.config.PgiExecutionGatewayConfig;
import com.edgestackers.pgi.services.execution.metadata.PgiRaceMetadataCache;
import com.edgestackers.pgi.services.execution.nats.NatsPublisher;
import com.edgestackers.pgi.services.execution.order.PgiOrderResponseSummaryProcessor;
import com.edgestackers.pgi.services.execution.order.StrategyOrderRequestProcessor;
import com.edgestackers.pgi.services.execution.order.validation.StrategyOrderRequestValidator;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ExecutionModule {
    private final TotePoolJurisdiction totePoolJurisdiction;
    private final PgiExecutionGatewayConfig pgiExecutionGatewayConfig;

    public ExecutionModule(TotePoolJurisdiction totePoolJurisdiction, PgiExecutionGatewayConfig pgiExecutionGatewayConfig) {
        this.totePoolJurisdiction = totePoolJurisdiction;
        this.pgiExecutionGatewayConfig = pgiExecutionGatewayConfig;
    }

    @Provides
    @Singleton
    public StrategyOrderRequestValidator strategyOrderRequestValidator() {
        return new StrategyOrderRequestValidator(pgiExecutionGatewayConfig);
    }

    @Provides
    @Singleton
    public StrategyOrderRequestProcessor strategyOrderRequestProcessor(IService iService,
                                                                       PgiRaceMetadataCache pgiRaceMetadataCache,
                                                                       PgiOrderResponseSummaryProcessor pgiOrderResponseSummaryProcessor,
                                                                       StrategyOrderRequestValidator strategyOrderRequestValidator)
    {
        return new StrategyOrderRequestProcessor(
                iService,
                pgiRaceMetadataCache,
                pgiOrderResponseSummaryProcessor,
                totePoolJurisdiction,
                strategyOrderRequestValidator
        );
    }

    @Provides
    @Singleton
    public PgiOrderResponseSummaryProcessor pgiOrderResponseSummaryProcessor(NatsPublisher natsPublisher) {
        return new PgiOrderResponseSummaryProcessor(natsPublisher);
    }
}
