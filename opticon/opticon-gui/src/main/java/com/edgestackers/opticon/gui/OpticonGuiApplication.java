package com.edgestackers.opticon.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class OpticonGuiApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        OpticonGuiInitializer opticonGuiInitializer = new OpticonGuiInitializer();
        opticonGuiInitializer.initialize();
    }

    public static void main(String[] args) {
        launch();
    }
}
