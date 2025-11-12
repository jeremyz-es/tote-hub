package com.edgestackers.opticon.service.message;


import com.edgestackers.tote.hub.core.datamodel.context.OrderGatewayExecutionStatus;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteBetResponse;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteBetResult;
import com.edgestackers.tote.hub.core.datamodel.message.order.ToteOrderResponseMessage;

import java.util.List;

enum OrderExecutionStatusUtil {
    ;

    static OrderGatewayExecutionStatus executionStatusFor(ToteOrderResponseMessage message) {
        List<ToteBetResponse> toteBetResponses = message.toteBetResponses();
        if (toteBetResponses.stream().allMatch(betResponse -> betResponse.toteBetResult() == ToteBetResult.ACCEPTED)) return OrderGatewayExecutionStatus.ORDER_ACCEPTED;
        if (toteBetResponses.stream().allMatch(betResponse -> betResponse.toteBetResult() == ToteBetResult.REJECTED)) return OrderGatewayExecutionStatus.ORDER_REJECTION;
        if (toteBetResponses.stream().allMatch(betResponse -> betResponse.toteBetResult() == ToteBetResult.RUNNER_SCRATCHED)) return OrderGatewayExecutionStatus.SCRATCHED_RUNNERS;
        if (toteBetResponses.stream().allMatch(betResponse -> betResponse.toteBetResult() == ToteBetResult.POOL_CLOSED)) return OrderGatewayExecutionStatus.RACE_CLOSED;
        return OrderGatewayExecutionStatus.ERRONEOUS_ORDER;
    }
}
