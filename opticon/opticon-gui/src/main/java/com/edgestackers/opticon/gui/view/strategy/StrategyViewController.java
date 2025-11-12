package com.edgestackers.opticon.gui.view.strategy;

import com.edgestackers.core.datamodel.tote.ToteBetType;
import com.edgestackers.core.datamodel.tote.TotePoolJurisdiction;
import com.edgestackers.opticon.common.datamodel.OpticonInitContext;
import com.edgestackers.opticon.gui.control.listener.ExoticInstrumentFilterListener;
import com.edgestackers.opticon.gui.datamodel.filter.ExoticInstrumentFilter;
import com.edgestackers.opticon.gui.view.OpticonViewType;
import com.edgestackers.opticon.gui.view.cycles.CycleViewPaneController;
import com.edgestackers.opticon.gui.view.pacman.PacmanFlucsViewPaneController;
import com.edgestackers.opticon.gui.view.race.RaceViewPaneController;
import com.edgestackers.opticon.gui.view.turbo.TurboViewPaneController;
import javafx.collections.FXCollections;

import java.util.HashSet;
import java.util.Set;

import static com.edgestackers.opticon.gui.settings.OpticonGuiSettingsManager.getSettingsFor;
import static com.edgestackers.opticon.gui.settings.OpticonGuiSettingsManager.saveOpticonGuiSettings;

public class StrategyViewController {
    private static final OpticonViewType VIEW_TYPE = OpticonViewType.STRATEGY_VIEW;
    private final StrategyView view;
    private final StrategyViewModel viewModel;
    private final RaceViewPaneController raceViewPaneController;
    private final CycleViewPaneController cycleViewPaneController;
    private final TurboViewPaneController turboViewPaneController;
    private final PacmanFlucsViewPaneController pacmanFlucsViewPaneController;
    private final Set<ExoticInstrumentFilterListener> filterListeners = new HashSet<>();

    public StrategyViewController(StrategyView view,
                                  StrategyViewModel viewModel,
                                  RaceViewPaneController raceViewPaneController,
                                  CycleViewPaneController cycleViewPaneController,
                                  TurboViewPaneController turboViewPaneController,
                                  PacmanFlucsViewPaneController pacmanFlucsViewPaneController)
    {
        this.view = view;
        this.viewModel = viewModel;
        this.raceViewPaneController = raceViewPaneController;
        this.cycleViewPaneController = cycleViewPaneController;
        this.turboViewPaneController = turboViewPaneController;
        this.pacmanFlucsViewPaneController = pacmanFlucsViewPaneController;
    }

    public void initialize() {
        view.initialize(getSettingsFor(OpticonViewType.STRATEGY_VIEW));
        viewModel.initialize();
        cycleViewPaneController.initialize();
        turboViewPaneController.initialize();
        pacmanFlucsViewPaneController.initialize();
        initializeBindings();
        initializeListeners();
    }

    public void handleInitContext(OpticonInitContext opticonInitContext) {
        cycleViewPaneController.handle(opticonInitContext.cycleUpdates());
        opticonInitContext.dividendPredictionUpdates().forEach(turboViewPaneController::process);
        opticonInitContext.theoUpdates().forEach(turboViewPaneController::process);
        opticonInitContext.pacmanRaceFlucsUpdates().forEach(pacmanFlucsViewPaneController::process);
    }

    private void initializeBindings() {
        view.getBetTypeSelector().setItems(FXCollections.observableArrayList(ToteBetType.values()));
        view.getJurisdictionSelector().setItems(FXCollections.observableArrayList(TotePoolJurisdiction.values()));
        view.getBetTypeSelector().valueProperty().bindBidirectional(viewModel.selectedBetTypeProperty());
        view.getJurisdictionSelector().valueProperty().bindBidirectional(viewModel.selectedJurisdictionProperty());
        raceViewPaneController.registerRaceSwitchListener(cycleViewPaneController::handle);
        raceViewPaneController.registerRaceSwitchListener(turboViewPaneController::handle);
        raceViewPaneController.registerRaceSwitchListener(pacmanFlucsViewPaneController::handle);
    }

    private void initializeListeners() {
        view.getJurisdictionSelector().valueProperty().addListener((obs, oldVal, newVal) -> notifyInstrumentFilter());
        view.getBetTypeSelector().valueProperty().addListener((obs, oldVal, newVal) -> notifyInstrumentFilter());
        filterListeners.add(cycleViewPaneController::handle);
        filterListeners.add(turboViewPaneController::handle);
        view.getStage().xProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().yProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().widthProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().heightProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
    }

    private void notifyInstrumentFilter() {
        ToteBetType toteBetType = view.getBetTypeSelector().getValue();
        TotePoolJurisdiction jurisdiction = view.getJurisdictionSelector().getValue();
        ExoticInstrumentFilter filter = new ExoticInstrumentFilter(toteBetType, jurisdiction);
        filterListeners.forEach(filterListener -> filterListener.handle(filter));
    }
}
