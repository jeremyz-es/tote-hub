package com.edgestackers.pgi.common.datamodel.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PgiOrderResponse.class, name = "PgiOrderResponseMessage"),
})
public interface PgiExecutionGatewayMessage {
}
