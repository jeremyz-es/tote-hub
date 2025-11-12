package com.edgestackers.opticon.service.component;

import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.message.PacmanRaceFlucsMessageProcessor;
import com.edgestackers.opticon.service.metadata.MasterFieldsCache;
import com.edgestackers.opticon.service.metadata.RaceMetadataCache;
import com.edgestackers.opticon.service.pacman.PacmanRaceFlucsUpdateCache;
import com.edgestackers.opticon.service.pacman.PacmanRaceFlucsUpdateFactory;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class PacmanModule {

    @Provides
    @Singleton
    public PacmanRaceFlucsUpdateCache pacmanRaceFlucsUpdateCache() {
        return new PacmanRaceFlucsUpdateCache();
    }

    @Provides
    @Singleton
    public PacmanRaceFlucsUpdateFactory pacmanRaceFlucsUpdateFactory(RaceMetadataCache raceMetadataCache,
                                                                     MasterFieldsCache masterFieldsCache,
                                                                     PacmanRaceFlucsUpdateCache raceFlucsUpdateCache)
    {
        return new PacmanRaceFlucsUpdateFactory(
                raceMetadataCache,
                masterFieldsCache,
                raceFlucsUpdateCache
        );
    }

    @Provides
    @Singleton
    public PacmanRaceFlucsMessageProcessor pacmanRaceFlucsMessageProcessor(PacmanRaceFlucsUpdateFactory pacmanRaceFlucsUpdateFactory,
                                                                           PacmanRaceFlucsUpdateCache pacmanRaceFlucsUpdateCache,
                                                                           OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        return new PacmanRaceFlucsMessageProcessor(
                pacmanRaceFlucsUpdateFactory,
                pacmanRaceFlucsUpdateCache,
                opticonMessageWebsocketPublisher
        );
    }


}
