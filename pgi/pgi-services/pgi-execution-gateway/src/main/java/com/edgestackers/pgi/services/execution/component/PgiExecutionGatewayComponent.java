package com.edgestackers.pgi.services.execution.component;

import com.edgestackers.pgi.services.execution.PgiExecutionGateway;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        ExecutionModule.class,
        ExecutionGatewayModule.class,
        FileBetModule.class,
        GwsModule.class,
        MetadataModule.class,
        NatsModule.class,
        RestModule.class,
        WebsocketModule.class,
})
public interface PgiExecutionGatewayComponent {
    PgiExecutionGateway pgiExecutionGateway();
}
