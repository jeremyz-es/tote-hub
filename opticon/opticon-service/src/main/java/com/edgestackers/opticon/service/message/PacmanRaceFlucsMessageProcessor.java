package com.edgestackers.opticon.service.message;

import com.edgestackers.opticon.common.datamodel.PacmanRaceFlucsUpdate;
import com.edgestackers.opticon.service.controller.publisher.OpticonMessageWebsocketPublisher;
import com.edgestackers.opticon.service.pacman.PacmanRaceFlucsUpdateCache;
import com.edgestackers.opticon.service.pacman.PacmanRaceFlucsUpdateFactory;
import com.edgestackers.tote.hub.core.datamodel.message.pacman.PacmanRaceFlucsMessage;
import jakarta.annotation.Nullable;

public class PacmanRaceFlucsMessageProcessor {
    private final PacmanRaceFlucsUpdateFactory pacmanRaceFlucsUpdateFactory;
    private final PacmanRaceFlucsUpdateCache pacmanRaceFlucsUpdateCache;
    private final OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher;

    public PacmanRaceFlucsMessageProcessor(PacmanRaceFlucsUpdateFactory pacmanRaceFlucsUpdateFactory,
                                           PacmanRaceFlucsUpdateCache pacmanRaceFlucsUpdateCache,
                                           OpticonMessageWebsocketPublisher opticonMessageWebsocketPublisher)
    {
        this.pacmanRaceFlucsUpdateFactory = pacmanRaceFlucsUpdateFactory;
        this.pacmanRaceFlucsUpdateCache = pacmanRaceFlucsUpdateCache;
        this.opticonMessageWebsocketPublisher = opticonMessageWebsocketPublisher;
    }

    public void process(PacmanRaceFlucsMessage message) {
        @Nullable PacmanRaceFlucsUpdate pacmanRaceFlucsUpdate = pacmanRaceFlucsUpdateFactory.createUpdate(message);
        if (pacmanRaceFlucsUpdate == null) return;
        pacmanRaceFlucsUpdateCache.put(pacmanRaceFlucsUpdate);
        opticonMessageWebsocketPublisher.publish(pacmanRaceFlucsUpdate);
    }
}
