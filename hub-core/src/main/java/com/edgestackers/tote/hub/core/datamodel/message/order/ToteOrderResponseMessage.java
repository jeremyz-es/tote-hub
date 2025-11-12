package com.edgestackers.tote.hub.core.datamodel.message.order;

import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.message.ToteMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ToteOrderResponseMessage(@JsonProperty("tote_provider") ToteProvider toteProvider,
                                       @JsonProperty("generated_at_epoch_nanos") long epochNanosTimestamp,
                                       @JsonProperty("tote_pool_jurisdiction") TotePoolJurisdiction totePoolJurisdiction,
                                       @JsonProperty("client_order_id") String clientOrderId,
                                       @JsonProperty("tote_bet_responses") List<ToteBetResponse> toteBetResponses) implements ToteMessage {
}
