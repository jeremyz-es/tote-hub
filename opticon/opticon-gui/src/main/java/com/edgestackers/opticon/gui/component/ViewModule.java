package com.edgestackers.opticon.gui.component;

import com.edgestackers.opticon.gui.view.cycles.CycleViewPane;
import com.edgestackers.opticon.gui.view.execution.ExecutionView;
import com.edgestackers.opticon.gui.view.operations.OperationsView;
import com.edgestackers.opticon.gui.view.order.OrderViewPane;
import com.edgestackers.opticon.gui.view.pacman.PacmanFlucsViewPane;
import com.edgestackers.opticon.gui.view.parameters.crud.ToteStrategyParametersCrudView;
import com.edgestackers.opticon.gui.view.parameters.ToteStrategyParametersView;
import com.edgestackers.opticon.gui.view.race.RaceViewPane;
import com.edgestackers.opticon.gui.view.strategy.StrategyView;
import com.edgestackers.opticon.gui.view.tote.ToteMainView;
import com.edgestackers.opticon.gui.view.turbo.TurboViewPane;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ViewModule {

    @Provides
    @Singleton
    public RaceViewPane raceView() {
        return new RaceViewPane();
    }

    @Provides
    @Singleton
    public CycleViewPane cycleView() {
        return new CycleViewPane();
    }

    @Provides
    @Singleton
    public ToteMainView accountBalanceView(RaceViewPane raceViewPane) {
        return new ToteMainView(raceViewPane);
    }

    @Provides
    @Singleton
    public TurboViewPane turboView() {
        return new TurboViewPane();
    }

    @Provides
    @Singleton
    public OrderViewPane orderSummaryView() {
        return new OrderViewPane();
    }

    @Provides
    @Singleton
    public PacmanFlucsViewPane pacmanFlucsView() {
        return new PacmanFlucsViewPane();
    }

    @Provides
    @Singleton
    public OperationsView operationsView() {
        return new OperationsView();
    }

    @Provides
    @Singleton
    public StrategyView strategyView(CycleViewPane cycleViewPane,
                                     TurboViewPane turboViewPane,
                                     PacmanFlucsViewPane pacmanFlucsViewPane)
    {
        return new StrategyView(cycleViewPane, turboViewPane, pacmanFlucsViewPane);
    }

    @Provides
    @Singleton
    public ExecutionView executionView(OrderViewPane orderViewPane) {
        return new ExecutionView(orderViewPane);
    }

    @Provides
    @Singleton
    public ToteStrategyParametersCrudView toteStrategyParametersCrudView() {
        return new ToteStrategyParametersCrudView();
    }

    @Provides
    @Singleton
    public ToteStrategyParametersView toteStrategyParametersView() {
        return new ToteStrategyParametersView();
    }
}

