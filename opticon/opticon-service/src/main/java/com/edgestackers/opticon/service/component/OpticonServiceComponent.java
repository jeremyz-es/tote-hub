package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.OpticonService;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        CycleUpdateModule.class,
        DataModule.class,
        ExoticDividendModule.class,
        ExoticSummaryModule.class,
        ExoticTheoModule.class,
        MetadataModule.class,
        NatsModule.class,
        NitroModule.class,
        OperationsModule.class,
        OpticonServiceModule.class,
        OrderModule.class,
        PacmanModule.class,
        ParametersModule.class,
        RaceEventModule.class,
        RestModule.class,
        RunSummaryModule.class,
        TheoModule.class,
        WebsocketModule.class,
})
public interface OpticonServiceComponent {
    OpticonService opticonService();
}
