package com.edgestackers.opticon.common.client;

import com.edgestackers.opticon.common.message.OpticonMessage;

public interface OpticonMessageHandler {
    void handle(OpticonMessage opticonMessage);
}
