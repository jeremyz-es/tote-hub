package com.edgestackers.pgi.services.feed.component;

import com.amtote.gws.GwsSoap;
import com.edgestackers.pgi.common.client.GwsClientInfoProvider;
import com.edgestackers.pgi.services.common.datamodel.PgiTotePoolId;
import com.edgestackers.pgi.services.feed.cycle.PgiCycleUpdateProcessor;
import com.edgestackers.pgi.services.feed.information.PgiRaceInformationCache;
import com.edgestackers.pgi.services.feed.metadata.PgiRaceMetadataCache;
import com.edgestackers.pgi.services.feed.notification.*;
import com.edgestackers.pgi.services.feed.publisher.PgiMessageNatsPublisher;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class EventNotificationModule {
    private final String eventServiceSubscriptionEndpoint;
    private final boolean enableScratchingPublishing;
    private final TotePoolJurisdiction totePoolJurisdiction;

    public EventNotificationModule(String eventServiceSubscriptionEndpoint,
                                   boolean enableScratchingPublishing,
                                   TotePoolJurisdiction totePoolJurisdiction)
    {
        this.eventServiceSubscriptionEndpoint = eventServiceSubscriptionEndpoint;
        this.enableScratchingPublishing = enableScratchingPublishing;
        this.totePoolJurisdiction = totePoolJurisdiction;
    }

    @Provides
    @Singleton
    public CycleDataUpdatedNotificationProcessor cycleDataUpdatedNotificationProcessor(GwsSoap gwsSoap,
                                                                                       GwsClientInfoProvider gwsClientInfoProvider,
                                                                                       PgiRaceInformationCache pgiRaceInformationCache,
                                                                                       PgiCycleUpdateProcessor pgiCycleUpdateProcessor,
                                                                                       PgiTotePoolId pgiTotePoolId) {
        return new CycleDataUpdatedNotificationProcessor(
                gwsSoap,
                gwsClientInfoProvider,
                pgiRaceInformationCache,
                pgiCycleUpdateProcessor,
                pgiTotePoolId
        );
    }

    @Provides
    @Singleton
    public RunnerScratchedNotificationMessageProcessor runnerScratchedNotificationMessageProcessor(PgiRaceMetadataCache pgiRaceMetadataCache,
                                                                                                   PgiMessageNatsPublisher natsPublisher) {
        return new RunnerScratchedNotificationMessageProcessor(
                pgiRaceMetadataCache,
                natsPublisher,
                enableScratchingPublishing
        );
    }

    @Provides
    @Singleton
    public RaceOfficialNotificationMessageProcessor raceOfficialNotificationMessageProcessor(GwsSoap gwsSoap,
                                                                                             GwsClientInfoProvider gwsClientInfoProvider,
                                                                                             PgiRaceMetadataCache raceMetadataCache,
                                                                                             PgiTotePoolId pgiTotePoolId,
                                                                                             PgiMessageNatsPublisher natsPublisher) {
        return new RaceOfficialNotificationMessageProcessor(
                gwsSoap,
                gwsClientInfoProvider,
                raceMetadataCache,
                pgiTotePoolId,
                natsPublisher,
                totePoolJurisdiction
        );
    }

    @Provides
    @Singleton
    public EventNotificationProcessor eventNotificationProcessor(CycleDataUpdatedNotificationProcessor cycleDataUpdatedNotificationProcessor,
                                                                 RunnerScratchedNotificationMessageProcessor runnerScratchedNotificationMessageProcessor,
                                                                 RaceOfficialNotificationMessageProcessor raceOfficialNotificationMessageProcessor)
    {
        return new EventNotificationProcessor(
                cycleDataUpdatedNotificationProcessor,
                runnerScratchedNotificationMessageProcessor,
                raceOfficialNotificationMessageProcessor
        );
    }

    @Provides
    @Singleton
    public EventNotificationSubscriber eventNotificationSubscriber(EventNotificationProcessor eventNotificationProcessor) {
        return new EventNotificationSubscriber(eventServiceSubscriptionEndpoint, eventNotificationProcessor);
    }
}
