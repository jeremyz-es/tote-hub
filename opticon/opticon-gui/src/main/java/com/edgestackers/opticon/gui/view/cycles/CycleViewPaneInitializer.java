package com.edgestackers.opticon.gui.view.cycles;

import com.edgestackers.opticon.gui.datamodel.cycles.OpticonMarketCycleUpdateEntry;
import com.edgestackers.opticon.gui.settings.OpticonSceneSettings;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.lang.reflect.Field;
import java.util.Map;

import static com.edgestackers.opticon.gui.view.util.OpticonGuiResourceUtil.OPTICON_CSS;
import static com.edgestackers.opticon.gui.view.util.OpticonViewUtil.setAnchorsAndSize;

public enum CycleViewPaneInitializer {
    ;
    private static final Map<String, String> COLUMN_HEADER_MAPPINGS = Map.ofEntries(
            // Map.entry("track", "Track"),
            // Map.entry("race", "Race #"),
            // Map.entry("jurisdiction", "Jurisdiction"),
            // Map.entry("toteBetType", "Bet Type"),
            Map.entry("toteCycleType", "Cycle Type"),
            Map.entry("receivedAtSydTime", "Received @ (SYD)"),
            Map.entry("poolTotal", "Pool Size"),
            Map.entry("officialRaceJumpDeltaSeconds", "Official Jump"),
            Map.entry("previousCycleDeltaSeconds", "Previous Cycle Δs"),
            Map.entry("firstHorseInDeltaSeconds", "FH In Δs"),
            Map.entry("threeToGoDeltaSeconds", "3TG Δs"),
            Map.entry("twoToGoDeltaSeconds", "2TG Δs"),
            Map.entry("lastHorseInDeltaSeconds", "LH In Δs"),
            Map.entry("raceJumpedDeltaSeconds", "Race Jumped Δs")
    );

    static void createAndInitializeView(AnchorPane rootPane,
                                        TableView<OpticonMarketCycleUpdateEntry> tableView)
    {
        setAnchorsAndSize(tableView, null, null, 20, 20, 20, 20);
        rootPane.getChildren().addAll(tableView);
    }

    static void initializeTableView(TableView<OpticonMarketCycleUpdateEntry> tableView) {
        for (Field field : OpticonMarketCycleUpdateEntry.class.getDeclaredFields()) {
            if (COLUMN_HEADER_MAPPINGS.containsKey(field.getName())) {
                String columnName = COLUMN_HEADER_MAPPINGS.get(field.getName());
                TableColumn<OpticonMarketCycleUpdateEntry, String> tableColumn = new TableColumn<>(columnName);
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                tableView.getColumns().add(tableColumn);
            }
        }
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);
    }
}
