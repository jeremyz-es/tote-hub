package com.edgestackers.opticon.gui.settings;

import com.edgestackers.opticon.gui.view.OpticonViewType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class OpticonGuiSettings {
    private static final OpticonSceneSettings DEFAULT = new OpticonSceneSettings(0, 0, 800, 600);
    private final Map<OpticonViewType, OpticonSceneSettings> sceneSettings;

    public OpticonGuiSettings(@JsonProperty("scene_settings") Map<OpticonViewType, OpticonSceneSettings> sceneSettings) {
        this.sceneSettings = sceneSettings;
    }

    public void put(OpticonViewType opticonViewType, OpticonSceneSettings opticonSceneSettings) {
        sceneSettings.put(opticonViewType, opticonSceneSettings);
    }

    public Map<OpticonViewType, OpticonSceneSettings> getSceneSettings() { return sceneSettings; }

    public OpticonSceneSettings get(OpticonViewType opticonViewType) {
        return sceneSettings.get(opticonViewType) == null ? DEFAULT : sceneSettings.get(opticonViewType);
    }
}
