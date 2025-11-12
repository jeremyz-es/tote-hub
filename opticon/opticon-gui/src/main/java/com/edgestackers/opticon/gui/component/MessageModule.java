package com.edgestackers.opticon.gui.component;

import com.edgestackers.opticon.gui.message.OpticonMessageCacheRefresher;
import com.edgestackers.opticon.gui.message.OpticonMessageProcessor;
import com.edgestackers.opticon.gui.view.cycles.CycleViewPaneController;
import com.edgestackers.opticon.gui.view.operations.OperationsViewController;
import com.edgestackers.opticon.gui.view.order.OrderViewPaneController;
import com.edgestackers.opticon.gui.view.pacman.PacmanFlucsViewPaneController;
import com.edgestackers.opticon.gui.view.race.RaceViewPaneController;
import com.edgestackers.opticon.gui.view.tote.ToteMainViewController;
import com.edgestackers.opticon.gui.view.turbo.TurboViewPaneController;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class MessageModule {

    @Provides
    @Singleton
    public OpticonMessageProcessor opticonMessageProcessor(RaceViewPaneController raceViewPaneController,
                                                           CycleViewPaneController cycleViewPaneController,
                                                           ToteMainViewController toteMainViewController,
                                                           OrderViewPaneController orderViewPaneController,
                                                           TurboViewPaneController turboViewPaneController,
                                                           PacmanFlucsViewPaneController pacmanFlucsViewPaneController,
                                                           OperationsViewController operationsViewController)
    {
        return new OpticonMessageProcessor(
                raceViewPaneController,
                cycleViewPaneController,
                toteMainViewController,
                orderViewPaneController,
                turboViewPaneController,
                pacmanFlucsViewPaneController,
                operationsViewController
        );
    }

    @Provides
    @Singleton
    public OpticonMessageCacheRefresher opticonMessageCacheRefresher() {
        return new OpticonMessageCacheRefresher();
    }
}
