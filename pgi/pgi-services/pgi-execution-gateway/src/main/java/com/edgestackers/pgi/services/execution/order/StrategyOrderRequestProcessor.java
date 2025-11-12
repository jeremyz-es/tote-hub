package com.edgestackers.pgi.services.execution.order;

import com.amtote.filebet.IService;
import com.edgestackers.pgi.common.datamodel.metadata.PgiRaceMetadata;
import com.edgestackers.pgi.common.datamodel.message.PgiOrderResponse;
import com.edgestackers.pgi.services.execution.metadata.PgiRaceMetadataCache;
import com.edgestackers.pgi.services.execution.order.validation.StrategyOrderRequestValidationException;
import com.edgestackers.pgi.services.execution.order.validation.StrategyOrderRequestValidator;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.strategy.StrategyOrderRequest;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.edgestackers.pgi.services.execution.order.PgiBetListFactory.generateBetsFile;
import static com.edgestackers.pgi.services.execution.order.PgiOrderSummaryResponseConverter.convertRawResponseToOrderSummary;

public class StrategyOrderRequestProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyOrderRequestProcessor.class);
    private final IService iService;
    private final PgiRaceMetadataCache pgiRaceMetadataCache;
    private final PgiOrderResponseSummaryProcessor pgiOrderResponseSummaryProcessor;
    private final TotePoolJurisdiction totePoolJurisdiction;
    private final StrategyOrderRequestValidator strategyOrderRequestValidator;

    public StrategyOrderRequestProcessor(IService iService,
                                         PgiRaceMetadataCache pgiRaceMetadataCache,
                                         PgiOrderResponseSummaryProcessor pgiOrderResponseSummaryProcessor,
                                         TotePoolJurisdiction totePoolJurisdiction,
                                         StrategyOrderRequestValidator strategyOrderRequestValidator)
    {
        this.iService = iService;
        this.pgiRaceMetadataCache = pgiRaceMetadataCache;
        this.pgiOrderResponseSummaryProcessor = pgiOrderResponseSummaryProcessor;
        this.totePoolJurisdiction = totePoolJurisdiction;
        this.strategyOrderRequestValidator = strategyOrderRequestValidator;
    }

    public void process(StrategyOrderRequest request) throws StrategyOrderRequestProcessingException {
        String clientOrderId = request.clientOrderId();
        try {
            strategyOrderRequestValidator.validate(request);
        }
        catch (StrategyOrderRequestValidationException e) {
            LOGGER.error("Failed to process {} for Client OID: {}, EIR: {} due to {} - {}",
                    StrategyOrderRequest.class.getSimpleName(),
                    clientOrderId,
                    request.esRaceId(),
                    e.getClass().getSimpleName(),
                    e.getMessage()
            );
            throw new StrategyOrderRequestProcessingException(e.getMessage());
        }
        @Nullable PgiRaceMetadata pgiRaceMetadata = pgiRaceMetadataCache.get(request.esRaceId());
        if (pgiRaceMetadata == null) {
            String errorMessage = String.format("Failed to process [%s] for ClientOrderId=[%s] as no [%s] was found for EsRaceId=[%s]", StrategyOrderRequest.class.getSimpleName(), clientOrderId, PgiRaceMetadata.class.getSimpleName(), request.esRaceId());
            LOGGER.error(errorMessage);
            throw new StrategyOrderRequestProcessingException(errorMessage);
        }
        PgiBetList pgiBetList = generateBetsFile(request, pgiRaceMetadata);
        try {
            String rawOrderResponse = iService.submitBets(pgiBetList.betsFile());
            if (pgiBetList.desc() != null) {
                LOGGER.info("\nBets for {} {}:\n{}", pgiRaceMetadata.track(), pgiRaceMetadata.raceNumber(), pgiBetList.desc());
            }
            try {
                PgiOrderResponse orderResponseSummary = convertRawResponseToOrderSummary(clientOrderId, rawOrderResponse, this.totePoolJurisdiction);
                pgiOrderResponseSummaryProcessor.process(orderResponseSummary);
            }
            catch (Exception e) {
                LOGGER.error("Failed to process order response summary for {} {} {} due to {} - {}", pgiRaceMetadata.raceCode(), pgiRaceMetadata.track(), pgiRaceMetadata.raceNumber(), e.getClass().getSimpleName(), e.getMessage());
            }
        }
        catch (Exception e) {
            LOGGER.error("Failed to submit file bets due to {} - {}", e.getClass().getSimpleName(), e.getMessage());
        }

    }
}
