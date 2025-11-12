package com.edgestackers.opticon.gui.view.pacman;

import com.edgestackers.opticon.gui.datamodel.pacman.PacmanRaceFlucsEntry;
import com.edgestackers.opticon.gui.datamodel.pacman.PacmanRunFlucsEntry;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.Field;
import java.util.Map;

import static com.edgestackers.opticon.gui.view.util.OpticonViewUtil.setAnchorsAndSize;

enum PacmanFlucsViewPaneInitializer {
    ;
    private static final Map<String, String> RACE_FLUCS_TABLE_VIEW_COLUMN_HEADER_NAMES = Map.ofEntries(
            Map.entry("qldTime", "QLD Time"),
            Map.entry("track", "T"),
            Map.entry("race", "R#"),
            Map.entry("lastFlucsUpdateDeltaSeconds", "Last Flucs Δs")
    );

    private static final Map<String, String> RUN_FLUCS_TABLE_VIEW_COLUMN_HEADER_NAMES = Map.ofEntries(
            Map.entry("tab", "#"),
            Map.entry("runnerName", "R"),
            Map.entry("runnerTotalMatched", "R. Total Matched"),
            Map.entry("marketTotalMatched", "Mkt. Total Matched")

    );

    static void createAndInitializeView(AnchorPane rootPane,
                                        TableView<PacmanRaceFlucsEntry> raceFlucsTableView,
                                        TableView<PacmanRunFlucsEntry> runFlucsTableView) {

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(raceFlucsTableView, runFlucsTableView);
        splitPane.setOrientation(Orientation.VERTICAL);
        setAnchorsAndSize(splitPane, null, null, 20, 20, 20, 20);
        rootPane.getChildren().addAll(splitPane);
    }

    static void initializeRaceFlucsTableView(TableView<PacmanRaceFlucsEntry> tableView) {
        for (Field field : PacmanRaceFlucsEntry.class.getDeclaredFields()) {
            if (RACE_FLUCS_TABLE_VIEW_COLUMN_HEADER_NAMES.containsKey(field.getName())) {
                String columnName = RACE_FLUCS_TABLE_VIEW_COLUMN_HEADER_NAMES.get(field.getName());
                TableColumn<PacmanRaceFlucsEntry, String> tableColumn = new TableColumn<>(columnName);
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                tableView.getColumns().add(tableColumn);
            }
        }
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);
    }

    static void initializeRunFlucsTableView(TableView<PacmanRunFlucsEntry> tableView) {
        for (Field field : PacmanRunFlucsEntry.class.getDeclaredFields()) {
            if (RUN_FLUCS_TABLE_VIEW_COLUMN_HEADER_NAMES.containsKey(field.getName())) {
                String columnName = RUN_FLUCS_TABLE_VIEW_COLUMN_HEADER_NAMES.get(field.getName());
                TableColumn<PacmanRunFlucsEntry, String> tableColumn = new TableColumn<>(columnName);
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                tableView.getColumns().add(tableColumn);
                continue;
            }
            if (field.getName().contains("tradedTwap")) {
                String columnName = field.getName().replace("tradedTwap", "Twap ");
                TableColumn<PacmanRunFlucsEntry, String> tableColumn = new TableColumn<>(columnName);
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                tableView.getColumns().add(tableColumn);
                continue;
            }
            if (field.getName().contains("tradedVolume")) {
                String columnName = field.getName().replace("tradedVolume", "Tvol ");
                TableColumn<PacmanRunFlucsEntry, String> tableColumn = new TableColumn<>(columnName);
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                tableView.getColumns().add(tableColumn);
            }
        }
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);
    }
}
