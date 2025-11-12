package com.edgestackers.opticon.gui.view.turbo;

import com.edgestackers.opticon.gui.datamodel.turbo.OpticonDividendPredictionUpdateEntry;
import com.edgestackers.opticon.gui.datamodel.turbo.OpticonTheoUpdateEntry;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import static com.edgestackers.opticon.gui.view.turbo.TurboViewPaneInitializer.*;

public class TurboViewPane {
    private final AnchorPane rootPane = new AnchorPane();
    private final TableView<OpticonDividendPredictionUpdateEntry> dividendTableView = new TableView<>();
    private final TableView<OpticonTheoUpdateEntry> theoTableView = new TableView<>();

    public void initialize() {
        createView();
    }

    private void createView() {
        createAndInitializeView(rootPane, dividendTableView, theoTableView);
        initializeDividendTableView(dividendTableView);
        initializeTheoTableView(theoTableView);
    }

    public AnchorPane getRootPane() { return rootPane; }

    public TableView<OpticonDividendPredictionUpdateEntry> getDividendTableView() { return dividendTableView; }

    public TableView<OpticonTheoUpdateEntry> getTheoTableView() { return theoTableView; }
}
