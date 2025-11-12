package com.edgestackers.pgi.common.datamodel.message;

import com.edgestackers.pgi.common.datamodel.cycle.PgiCycleUpdate;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PgiCycleUpdate.class, name = "pgiCycleUpdateMessage"),
})
public interface PgiFeedServiceMessage {
}
