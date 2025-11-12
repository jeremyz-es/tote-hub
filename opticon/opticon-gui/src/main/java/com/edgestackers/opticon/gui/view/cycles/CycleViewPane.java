package com.edgestackers.opticon.gui.view.cycles;

import com.edgestackers.opticon.gui.datamodel.cycles.OpticonMarketCycleUpdateEntry;
import com.edgestackers.opticon.gui.settings.OpticonSceneSettings;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static com.edgestackers.opticon.gui.view.cycles.CycleViewPaneInitializer.createAndInitializeView;
import static com.edgestackers.opticon.gui.view.cycles.CycleViewPaneInitializer.initializeTableView;

public class CycleViewPane {
    private final AnchorPane rootPane = new AnchorPane();

    private final TableView<OpticonMarketCycleUpdateEntry> tableView = new TableView<>();

    public void initialize() {
        createView();
    }

    private void createView() {
        createAndInitializeView(rootPane, tableView);
        initializeTableView(tableView);
    }

    public AnchorPane getRootPane() { return rootPane; }

    public TableView<OpticonMarketCycleUpdateEntry> getTableView() { return tableView; }
}
