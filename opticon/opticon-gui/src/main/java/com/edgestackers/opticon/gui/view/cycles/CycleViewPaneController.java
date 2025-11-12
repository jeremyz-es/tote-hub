package com.edgestackers.opticon.gui.view.cycles;

import com.edgestackers.opticon.common.datamodel.OpticonMarketCycleUpdate;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;
import com.edgestackers.opticon.gui.datamodel.filter.ExoticInstrumentFilter;

import java.util.List;

public class CycleViewPaneController {
    private final CycleViewPane view;
    private final CycleViewPaneModel viewModel;

    public CycleViewPaneController(CycleViewPane view, CycleViewPaneModel viewModel) {
        this.view = view;
        this.viewModel = viewModel;
    }

    public void initialize() {
        view.initialize();
        viewModel.initialize();
        initializeBindings();
    }

    public void handle(List<OpticonMarketCycleUpdate> cycleUpdates) {
        cycleUpdates.forEach(this::handle);
    }

    public void handle(OpticonMarketCycleUpdate cycleUpdate) {
        viewModel.handle(cycleUpdate);
    }

    public void handle(OpticonRaceSwitchKey opticonRaceSwitchKey) {
        viewModel.switchTo(opticonRaceSwitchKey);
    }

    public void handle(ExoticInstrumentFilter filter) {
        viewModel.switchTo(filter);
    }

    private void initializeBindings() {
        view.getTableView().setItems(viewModel.getSortedUpdates());
    }
}
