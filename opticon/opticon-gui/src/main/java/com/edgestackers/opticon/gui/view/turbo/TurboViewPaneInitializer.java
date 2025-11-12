package com.edgestackers.opticon.gui.view.turbo;

import com.edgestackers.opticon.gui.datamodel.turbo.OpticonDividendPredictionUpdateEntry;
import com.edgestackers.opticon.gui.datamodel.turbo.OpticonTheoUpdateEntry;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.Field;
import java.util.Map;

import static com.edgestackers.opticon.gui.view.util.OpticonViewUtil.setAnchorsAndSize;

public enum TurboViewPaneInitializer {
    ;
    private static final Map<String, String> DIVIDEND_TABLE_VIEW_COLUMN_HEADER_NAMES = Map.ofEntries(
            Map.entry("generatedAtQldTime", "Gen @ (QLD)"),
            Map.entry("marketPoolTotal", "Mkt Pool"),
            Map.entry("predictedPoolTotal", "Pred Pool"),
            Map.entry("lastDividendUpdateDeltaSeconds", "Last Div Δs"),
            Map.entry("calculationTimeMillis", "Calc Time (ms)")
    );
    private static final Map<String, String> THEO_TABLE_VIEW_COLUMN_HEADER_NAMES = Map.ofEntries(
            Map.entry("generatedAtQldTime", "Gen @ (QLD)"),
            Map.entry("lastTheoUpdateDeltaSeconds", "Last Theo Δs"),
            Map.entry("calculationTimeMillis", "Calc Time (ms)")
    );

    static void createAndInitializeView(AnchorPane rootPane,
                                        TableView<OpticonDividendPredictionUpdateEntry> dividendTableView,
                                        TableView<OpticonTheoUpdateEntry> theoTableView)
    {
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.getItems().addAll(dividendTableView, theoTableView);
        setAnchorsAndSize(splitPane, null, null, 20, 20, 20, 20);
        rootPane.getChildren().add(splitPane);
    }

    static void initializeDividendTableView(TableView<OpticonDividendPredictionUpdateEntry> dividendTableView) {
        for (Field field : OpticonDividendPredictionUpdateEntry.class.getDeclaredFields()) {
            if (DIVIDEND_TABLE_VIEW_COLUMN_HEADER_NAMES.containsKey(field.getName())) {
                String columnName = DIVIDEND_TABLE_VIEW_COLUMN_HEADER_NAMES.get(field.getName());
                TableColumn<OpticonDividendPredictionUpdateEntry, String> tableColumn = new TableColumn<>(columnName);
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                dividendTableView.getColumns().add(tableColumn);
            }
        }
        dividendTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);
    }

    static void initializeTheoTableView(TableView<OpticonTheoUpdateEntry> theoTableView) {
        for (Field field : OpticonTheoUpdateEntry.class.getDeclaredFields()) {
            if (THEO_TABLE_VIEW_COLUMN_HEADER_NAMES.containsKey(field.getName())) {
                String columnName = THEO_TABLE_VIEW_COLUMN_HEADER_NAMES.get(field.getName());
                TableColumn<OpticonTheoUpdateEntry, String> tableColumn = new TableColumn<>(columnName);
                tableColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                theoTableView.getColumns().add(tableColumn);
            }
        }
        theoTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);
    }
}
