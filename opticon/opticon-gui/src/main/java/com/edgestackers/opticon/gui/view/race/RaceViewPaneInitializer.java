package com.edgestackers.opticon.gui.view.race;

import com.edgestackers.opticon.gui.datamodel.run.OpticonRunSummaryEntry;
import com.edgestackers.opticon.gui.view.util.OpticonViewUtil;
import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.datamodel.turbo.TurboExoticTheoModel;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.edgestackers.opticon.common.util.OpticonRunTheoTypeIdentifierFactory.createRunTheoTypeIdentifier;

enum RaceViewPaneInitializer {
    ;
    private static final String RACE_TYPE_SELECTOR_PROMPT = "Race Type";
    private static final String TRACK_SELECTOR_PROMPT = "Track";
    private static final String RACE_SELECTOR_PROMPT = "Race";
    private static final Map<String, String> TABLE_VIEW_COLUMN_HEADER_NAMES = Map.ofEntries(
            Map.entry("tab", "#"),
            Map.entry("runnerName", "R"),
            Map.entry("lastUpdatedAtQldTime", "LU@ (QLD)")
    );
    private static final List<String> ADDITIONAL_COLUMNS = List.of(
            createRunTheoTypeIdentifier(TurboExoticTheoModel.LAGRANGE_WIN, 0, 0),
            createRunTheoTypeIdentifier(TurboExoticTheoModel.SNAG_WIN, 0, 0),
            createRunTheoTypeIdentifier(TurboExoticTheoModel.SCROOGE_PLACE, 0, 0),
            createRunTheoTypeIdentifier(TurboExoticTheoModel.SCROOGE_PLACE, 3, 0)
    );

    static void createAndInitializeView(AnchorPane rootPane,
                                        ComboBox<RaceType> raceTypeSelector,
                                        ComboBox<String> trackSelector,
                                        ComboBox<Integer> raceSelector,
                                        Button previousRace,
                                        Button nextRace,
                                        Button playRace,
                                        Label timeUntilRaceLabel,
                                        TableView<OpticonRunSummaryEntry> tableView)
    {
        raceTypeSelector.setPromptText(RACE_TYPE_SELECTOR_PROMPT);
        trackSelector.setPromptText(TRACK_SELECTOR_PROMPT);
        raceSelector.setPromptText(RACE_SELECTOR_PROMPT);

        timeUntilRaceLabel.setTextFill(Color.WHITE);

        HBox controlsHBox = new HBox();
        controlsHBox.setSpacing(10);
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        rightSpacer.setMinWidth(20);
        controlsHBox.getChildren().addAll(raceTypeSelector, trackSelector, raceSelector, previousRace, nextRace, playRace, leftSpacer, timeUntilRaceLabel, rightSpacer);
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);

        OpticonViewUtil.setAnchorsAndSize(controlsHBox, 65, null, 20,20, null, 20);
        OpticonViewUtil.setAnchorsAndSize(tableView, null, null, 65, 20, 20, 20);
        rootPane.getChildren().addAll(controlsHBox, tableView);
    }

    static void initializeTableView(TableView<OpticonRunSummaryEntry> tableView,
                                    TableColumn<OpticonRunSummaryEntry, String> nswWinPoolColumn,
                                    TableColumn<OpticonRunSummaryEntry, String> vicWinPoolColumn,
                                    TableColumn<OpticonRunSummaryEntry, String> qldWinPoolColumn)
    {
        for (Field field : OpticonRunSummaryEntry.class.getDeclaredFields()) {
            if (!TABLE_VIEW_COLUMN_HEADER_NAMES.containsKey(field.getName())) continue;
            String columnName = TABLE_VIEW_COLUMN_HEADER_NAMES.get(field.getName());
            TableColumn<OpticonRunSummaryEntry, String> column = new TableColumn<>(columnName);
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            tableView.getColumns().add(column);
        }
        setUpWinPoolColumn(tableView, nswWinPoolColumn, "nswDesc");
        setUpWinPoolColumn(tableView, vicWinPoolColumn, "vicDesc");
        setUpWinPoolColumn(tableView, qldWinPoolColumn, "qldDesc");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);
        addDynamicTheoColumns(tableView);
    }

    private static void setUpWinPoolColumn(TableView<OpticonRunSummaryEntry> tableView,
                                           TableColumn<OpticonRunSummaryEntry, String> winPoolColumn,
                                           String propertyValueName)
    {
        tableView.getColumns().add(winPoolColumn);
        winPoolColumn.setCellValueFactory(new PropertyValueFactory<>(propertyValueName));
    }

    private static void addDynamicTheoColumns(TableView<OpticonRunSummaryEntry> tableView)
    {
        for (String key : RaceViewPaneInitializer.ADDITIONAL_COLUMNS) {
            TableColumn<OpticonRunSummaryEntry, String> column = new TableColumn<>(key);
            column.setCellValueFactory(cellData -> {
                OpticonRunSummaryEntry entry = cellData.getValue();
                return entry.getTheoProperty(key);
            });
            tableView.getColumns().add(column);
        }
    }
}
