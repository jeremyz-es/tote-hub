package com.edgestackers.opticon.gui.component;

import com.edgestackers.opticon.gui.view.operations.OperationsViewModel;
import com.edgestackers.opticon.gui.view.order.OrderViewPaneModel;
import com.edgestackers.opticon.gui.view.pacman.PacmanFlucsViewPaneModel;
import com.edgestackers.opticon.gui.view.parameters.crud.ToteStrategyParametersCrudViewModel;
import com.edgestackers.opticon.gui.view.parameters.ToteStrategyParametersViewModel;
import com.edgestackers.opticon.gui.view.race.RaceViewPaneModel;
import com.edgestackers.opticon.gui.view.cycles.CycleViewPaneModel;
import com.edgestackers.opticon.gui.view.strategy.StrategyViewModel;
import com.edgestackers.opticon.gui.view.tote.ToteMainViewModel;
import com.edgestackers.opticon.gui.view.turbo.TurboViewPaneModel;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ViewModelModule {

    @Provides
    @Singleton
    public CycleViewPaneModel cycleViewModel() {
        return new CycleViewPaneModel();
    }

    @Provides
    @Singleton
    public RaceViewPaneModel raceViewModel() {
        return new RaceViewPaneModel();
    }

    @Provides
    @Singleton
    public ToteMainViewModel accountBalanceViewModel() {
        return new ToteMainViewModel();
    }

    @Provides
    @Singleton
    public TurboViewPaneModel turboViewModel() {
        return new TurboViewPaneModel();
    }

    @Provides
    @Singleton
    public OrderViewPaneModel orderSummaryViewModel() {
        return new OrderViewPaneModel();
    }

    @Provides
    @Singleton
    public PacmanFlucsViewPaneModel pacmanFlucsViewModel() {
        return new PacmanFlucsViewPaneModel();
    }

    @Provides
    @Singleton
    public OperationsViewModel operationsViewModel() {
        return new OperationsViewModel();
    }

    @Provides
    @Singleton
    public StrategyViewModel strategyViewModel() {
        return new StrategyViewModel();
    }

    @Provides
    @Singleton
    public ToteStrategyParametersCrudViewModel toteStrategyParametersCrudViewModel() {
        return new ToteStrategyParametersCrudViewModel();
    }

    @Provides
    @Singleton
    public ToteStrategyParametersViewModel toteStrategyParametersViewModel() {
        return new ToteStrategyParametersViewModel();
    }
}
