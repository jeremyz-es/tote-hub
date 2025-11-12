package com.edgestackers.opticon.gui.settings;

import com.edgestackers.opticon.gui.view.OpticonViewType;
import com.edgestackers.opticon.gui.view.util.OpticonGuiDebouncer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public enum OpticonGuiSettingsManager {
    ;
    private static final Logger LOGGER = LoggerFactory.getLogger(OpticonGuiSettingsManager.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final OpticonGuiDebouncer DEBOUNCER = new OpticonGuiDebouncer();
    private static final File SETTINGS_FILE = new File("opticon-gui-settings.json");
    private static final OpticonGuiSettings OPTICON_GUI_SETTINGS = loadOpticonGuiSettings();

    public static synchronized void saveOpticonGuiSettings(OpticonViewType opticonViewType, Stage stage) {
        DEBOUNCER.debounce(() -> {
            double stageX = stage.xProperty().get();
            double stageY = stage.yProperty().get();
            double stageWidth = stage.widthProperty().get();
            double stageHeight = stage.heightProperty().get();
            OpticonSceneSettings opticonSceneSettings = new OpticonSceneSettings(stageX, stageY, stageWidth, stageHeight);
            OPTICON_GUI_SETTINGS.put(opticonViewType, opticonSceneSettings);
            try {
                OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValue(SETTINGS_FILE, OPTICON_GUI_SETTINGS);
                LOGGER.info("Opticon GUI Settings successfully saved to file.");
            }
            catch (IOException e) {
                LOGGER.error("Failed to save Opticon GUI Settings due to ERROR: {}", e.getMessage());
            }
        });
    }

    public static synchronized OpticonSceneSettings getSettingsFor(OpticonViewType opticonViewType) {
        return OPTICON_GUI_SETTINGS.get(opticonViewType);
    }

    private static synchronized OpticonGuiSettings loadOpticonGuiSettings() {
        try {
            return OBJECT_MAPPER.readValue(SETTINGS_FILE, new TypeReference<>() {});
        }
        catch (IOException e) {
            LOGGER.error("Failed to load Opticon GUI Settings due to ERROR: {}... using default settings.", e.getMessage());
            return new OpticonGuiSettings(new HashMap<>());
        }
    }
}
