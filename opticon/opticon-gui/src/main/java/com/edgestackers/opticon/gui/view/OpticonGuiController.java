package com.edgestackers.opticon.gui.view;

import com.edgestackers.opticon.common.client.OpticonApiClient;
import com.edgestackers.opticon.common.datamodel.OpticonInitContext;
import com.edgestackers.opticon.gui.message.OpticonMessageSubscription;
import com.edgestackers.opticon.gui.view.execution.ExecutionViewManager;
import com.edgestackers.opticon.gui.view.operations.OperationsViewController;
import com.edgestackers.opticon.gui.view.parameters.ToteStrategyParametersViewController;
import com.edgestackers.opticon.gui.view.race.RaceViewPaneController;
import com.edgestackers.opticon.gui.view.strategy.StrategyViewController;
import com.edgestackers.opticon.gui.view.tote.ToteMainViewController;
import com.edgestackers.tote.hub.core.parameters.CaesarApiClient;
import com.edgestackers.tote.hub.core.parameters.ToteStrategyParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class OpticonGuiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpticonGuiController.class);
    private final OpticonApiClient opticonApiClient;
    private final OpticonMessageSubscription opticonMessageSubscription;
    private final RaceViewPaneController raceViewPaneController;
    private final ToteMainViewController toteMainViewController;
    private final OperationsViewController operationsViewController;
    private final StrategyViewController strategyViewController;
    private final ExecutionViewManager executionViewManager;
    private final ToteStrategyParametersViewController toteStrategyParametersViewController;
    private final CaesarApiClient caesarApiClient;

    public OpticonGuiController(OpticonApiClient opticonApiClient,
                                OpticonMessageSubscription opticonMessageSubscription,
                                RaceViewPaneController raceViewPaneController,
                                ToteMainViewController toteMainViewController,
                                OperationsViewController operationsViewController,
                                StrategyViewController strategyViewController,
                                ExecutionViewManager executionViewManager,
                                ToteStrategyParametersViewController toteStrategyParametersViewController,
                                CaesarApiClient caesarApiClient)
    {
        this.opticonApiClient = opticonApiClient;
        this.opticonMessageSubscription = opticonMessageSubscription;
        this.raceViewPaneController = raceViewPaneController;
        this.toteMainViewController = toteMainViewController;
        this.operationsViewController = operationsViewController;
        this.strategyViewController = strategyViewController;
        this.executionViewManager = executionViewManager;
        this.toteStrategyParametersViewController = toteStrategyParametersViewController;
        this.caesarApiClient = caesarApiClient;
    }

    public void initialize() {
        initializeControllers();
        initializeControllerBindings();
        initializeContext();
        opticonMessageSubscription.start();
    }

    private void initializeControllers() {
        toteMainViewController.initialize();
        operationsViewController.initialize();
        strategyViewController.initialize();
        executionViewManager.initialize();
        toteStrategyParametersViewController.initialize();
    }

    private void initializeControllerBindings() {
        raceViewPaneController.registerRaceSwitchListener(toteMainViewController::handle);
        raceViewPaneController.registerRaceSwitchListener(operationsViewController::handle);
    }

    private void initializeContext() {
        try {
            OpticonInitContext opticonInitContext = opticonApiClient.retrieveOpticonInitContext();
            raceViewPaneController.handle(opticonInitContext.esRaceMetadatas());
            opticonInitContext.runSummaries().forEach(raceViewPaneController::handle);
            opticonInitContext.balanceSummaries().forEach(toteMainViewController::handle);
            opticonInitContext.exoticContextSummaries().forEach(operationsViewController::process);
            strategyViewController.handleInitContext(opticonInitContext);
            executionViewManager.handleInitContext(opticonInitContext);
            List<ToteStrategyParameters> strategyParameters = caesarApiClient.retrieveToteStrategyParameters();
            strategyParameters.forEach(toteStrategyParametersViewController::handle);

        }
        catch (Exception e) {
            LOGGER.error("Failed to initialize start up context for Opticon due to {} - {}", e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
