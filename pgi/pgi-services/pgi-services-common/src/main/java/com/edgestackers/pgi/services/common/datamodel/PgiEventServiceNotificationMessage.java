package com.edgestackers.pgi.services.common.datamodel;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CycleDataUpdatedNotificationMessage.class, name = "CycleDataUpdatedNotificationMessage"),
        @JsonSubTypes.Type(value = RaceOfficialNotificationMessage.class, name = "RaceOfficialNotificationMessage"),
})
public interface PgiEventServiceNotificationMessage {
}
