package com.edgestackers.pgi.services.execution.order;

import com.edgestackers.pgi.common.datamodel.message.PgiOrderResponse;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;

import static com.edgestackers.pgi.services.execution.order.PgiBetResponseConverter.convertResponseToBetResponses;
import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public enum PgiOrderSummaryResponseConverter {
    ;

    public static PgiOrderResponse convertRawResponseToOrderSummary(String clientOrderId,
                                                                    String rawResponse,
                                                                    TotePoolJurisdiction totePoolJurisdiction) {
        return new PgiOrderResponse(
                clientOrderId,
                generateEpochNanosTimestamp(),
                totePoolJurisdiction,
                convertResponseToBetResponses(rawResponse)
        );
    }
}
