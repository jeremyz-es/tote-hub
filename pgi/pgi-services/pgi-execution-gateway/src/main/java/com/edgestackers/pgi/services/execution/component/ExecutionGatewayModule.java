package com.edgestackers.pgi.services.execution.component;

import com.edgestackers.pgi.services.execution.PgiExecutionGateway;
import com.edgestackers.pgi.services.execution.controller.PgiExecutionGatewayRestController;
import com.edgestackers.pgi.services.execution.filebet.FileBetService;
import com.edgestackers.pgi.services.execution.metadata.PgiMetadataService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ExecutionGatewayModule {

    @Provides
    @Singleton
    public PgiExecutionGateway pgiExecutionGateway(PgiExecutionGatewayRestController restController,
                                                   PgiMetadataService pgiMetadataService,
                                                   FileBetService fileBetService) {
        return new PgiExecutionGateway(restController, pgiMetadataService, fileBetService);
    }
}
