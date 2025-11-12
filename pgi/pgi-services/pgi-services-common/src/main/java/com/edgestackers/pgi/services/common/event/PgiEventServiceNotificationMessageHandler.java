package com.edgestackers.pgi.services.common.event;

import com.edgestackers.pgi.services.common.datamodel.PgiEventServiceNotificationMessage;

public interface PgiEventServiceNotificationMessageHandler {
    void handleNotification(PgiEventServiceNotificationMessage message);
}
