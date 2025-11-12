package com.edgestackers.pgi.common.client;

import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

public enum GwsClientServiceConnectionConfigProvider {
    ;
    private static final SsmClient SSM_CLIENT = SsmClient.builder().region(Region.AP_SOUTHEAST_2).build();

    public static GwsClientServiceConnectionConfig retrieveAndCreateConnectionConfig(TotePoolJurisdiction totePoolJurisdiction,
                                                                                     String baseEndpoint) {
        String jurisParam = totePoolJurisdiction.name().toLowerCase();
        return new GwsClientServiceConnectionConfig(
                getParam("/tote/pgi/user"),
                getParam("/tote/pgi/password"),
                getParam(String.format("/tote/pgi/community/%s", jurisParam)),
                baseEndpoint,
                getParam(String.format("/tote/pgi/master/account/%s", jurisParam)),
                getParam(String.format("/tote/pgi/master/pin/%s", jurisParam)),
                getParam(String.format("/tote/pgi/rebate/account/%s", jurisParam)),
                getParam(String.format("/tote/pgi/rebate/pin/%s", jurisParam))
        );
    }

    private static String getParam(String parameterPath) {
        GetParameterRequest req = GetParameterRequest.builder()
                .name(parameterPath)
                .withDecryption(true)
                .build();
        return SSM_CLIENT.getParameter(req).parameter().value();
    }
}
