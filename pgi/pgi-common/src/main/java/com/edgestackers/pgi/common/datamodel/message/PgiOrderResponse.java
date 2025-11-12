package com.edgestackers.pgi.common.datamodel.message;

import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PgiOrderResponse(@JsonProperty("client_order_id") String clientOrderId,
                               @JsonProperty("epoch_nanos_timestamp") long receivedAtEpochNanos,
                               @JsonProperty("jurisdiction") TotePoolJurisdiction totePoolJurisdiction,
                               @JsonProperty("pgi_bet_responses") List<PgiBetResponse> pgiBetResponseSummaries) implements PgiExecutionGatewayMessage {

}
