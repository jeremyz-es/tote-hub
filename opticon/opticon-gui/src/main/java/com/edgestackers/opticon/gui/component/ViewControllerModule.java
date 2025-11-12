package com.edgestackers.opticon.gui.component;

import com.edgestackers.opticon.gui.view.cycles.CycleViewPaneController;
import com.edgestackers.opticon.gui.view.execution.ExecutionView;
import com.edgestackers.opticon.gui.view.execution.ExecutionViewManager;
import com.edgestackers.opticon.gui.view.operations.OperationsView;
import com.edgestackers.opticon.gui.view.operations.OperationsViewController;
import com.edgestackers.opticon.gui.view.operations.OperationsViewModel;
import com.edgestackers.opticon.gui.view.order.OrderViewPane;
import com.edgestackers.opticon.gui.view.order.OrderViewPaneController;
import com.edgestackers.opticon.gui.view.order.OrderViewPaneModel;
import com.edgestackers.opticon.gui.view.pacman.PacmanFlucsViewPane;
import com.edgestackers.opticon.gui.view.pacman.PacmanFlucsViewPaneController;
import com.edgestackers.opticon.gui.view.pacman.PacmanFlucsViewPaneModel;
import com.edgestackers.opticon.gui.view.parameters.*;
import com.edgestackers.opticon.gui.view.parameters.crud.ToteStrategyParametersCrudView;
import com.edgestackers.opticon.gui.view.parameters.crud.ToteStrategyParametersCrudViewController;
import com.edgestackers.opticon.gui.view.parameters.crud.ToteStrategyParametersCrudViewModel;
import com.edgestackers.opticon.gui.view.race.RaceViewPane;
import com.edgestackers.opticon.gui.view.race.RaceViewPaneController;
import com.edgestackers.opticon.gui.view.cycles.CycleViewPane;
import com.edgestackers.opticon.gui.view.cycles.CycleViewPaneModel;
import com.edgestackers.opticon.gui.view.race.RaceViewPaneModel;
import com.edgestackers.opticon.gui.view.strategy.StrategyView;
import com.edgestackers.opticon.gui.view.strategy.StrategyViewController;
import com.edgestackers.opticon.gui.view.strategy.StrategyViewModel;
import com.edgestackers.opticon.gui.view.tote.ToteMainView;
import com.edgestackers.opticon.gui.view.tote.ToteMainViewController;
import com.edgestackers.opticon.gui.view.tote.ToteMainViewModel;
import com.edgestackers.opticon.gui.view.turbo.TurboViewPane;
import com.edgestackers.opticon.gui.view.turbo.TurboViewPaneController;
import com.edgestackers.opticon.gui.view.turbo.TurboViewPaneModel;
import com.edgestackers.tote.hub.core.parameters.CaesarApiClient;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ViewControllerModule {

    @Provides
    @Singleton
    public RaceViewPaneController raceViewController(RaceViewPane raceViewPane, RaceViewPaneModel raceViewPaneModel) {
        return new RaceViewPaneController(raceViewPane, raceViewPaneModel);
    }

    @Provides
    @Singleton
    public CycleViewPaneController cycleViewController(CycleViewPane cycleViewPane, CycleViewPaneModel cycleViewPaneModel) {
        return new CycleViewPaneController(cycleViewPane, cycleViewPaneModel);
    }

    @Provides
    @Singleton
    public ToteMainViewController accountBalanceViewController(ToteMainView toteMainView,
                                                               ToteMainViewModel toteMainViewModel,
                                                               RaceViewPaneController raceViewPaneController) {
        return new ToteMainViewController(toteMainView, toteMainViewModel, raceViewPaneController);
    }

    @Provides
    @Singleton
    public OrderViewPaneController orderSummaryViewController(OrderViewPane orderViewPane, OrderViewPaneModel orderViewPaneModel) {
        return new OrderViewPaneController(orderViewPane, orderViewPaneModel);
    }

    @Provides
    @Singleton
    public PacmanFlucsViewPaneController pacmanFlucsViewController(PacmanFlucsViewPane view, PacmanFlucsViewPaneModel viewModel) {
        return new PacmanFlucsViewPaneController(view, viewModel);
    }

    @Provides
    @Singleton
    public TurboViewPaneController turboViewPaneController(TurboViewPane view, TurboViewPaneModel viewModel) {
        return new TurboViewPaneController(view, viewModel);
    }

    @Provides
    @Singleton
    public OperationsViewController operationsViewController(OperationsView view, OperationsViewModel viewModel) {
        return new OperationsViewController(view, viewModel);
    }

    @Provides
    @Singleton
    public StrategyViewController strategyViewManager(StrategyView view,
                                                      StrategyViewModel viewModel,
                                                      RaceViewPaneController raceViewPaneController,
                                                      CycleViewPaneController cycleViewPaneController,
                                                      TurboViewPaneController turboViewPaneController,
                                                      PacmanFlucsViewPaneController pacmanFlucsViewPaneController)
    {
        return new StrategyViewController(
                view,
                viewModel,
                raceViewPaneController,
                cycleViewPaneController,
                turboViewPaneController,
                pacmanFlucsViewPaneController
        );
    }

    @Provides
    @Singleton
    public ExecutionViewManager executionViewManager(ExecutionView view,
                                                     RaceViewPaneController raceViewPaneController,
                                                     OrderViewPaneController orderViewPaneController)
    {
        return new ExecutionViewManager(
                view,
                raceViewPaneController,
                orderViewPaneController
        );
    }

    @Provides
    @Singleton
    public ToteStrategyParametersViewController toteStrategyParametersViewController(ToteStrategyParametersView view,
                                                                                     ToteStrategyParametersViewModel viewModel,
                                                                                     ToteStrategyParametersCrudViewController crudViewController,
                                                                                     CaesarApiClient caesarApiClient) {
        return new ToteStrategyParametersViewController(view, viewModel, crudViewController, caesarApiClient);
    }

    @Provides
    @Singleton
    public ToteStrategyParametersCrudViewController toteStrategyParametersCrudViewController(ToteStrategyParametersCrudView view,
                                                                                             ToteStrategyParametersCrudViewModel viewModel,
                                                                                             CaesarApiClient caesarApiClient) {
        return new ToteStrategyParametersCrudViewController(view, viewModel, caesarApiClient);
    }
}
