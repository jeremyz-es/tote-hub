package com.edgestackers.pgi.common.client;

import com.amtote.gws.GpHeader;

public enum GpHeaderProvider {
    ;
    private static final int DEFAULT_GP_HEADER_CHANNEL = 0;
    private static final String DEFAULT_GP_HEADER_AUDIT_TRAIL = "es01";

    public static GpHeader createGpHeader(GwsClientServiceConnectionConfig connectionConfig) {
        GpHeader gpHeader = new GpHeader();
        gpHeader.setUserName(connectionConfig.username());
        gpHeader.setPassWord(connectionConfig.password());
        gpHeader.setCmty(connectionConfig.community());
        gpHeader.setChannel(DEFAULT_GP_HEADER_CHANNEL);
        gpHeader.setAuditTrail(DEFAULT_GP_HEADER_AUDIT_TRAIL);
        return gpHeader;
    }
}
