package com.edgestackers.opticon.gui.view.pacman;

import com.edgestackers.opticon.common.datamodel.PacmanRaceFlucsUpdate;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;

public class PacmanFlucsViewPaneController {
    private final PacmanFlucsViewPane view;
    private final PacmanFlucsViewPaneModel viewModel;

    public PacmanFlucsViewPaneController(PacmanFlucsViewPane view,
                                         PacmanFlucsViewPaneModel viewModel)
    {
        this.view = view;
        this.viewModel = viewModel;
    }

    public void initialize() {
        view.initialize();
        viewModel.initialize();
        initializeBindings();
        initializeListeners();
    }

    public void handle(OpticonRaceSwitchKey opticonRaceSwitchKey) {
        viewModel.switchTo(opticonRaceSwitchKey);
    }

    public void process(PacmanRaceFlucsUpdate pacmanRaceFlucsUpdate) {
        viewModel.process(pacmanRaceFlucsUpdate);
    }

    private void initializeBindings() {
        view.getRunFlucsTableView().setItems(viewModel.getSortedPacmanRunFlucs());
        view.getRaceFlucsTableView().setItems(viewModel.getSortedPacmanRaceFlucs());
    }

    private void initializeListeners() {
        view.getRaceFlucsTableView().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> viewModel.show(newVal));
    }
}
