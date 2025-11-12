package com.edgestackers.opticon.gui.view.parameters;

import com.edgestackers.opticon.gui.datamodel.parameters.ToteStrategyParametersEntry;
import com.edgestackers.opticon.gui.settings.OpticonSceneSettings;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.CheckComboBox;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import static com.edgestackers.opticon.gui.view.util.OpticonGuiResourceUtil.OPTICON_CSS;
import static com.edgestackers.opticon.gui.view.util.OpticonViewUtil.setAnchorsAndSize;

public enum ToteStrategyParametersViewInitializer {
    ;
    private static final String STAGE_TITLE = "Opticon | Tote Strategy Parameters";
    private static final Map<String, String> COLUMN_HEADER_MAPPINGS = Map.ofEntries(
            Map.entry("strategyName", "Strategy Name"),
            Map.entry("toteBetType", "T"),
            Map.entry("jurisdiction", "J"),
            Map.entry("dividendModelDescriptor", "Div Model"),
            Map.entry("theoModelDescriptor", "Theo Model"),
            Map.entry("maxRaceBet", "MRB"),
            Map.entry("rebate", "R"),
            Map.entry("takeout", "T"),
            Map.entry("discount", "D"),
            Map.entry("minBet", "Min Bet"),
            Map.entry("minPool", "Min Pool"),
            Map.entry("maxPool", "Max Pool"),
            Map.entry("maxDividendAgeSeconds", "Max Div Age (s)"),
            Map.entry("maxTheoAgeSeconds", "Max Theo Age (s)")
    );
    private static final Set<String> TWO_DP_COLUMNS = Set.of("maxRaceBet", "rebate", "takeout", "discount", "minBet");

    static void createAndInitializeView(Stage stage,
                                        AnchorPane rootPane,
                                        Scene scene,
                                        OpticonSceneSettings sceneSettings,
                                        CheckComboBox<ToteBetType> betTypeSelector,
                                        TableView<ToteStrategyParametersEntry> tableView,
                                        Button refreshButton,
                                        Button createButton)
    {
        setAnchorsAndSize(betTypeSelector, 25, 150, null, 25, 20, null);
        setAnchorsAndSize(tableView, null, null, 20, 20, 65, 20);
        setAnchorsAndSize(refreshButton, 25, null, null, null, 20, 85);
        setAnchorsAndSize(createButton, 25, 50, null, null, 20, 25);
        rootPane.getChildren().addAll(betTypeSelector, tableView, refreshButton, createButton);

        scene.getStylesheets().add(OPTICON_CSS);

        stage.setX(sceneSettings.stageX());
        stage.setY(sceneSettings.stageY());
        stage.setWidth(sceneSettings.stageWidth());
        stage.setHeight(sceneSettings.stageHeight());

        stage.setScene(scene);
        stage.setTitle(STAGE_TITLE);
        stage.show();
    }

    static void initializeTableView(TableView<ToteStrategyParametersEntry> tableView) {
        for (Field field : ToteStrategyParametersEntry.class.getDeclaredFields()) {
            if (TWO_DP_COLUMNS.contains(field.getName())) {
                String columnName = COLUMN_HEADER_MAPPINGS.get(field.getName());
                TableColumn<ToteStrategyParametersEntry, Double> tableColumn =
                        new TableColumn<>(columnName);
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                tableView.getColumns().add(tableColumn);
                tableColumn.setCellFactory(twoDpFormatterCallback());
            } else if (COLUMN_HEADER_MAPPINGS.containsKey(field.getName())) {
                String columnName = COLUMN_HEADER_MAPPINGS.get(field.getName());
                TableColumn<ToteStrategyParametersEntry, ?> tableColumn = new TableColumn<>(columnName);
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                tableView.getColumns().add(tableColumn);
            }
        }
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);
    }


    public static <T> Callback<TableColumn<T, Double>, TableCell<T, Double>> twoDpFormatterCallback() {
        return column -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item));
                }
            }
        };
    }
}
