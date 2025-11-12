package com.edgestackers.opticon.gui.message;

import com.edgestackers.opticon.common.client.OpticonMessageHandler;
import com.edgestackers.opticon.common.datamodel.*;
import com.edgestackers.opticon.common.message.OpticonMessage;
import com.edgestackers.opticon.gui.view.cycles.CycleViewPaneController;
import com.edgestackers.opticon.gui.view.operations.OperationsViewController;
import com.edgestackers.opticon.gui.view.order.OrderViewPaneController;
import com.edgestackers.opticon.gui.view.pacman.PacmanFlucsViewPaneController;
import com.edgestackers.opticon.gui.view.race.RaceViewPaneController;
import com.edgestackers.opticon.gui.view.tote.ToteMainViewController;
import com.edgestackers.opticon.gui.view.turbo.TurboViewPaneController;

public class OpticonMessageProcessor implements OpticonMessageHandler {
    private final RaceViewPaneController raceViewPaneController;
    private final CycleViewPaneController cycleViewPaneController;
    private final ToteMainViewController toteMainViewController;
    private final OrderViewPaneController orderViewPaneController;
    private final TurboViewPaneController turboViewPaneController;
    private final PacmanFlucsViewPaneController pacmanFlucsViewPaneController;
    private final OperationsViewController operationsViewController;

    public OpticonMessageProcessor(RaceViewPaneController raceViewPaneController,
                                   CycleViewPaneController cycleViewPaneController,
                                   ToteMainViewController toteMainViewController,
                                   OrderViewPaneController orderViewPaneController,
                                   TurboViewPaneController turboViewPaneController,
                                   PacmanFlucsViewPaneController pacmanFlucsViewPaneController,
                                   OperationsViewController operationsViewController)
    {
        this.raceViewPaneController = raceViewPaneController;
        this.cycleViewPaneController = cycleViewPaneController;
        this.toteMainViewController = toteMainViewController;
        this.orderViewPaneController = orderViewPaneController;
        this.turboViewPaneController = turboViewPaneController;
        this.pacmanFlucsViewPaneController = pacmanFlucsViewPaneController;
        this.operationsViewController = operationsViewController;
    }

    public void handle(OpticonMessage opticonMessage) {
        if (opticonMessage instanceof OpticonRunSummary runSummary) raceViewPaneController.handle(runSummary);
        if (opticonMessage instanceof OpticonWinPoolUpdate winPoolUpdate) raceViewPaneController.handle(winPoolUpdate);
        if (opticonMessage instanceof OpticonMarketCycleUpdate update) cycleViewPaneController.handle(update);
        if (opticonMessage instanceof OpticonAccountBalanceSummary update) toteMainViewController.handle(update);
        if (opticonMessage instanceof OpticonOrderUpdate update) orderViewPaneController.process(update);
        if (opticonMessage instanceof OpticonDividendPredictionUpdate update) turboViewPaneController.process(update);
        if (opticonMessage instanceof OpticonTheoUpdate update) turboViewPaneController.process(update);
        if (opticonMessage instanceof PacmanRaceFlucsUpdate update) pacmanFlucsViewPaneController.process(update);
        if (opticonMessage instanceof OpticonStrategyContextSummary update) operationsViewController.process(update);
    }
}
