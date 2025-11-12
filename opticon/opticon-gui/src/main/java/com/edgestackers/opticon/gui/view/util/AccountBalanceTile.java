package com.edgestackers.opticon.gui.view.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AccountBalanceTile extends StackPane {
    private final Label titleLabel;
    private final Label masterBalanceLabel;
    private final Label rebateBalanceLabel;
    private final Label totalBalanceLabel;

    public AccountBalanceTile(String titleText) {
        this.titleLabel = new Label(titleText);
        this.masterBalanceLabel = new Label();
        this.rebateBalanceLabel = new Label();
        this.totalBalanceLabel = new Label();
        initialize();
    }

    public Label getMasterBalanceLabel() { return masterBalanceLabel; }
    public Label getRebateBalanceLabel() { return rebateBalanceLabel; }
    public Label getTotalBalanceLabel() { return totalBalanceLabel; }

    private void initialize() {
        setPrefSize(200, 120);
        setStyle("-fx-background-color: #2b2b2b; -fx-background-radius: 10;");

        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16;");
        StackPane.setAlignment(titleLabel, Pos.TOP_LEFT);
        StackPane.setMargin(titleLabel, new Insets(10, 0, 0, 10));

        totalBalanceLabel.setPrefSize(100, 50);
        totalBalanceLabel.setMinSize(100, 50);
        totalBalanceLabel.setMaxSize(100, 50);
        totalBalanceLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        totalBalanceLabel.setStyle("-fx-text-fill: green;");
        totalBalanceLabel.setAlignment(Pos.CENTER);
        StackPane.setAlignment(totalBalanceLabel, Pos.CENTER);

        masterBalanceLabel.setFont(Font.font("System", 10));
        masterBalanceLabel.setStyle("-fx-text-fill: green;");
        StackPane.setAlignment(masterBalanceLabel, Pos.BOTTOM_CENTER);
        StackPane.setMargin(masterBalanceLabel, new Insets(0, 0, 5, 0));

        getChildren().addAll(titleLabel, totalBalanceLabel, masterBalanceLabel);
    }

}
