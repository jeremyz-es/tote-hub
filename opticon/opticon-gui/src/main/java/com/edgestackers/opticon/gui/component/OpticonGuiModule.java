package com.edgestackers.opticon.gui.component;

import com.edgestackers.opticon.common.client.OpticonApiClient;
import com.edgestackers.opticon.gui.message.OpticonMessageSubscription;
import com.edgestackers.opticon.gui.view.OpticonGuiController;
import com.edgestackers.opticon.gui.view.execution.ExecutionViewManager;
import com.edgestackers.opticon.gui.view.operations.OperationsViewController;
import com.edgestackers.opticon.gui.view.parameters.ToteStrategyParametersViewController;
import com.edgestackers.opticon.gui.view.race.RaceViewPaneController;
import com.edgestackers.opticon.gui.view.strategy.StrategyViewController;
import com.edgestackers.opticon.gui.view.tote.ToteMainViewController;
import com.edgestackers.tote.hub.core.parameters.CaesarApiClient;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class OpticonGuiModule {

    @Provides
    @Singleton
    public OpticonGuiController opticonGuiController(OpticonApiClient opticonApiClient,
                                                     OpticonMessageSubscription opticonMessageSubscription,
                                                     RaceViewPaneController raceViewPaneController,
                                                     ToteMainViewController toteMainViewController,
                                                     OperationsViewController operationsViewController,
                                                     StrategyViewController strategyViewController,
                                                     ExecutionViewManager executionViewManager,
                                                     ToteStrategyParametersViewController toteStrategyParametersViewController,
                                                     CaesarApiClient caesarApiClient)
    {
        return new OpticonGuiController(
                opticonApiClient,
                opticonMessageSubscription,
                raceViewPaneController,
                toteMainViewController,
                operationsViewController,
                strategyViewController,
                executionViewManager,
                toteStrategyParametersViewController,
                caesarApiClient
        );
    }
}
