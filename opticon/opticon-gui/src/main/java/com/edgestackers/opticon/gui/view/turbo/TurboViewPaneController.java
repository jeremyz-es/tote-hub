package com.edgestackers.opticon.gui.view.turbo;

import com.edgestackers.opticon.common.datamodel.OpticonDividendPredictionUpdate;
import com.edgestackers.opticon.common.datamodel.OpticonTheoUpdate;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;
import com.edgestackers.opticon.gui.datamodel.filter.ExoticInstrumentFilter;

public class TurboViewPaneController {
    private final TurboViewPane view;
    private final TurboViewPaneModel viewModel;

    public TurboViewPaneController(TurboViewPane view, TurboViewPaneModel viewModel)
    {
        this.view = view;
        this.viewModel = viewModel;
    }

    public void initialize() {
        view.initialize();
        viewModel.initialize();
        initializeBindings();
    }

    public void handle(OpticonRaceSwitchKey opticonRaceSwitchKey) {
        viewModel.switchTo(opticonRaceSwitchKey);
    }

    public void handle(ExoticInstrumentFilter filter) {
        viewModel.switchTo(filter);
    }

    public void process(OpticonDividendPredictionUpdate update) {
        viewModel.process(update);
    }

    public void process(OpticonTheoUpdate update) {
        viewModel.process(update);
    }

    private void initializeBindings() {
        view.getDividendTableView().setItems(viewModel.getSortedDividends());
        view.getTheoTableView().setItems(viewModel.getSortedTheos());
    }
}
