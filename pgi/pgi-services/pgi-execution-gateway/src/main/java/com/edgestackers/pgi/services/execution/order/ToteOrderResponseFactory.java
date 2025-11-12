package com.edgestackers.pgi.services.execution.order;

import com.edgestackers.pgi.common.datamodel.message.PgiBetResponse;
import com.edgestackers.pgi.common.datamodel.message.PgiOrderResponse;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteBetResponse;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteBetResult;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteOrderResponseMessage;

import java.util.ArrayList;
import java.util.List;

import static com.edgestackers.pgi.services.execution.order.PgiBetResponseError.convertFromErrorCode;
import static com.edgestackers.pgi.services.execution.order.PgiBetResponseError.convertToToteBetResult;
import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public enum ToteOrderResponseFactory {
    ;
    private static final ToteProvider TOTE_PROVIDER = ToteProvider.PGI;
    private static final String PGI_RUNNERS_STRING_DELIMITER = "|";
    private static final String TOTE_RUNNERS_STRING_DELIMITER = ",";

    public static ToteOrderResponseMessage createToteOrderResponse(PgiOrderResponse pgiOrderResponse) {
        return new ToteOrderResponseMessage(
                TOTE_PROVIDER,
                generateEpochNanosTimestamp(),
                pgiOrderResponse.totePoolJurisdiction(),
                pgiOrderResponse.clientOrderId(),
                createToteBetResponses(pgiOrderResponse.pgiBetResponseSummaries())
        );
    }

    private static List<ToteBetResponse> createToteBetResponses(List<PgiBetResponse> pgiBetResponseSummaries) {
        List<ToteBetResponse> toteBetResponses = new ArrayList<>();
        for (PgiBetResponse pgiBetResponse : pgiBetResponseSummaries) {
            toteBetResponses.add(new ToteBetResponse(
                    pgiBetResponse.clientBetId(),
                    pgiBetResponse.errorCode() == 0 ? ToteBetResult.ACCEPTED : convertToToteBetResult(convertFromErrorCode(pgiBetResponse.errorCode())),
                    String.join(TOTE_RUNNERS_STRING_DELIMITER, pgiBetResponse.runnersString().split(PGI_RUNNERS_STRING_DELIMITER)),
                    pgiBetResponse.errorCode() == 0 ? pgiBetResponse.stake() : 0.0d,
                    pgiBetResponse.stake()
            ));
        }
        return toteBetResponses;
    }
}
