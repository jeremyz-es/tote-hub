package com.edgestackers.pgi.services.feed;

import com.edgestackers.pgi.services.feed.controller.PgiFeedServiceRestController;
import com.edgestackers.pgi.services.feed.information.PgiInformationRefresher;
import com.edgestackers.pgi.services.feed.metadata.PgiRaceMetadataCacheRefresher;
import com.edgestackers.pgi.services.feed.notification.EventNotificationSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PgiFeedService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiFeedService.class);
    private final PgiFeedServiceRestController pgiFeedServiceRestController;
    private final PgiInformationRefresher metadataRefresher;
    private final EventNotificationSubscriber eventNotificationSubscriber;
    private final PgiRaceMetadataCacheRefresher pgiRaceMetadataCacheRefresher;

    public PgiFeedService(PgiFeedServiceRestController pgiFeedServiceRestController,
                          PgiInformationRefresher metadataRefresher,
                          EventNotificationSubscriber eventNotificationSubscriber,
                          PgiRaceMetadataCacheRefresher pgiRaceMetadataCacheRefresher) {
        this.pgiFeedServiceRestController = pgiFeedServiceRestController;
        this.metadataRefresher = metadataRefresher;
        this.eventNotificationSubscriber = eventNotificationSubscriber;
        this.pgiRaceMetadataCacheRefresher = pgiRaceMetadataCacheRefresher;
    }

    public void start() {
        pgiFeedServiceRestController.start();
        metadataRefresher.start();
        eventNotificationSubscriber.start();
        pgiRaceMetadataCacheRefresher.start();
        LOGGER.info("[{}] has started!", getClass().getSimpleName());
        /*
            1. Request available programs
            2. Request program details
            3. Request program definition
            4. Request race definition
            5. Request cycle data
         */
    }
}
