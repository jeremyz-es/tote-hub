package com.edgestackers.pgi.services.execution.order.validation;

import com.edgestackers.pgi.services.execution.config.PgiExecutionGatewayConfig;
import com.edgestackers.tote.hub.core.datamodel.strategy.StrategyBetRequest;
import com.edgestackers.tote.hub.core.datamodel.strategy.StrategyOrderRequest;

import java.util.List;

public class StrategyOrderRequestValidator {
    private final PgiExecutionGatewayConfig executionGatewayConfig;

    public StrategyOrderRequestValidator(PgiExecutionGatewayConfig executionGatewayConfig) {
        this.executionGatewayConfig = executionGatewayConfig;
    }

    public void validate(StrategyOrderRequest strategyOrderRequest) throws StrategyOrderRequestValidationException {
        List<StrategyBetRequest> strategyBetRequests = strategyOrderRequest.strategyBetRequests();
        double totalStake = strategyBetRequests.stream().map(StrategyBetRequest::stake).reduce(0.0, Double::sum);
        if (totalStake > executionGatewayConfig.strategyOrderDoNotExceedNotionalLimit()) {
            String errorMessage = String.format("Total stake for Strategy Order Request with ID = %s wit total stake of %s exceeded the order execution limit of %s",
                    strategyOrderRequest.clientOrderId(),
                    totalStake,
                    executionGatewayConfig.strategyOrderDoNotExceedNotionalLimit()
            );
            throw new StrategyOrderRequestValidationException(errorMessage);
        }
    }
}
