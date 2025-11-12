package com.edgestackers.opticon.gui.view.parameters;

import com.edgestackers.opticon.gui.datamodel.parameters.ToteStrategyParametersEntry;
import com.edgestackers.opticon.gui.view.OpticonViewType;
import com.edgestackers.opticon.gui.view.parameters.crud.ToteStrategyParametersCrudViewController;
import com.edgestackers.tote.hub.core.datamodel.core.*;
import com.edgestackers.tote.hub.core.parameters.CaesarApiClient;
import com.edgestackers.tote.hub.core.parameters.ToteStrategyParameters;
import jakarta.annotation.Nullable;
import javafx.collections.ObservableList;

import java.util.List;

import static com.edgestackers.opticon.gui.settings.OpticonGuiSettingsManager.getSettingsFor;
import static com.edgestackers.opticon.gui.settings.OpticonGuiSettingsManager.saveOpticonGuiSettings;

public class ToteStrategyParametersViewController {
    private static final OpticonViewType VIEW_TYPE = OpticonViewType.PARAMETERS_VIEW;
    private final ToteStrategyParametersView view;
    private final ToteStrategyParametersViewModel viewModel;
    private final ToteStrategyParametersCrudViewController crudViewController;
    private final CaesarApiClient caesarApiClient;

    public ToteStrategyParametersViewController(ToteStrategyParametersView view,
                                                ToteStrategyParametersViewModel viewModel,
                                                ToteStrategyParametersCrudViewController crudViewController,
                                                CaesarApiClient caesarApiClient)
    {
        this.view = view;
        this.viewModel = viewModel;
        this.crudViewController = crudViewController;
        this.caesarApiClient = caesarApiClient;
    }

    public void initialize() {
        crudViewController.initialize();
        initializeBindings();
        initializeListeners();
        view.initialize(getSettingsFor(VIEW_TYPE));
        viewModel.initialize();
        view.getTableView().refresh();
        crudViewController.registerOnCloseCallback(this::refreshParameters);
    }

    public void handle(ToteStrategyParameters parameters) {
        viewModel.handle(parameters);
    }

    private void initializeBindings() {
        view.getTableView().setItems(viewModel.getSortedEntries());
        view.getBetTypeSelector().getItems().addAll(ToteBetType.values());
        addCrudControllerBinding();
    }

    private void initializeListeners() {
        ObservableList<ToteBetType> checkedBetTypes = view.getBetTypeSelector().getCheckModel().getCheckedItems();
        viewModel.register(checkedBetTypes);
        view.getRefreshButton().setOnAction(action -> refreshParameters());
        view.getCreateButton().setOnAction(action -> crudViewController.open());
        view.getStage().xProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().yProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().widthProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
        view.getStage().heightProperty().addListener((obs, oldVal, newVal) -> saveOpticonGuiSettings(VIEW_TYPE, view.getStage()));
    }

    private void addCrudControllerBinding() {
        view.getTableView().setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !view.getTableView().getSelectionModel().isEmpty()) {
                @Nullable ToteStrategyParametersEntry selectedEntry = view.getTableView().getSelectionModel().getSelectedItem();
                if (selectedEntry == null) return;
                crudViewController.open(selectedEntry);
            }
        });
    }

    private void refreshParameters() {
        try {
            viewModel.clear();
            List<ToteStrategyParameters> strategyParameters = caesarApiClient.retrieveToteStrategyParameters();
            strategyParameters.forEach(viewModel::handle);
        }
        catch (Exception ignored) {}
    }
}
