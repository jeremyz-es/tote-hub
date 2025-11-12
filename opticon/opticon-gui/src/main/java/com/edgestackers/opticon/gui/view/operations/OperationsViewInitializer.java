package com.edgestackers.opticon.gui.view.operations;

import com.edgestackers.opticon.gui.datamodel.operations.OpticonStrategyContextSummaryEntry;
import com.edgestackers.opticon.gui.settings.OpticonSceneSettings;
import com.edgestackers.tote.hub.core.datamodel.context.NitroContextStatus;
import com.edgestackers.tote.hub.core.datamodel.context.OrderGatewayExecutionStatus;
import com.edgestackers.tote.hub.core.datamodel.context.ToteMarketCycleStatus;
import com.edgestackers.tote.hub.core.datamodel.context.TurboPricingStatus;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteRaceEvent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.CheckComboBox;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import static com.edgestackers.opticon.gui.view.util.OpticonGuiResourceUtil.OPTICON_CSS;
import static com.edgestackers.opticon.gui.view.util.OpticonViewUtil.setAnchorsAndSize;

public enum OperationsViewInitializer {
    ;
    private static final String STAGE_TITLE = "Opticon | Operations View";
    private static final Map<String, String> COLUMN_HEADER_MAPPINGS = Map.ofEntries(
            Map.entry("strategyName", "Strategy"),
            Map.entry("dividendModel", "Div Model"),
            Map.entry("theoModel", "Theo Model"),
            Map.entry("track", "T"),
            Map.entry("race", "R#"),
            Map.entry("jurisdiction", "J"),
            Map.entry("betType", "B"),
            Map.entry("qldTime", "Received @ (QLD)"),
            Map.entry("cyclesStatus", "Cycles"),
            Map.entry("turboDividendStatus", "Div"),
            Map.entry("turboTheoStatus", "Theo"),
            Map.entry("nitroStatus", "Nitro"),
            Map.entry("executionStatus", "Exec"),
            Map.entry("winningSelection", "Winner"),
            Map.entry("payout", "Payout"),
            Map.entry("turnover", "Turnover"),
            Map.entry("profit", "Profit")
    );
    private static final Set<String> TWO_DP_COLUMNS = Set.of("payout", "turnover", "profit");

    static void createAndInitializeView(Stage stage,
                                        AnchorPane rootPane,
                                        Scene scene,
                                        OpticonSceneSettings sceneSettings,
                                        CheckComboBox<ToteBetType> betTypeSelector,
                                        CheckComboBox<TotePoolJurisdiction> jurisdictionSelector,
                                        TableView<OpticonStrategyContextSummaryEntry> tableView,
                                        CheckBox linkRaceFilter)
    {
        setAnchorsAndSize(betTypeSelector, 25, 150, null, 25, 20, null);
        setAnchorsAndSize(jurisdictionSelector, 25, 100, null, 185, 20, null);
        setAnchorsAndSize(tableView, null, null, 20, 20, 65, 20);
        setAnchorsAndSize(linkRaceFilter, 25, null, null, null, 20, 25);
        rootPane.getChildren().addAll(betTypeSelector, jurisdictionSelector, tableView, linkRaceFilter);
        linkRaceFilter.setTextFill(Color.WHITE);
        scene.getStylesheets().add(OPTICON_CSS);

        stage.setX(sceneSettings.stageX());
        stage.setY(sceneSettings.stageY());
        stage.setWidth(sceneSettings.stageWidth());
        stage.setHeight(sceneSettings.stageHeight());

        stage.setScene(scene);
        stage.setTitle(STAGE_TITLE);
        stage.show();
    }

    static void initializeTableView(TableView<OpticonStrategyContextSummaryEntry> tableView) {
        for (Field field : OpticonStrategyContextSummaryEntry.class.getDeclaredFields()) {

            switch (field.getName()) {
                case "raceStatus" -> tableView.getColumns().add(enumStatusColumn("Race Status", ToteRaceEvent.class, "raceStatus"));
                case "betType" -> tableView.getColumns().add(enumStatusColumn("Bet Type", ToteBetType.class, "betType"));
                case "jurisdiction" -> tableView.getColumns().add(enumStatusColumn("Jurisdiction", TotePoolJurisdiction.class, "jurisdiction"));
                case "cyclesStatus" -> tableView.getColumns().add(enumStatusColumn("Cycles", ToteMarketCycleStatus.class, "cyclesStatus"));
                case "turboDividendStatus" -> tableView.getColumns().add(enumStatusColumn("Dividend", TurboPricingStatus.class, "turboDividendStatus"));
                case "turboTheoStatus" -> tableView.getColumns().add(enumStatusColumn("Theo", TurboPricingStatus.class, "turboTheoStatus"));
                case "nitroStatus" -> tableView.getColumns().add(enumStatusColumn("Nitro", NitroContextStatus.class, "nitroStatus"));
                case "executionStatus" -> tableView.getColumns().add(enumStatusColumn("Execution", OrderGatewayExecutionStatus.class, "executionStatus"));
                default -> {
                    if (TWO_DP_COLUMNS.contains(field.getName())) {
                        String columnName = COLUMN_HEADER_MAPPINGS.get(field.getName());
                        TableColumn<OpticonStrategyContextSummaryEntry, Double> tableColumn =
                                new TableColumn<>(columnName);
                        tableColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                        tableView.getColumns().add(tableColumn);
                        tableColumn.setCellFactory(twoDpFormatterCallback());
                    }
                    else if (COLUMN_HEADER_MAPPINGS.containsKey(field.getName())) {
                        String columnName = COLUMN_HEADER_MAPPINGS.get(field.getName());
                        TableColumn<OpticonStrategyContextSummaryEntry, String> tableColumn =
                                new TableColumn<>(columnName);
                        tableColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                        tableView.getColumns().add(tableColumn);
                    }
                }
            }
        }
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);
    }

    private static <T extends Enum<T>> TableColumn<OpticonStrategyContextSummaryEntry, T> enumStatusColumn(
            String header, Class<T> enumClass, String propertyName) {

        TableColumn<OpticonStrategyContextSummaryEntry, T> tableColumn = new TableColumn<>(header);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        tableColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.name());
                    try {
                        String color = (String) item.getClass()
                                .getMethod("getRgbIndicator")
                                .invoke(item);
                        setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;");
                    } catch (Exception e) {
                        setStyle("");
                    }
                }
            }
        });
        return tableColumn;
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
