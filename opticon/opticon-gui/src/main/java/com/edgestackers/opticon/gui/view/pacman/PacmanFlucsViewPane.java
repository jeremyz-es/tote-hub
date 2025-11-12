package com.edgestackers.opticon.gui.view.pacman;

import com.edgestackers.opticon.gui.datamodel.pacman.PacmanRaceFlucsEntry;
import com.edgestackers.opticon.gui.datamodel.pacman.PacmanRunFlucsEntry;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import static com.edgestackers.opticon.gui.view.pacman.PacmanFlucsViewPaneInitializer.*;

public class PacmanFlucsViewPane {
    private final AnchorPane rootPane = new AnchorPane();
    private final TableView<PacmanRaceFlucsEntry> raceFlucsTableView = new TableView<>();
    private final TableView<PacmanRunFlucsEntry> runFlucsTableView = new TableView<>();

    public void initialize() {
        createView();
    }

    private void createView() {
        createAndInitializeView(rootPane, raceFlucsTableView, runFlucsTableView);
        initializeRaceFlucsTableView(raceFlucsTableView);
        initializeRunFlucsTableView(runFlucsTableView);
    }

    public AnchorPane getRootPane() { return rootPane; }

    public TableView<PacmanRaceFlucsEntry> getRaceFlucsTableView() { return raceFlucsTableView; }

    public TableView<PacmanRunFlucsEntry> getRunFlucsTableView() { return runFlucsTableView; }
}
