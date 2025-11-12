package com.edgestackers.opticon.gui.view.race;

import com.edgestackers.opticon.gui.datamodel.run.OpticonRunSummaryEntry;
import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import static com.edgestackers.opticon.gui.view.race.RaceViewPaneInitializer.createAndInitializeView;
import static com.edgestackers.opticon.gui.view.race.RaceViewPaneInitializer.initializeTableView;

public class RaceViewPane {
    private final AnchorPane rootPane = new AnchorPane();
    private final ComboBox<RaceType> raceTypeSelector = new ComboBox<>();
    private final ComboBox<String> trackSelector = new ComboBox<>();
    private final ComboBox<Integer> raceSelector = new ComboBox<>();
    private final Button previousRaceButton = new Button("<");
    private final Button nextRaceButton = new Button(">");
    private final Button playRaceButton = new Button("Play");
    private final Label timeUntilRaceLabel = new Label();
    private final TableView<OpticonRunSummaryEntry> tableView = new TableView<>();
    private final TableColumn<OpticonRunSummaryEntry, String> nswWinPoolColumn = new TableColumn<>();
    private final TableColumn<OpticonRunSummaryEntry, String> vicWinPoolColumn = new TableColumn<>();
    private final TableColumn<OpticonRunSummaryEntry, String> qldWinPoolColumn = new TableColumn<>();

    public void initialize() {
        createView();
    }

    public ComboBox<RaceType> getRaceTypeSelector() { return raceTypeSelector; }

    public ComboBox<String> getTrackSelector() { return trackSelector; }

    public ComboBox<Integer> getRaceSelector() { return raceSelector; }

    public Button getPreviousRaceButton() { return previousRaceButton; }

    public Button getNextRaceButton() { return nextRaceButton; }

    public Button getPlayRaceButton() { return playRaceButton; }

    public Label getTimeUntilRaceLabel() { return timeUntilRaceLabel; }

    public AnchorPane getRootPane() { return rootPane; }

    public TableView<OpticonRunSummaryEntry> getTableView() {
        return tableView;
    }

    public TableColumn<OpticonRunSummaryEntry, String> getNswWinPoolColumn() { return nswWinPoolColumn; }

    public TableColumn<OpticonRunSummaryEntry, String> getVicWinPoolColumn() { return vicWinPoolColumn; }

    public TableColumn<OpticonRunSummaryEntry, String> getQldWinPoolColumn() { return qldWinPoolColumn; }

    private void createView() {
        createAndInitializeView(rootPane, raceTypeSelector, trackSelector, raceSelector,
                previousRaceButton, nextRaceButton, playRaceButton, timeUntilRaceLabel, tableView);
        initializeTableView(tableView, nswWinPoolColumn, vicWinPoolColumn, qldWinPoolColumn);
    }
}
