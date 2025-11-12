package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.OpticonService;
import com.edgestackers.opticon.service.controller.OpticonServiceRestController;
import com.edgestackers.opticon.service.controller.OpticonServiceWebsocketController;
import com.edgestackers.opticon.service.message.OpticonDataService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class OpticonServiceModule {

    @Provides
    @Singleton
    public OpticonService opticonService(OpticonServiceRestController restController,
                                         OpticonServiceWebsocketController websocketController,
                                         OpticonDataService opticonDataService)
    {
        return new OpticonService(restController, websocketController, opticonDataService);
    }
}
