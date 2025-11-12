package com.edgestackers.pgi.services.execution.metadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PgiMetadataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PgiMetadataService.class);
    private final PgiRaceMetadataCacheRefresher pgiRaceMetadataCacheRefresher;

    public PgiMetadataService(PgiRaceMetadataCacheRefresher pgiRaceMetadataCacheRefresher) {
        this.pgiRaceMetadataCacheRefresher = pgiRaceMetadataCacheRefresher;
    }

    public void start() {
        pgiRaceMetadataCacheRefresher.start();
        LOGGER.info("[{}] has started!", this.getClass().getSimpleName());
    }
}
