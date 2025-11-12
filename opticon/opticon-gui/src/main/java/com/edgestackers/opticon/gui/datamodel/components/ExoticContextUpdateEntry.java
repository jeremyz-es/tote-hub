package com.edgestackers.opticon.gui.datamodel.components;

import com.edgestackers.tote.common.datamodel.context.ExecutionGatewayOrderStatus;
import com.edgestackers.tote.common.datamodel.context.NitroContextStatus;

public class ExoticContextUpdateEntry {
    private final long epochNanosTimestamp;
    private final String qldTime;
    private final NitroContextStatus nitroContextStatus;
    private final ExecutionGatewayOrderStatus executionGatewayOrderStatus;

    public ExoticContextUpdateEntry(long epochNanosTimestamp,
                                    String qldTIme,
                                    NitroContextStatus nitroContextStatus,
                                    ExecutionGatewayOrderStatus executionGatewayOrderStatus)
    {
        this.epochNanosTimestamp = epochNanosTimestamp;
        this.qldTime = qldTIme;
        this.nitroContextStatus = nitroContextStatus;
        this.executionGatewayOrderStatus = executionGatewayOrderStatus;
    }

    public long getEpochNanosTimestamp() {
        return epochNanosTimestamp;
    }

    public String getQldTime() {
        return qldTime;
    }

    public ExecutionGatewayOrderStatus getExecutionGatewayContext() {
        return executionGatewayOrderStatus;
    }

    public NitroContextStatus getNitroContextStatus() {
        return nitroContextStatus;
    }
}
