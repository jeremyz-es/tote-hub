package com.edgestackers.opticon.gui.view.execution;

import com.edgestackers.opticon.common.datamodel.OpticonInitContext;
import com.edgestackers.opticon.common.datamodel.OpticonOrderUpdate;
import com.edgestackers.opticon.gui.view.OpticonViewType;
import com.edgestackers.opticon.gui.view.order.OrderViewPaneController;
import com.edgestackers.opticon.gui.view.race.RaceViewPaneController;

import java.util.Objects;

import static com.edgestackers.opticon.gui.settings.OpticonGuiSettingsManager.getSettingsFor;
import static com.edgestackers.opticon.gui.settings.OpticonGuiSettingsManager.saveOpticonGuiSettings;

public class ExecutionViewManager {
    private static final OpticonViewType VIEW_TYPE = OpticonViewType.EXECUTION_VIEW;
    private final ExecutionView view;
    private final RaceViewPaneController raceViewPaneController;
    private final OrderViewPaneController orderViewPaneController;

    public ExecutionViewManager(ExecutionView view,
                                RaceViewPaneController raceViewPaneController,
                                OrderViewPaneController orderViewPaneController)
    {
        this.view = view;
        this.raceViewPaneController = raceViewPaneController;
        this.orderViewPaneController = orderViewPaneController;
    }

    public void initialize() {
        view.initialize(getSettingsFor(OpticonViewType.EXECUTION_VIEW));
        orderViewPaneController.initialize();
        initializeBindings();
        initializeListeners();
    }

    public void handleInitContext(OpticonInitContext opticonInitContext) {
        opticonInitContext.orderUpdates().forEach(orderViewPaneController::process);
    }

    private void initializeBindings() {
        raceViewPaneController.registerRaceSwitchListener(orderViewPaneController::handle);
    }

    private void initializeListeners() {
        view.getStage().xProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().yProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().widthProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().heightProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
    }
}
