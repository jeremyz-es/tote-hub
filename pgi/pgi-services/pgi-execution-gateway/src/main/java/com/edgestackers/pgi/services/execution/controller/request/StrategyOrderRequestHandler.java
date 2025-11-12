package com.edgestackers.pgi.services.execution.controller.request;

import com.edgestackers.pgi.services.execution.order.StrategyOrderRequestProcessingException;
import com.edgestackers.pgi.services.execution.order.StrategyOrderRequestProcessor;
import com.edgestackers.tote.hub.core.datamodel.strategy.StrategyOrderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StrategyOrderRequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyOrderRequestHandler.class);
    private static final ObjectMapper DESERIALIZER = new ObjectMapper();
    private final StrategyOrderRequestProcessor strategyOrderRequestProcessor;

    public StrategyOrderRequestHandler(StrategyOrderRequestProcessor strategyOrderRequestProcessor) {
        this.strategyOrderRequestProcessor = strategyOrderRequestProcessor;
    }

    public void handle(Context context) {
        try {
            StrategyOrderRequest strategyOrderRequest = DESERIALIZER.readValue(context.body(), StrategyOrderRequest.class);
            LOGGER.info("Handling [{}] for ES_RACE_ID: [{}]", strategyOrderRequest.getClass().getSimpleName(), strategyOrderRequest.esRaceId());
            try {
                strategyOrderRequestProcessor.process(strategyOrderRequest);
                context.status(HttpStatus.OK);
            }
            catch (StrategyOrderRequestProcessingException e) {
                context.result(e.getMessage());
                context.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (JsonProcessingException e) {
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
            context.result("Failed to deserialize strategy order request.");
            LOGGER.error("Failed to deserialize order submission request into [{}] due to [{}] - {} | Request Body: {}",
                    StrategyOrderRequest.class.getSimpleName(),
                    e.getClass().getSimpleName(),
                    e.getMessage(),
                    context.body()
            );
        }
    }
}
