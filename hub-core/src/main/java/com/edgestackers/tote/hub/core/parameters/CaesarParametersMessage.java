package com.edgestackers.tote.hub.core.parameters;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ToteStrategyParameters.class, name = "toteStrategyParameters")
})
public interface CaesarParametersMessage {
}
