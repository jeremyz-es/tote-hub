package com.edgestackers.opticon.gui;

import com.edgestackers.opticon.gui.component.CaesarModule;
import com.edgestackers.opticon.gui.component.DaggerOpticonGuiComponent;
import com.edgestackers.opticon.gui.component.OpticonGuiComponent;
import com.edgestackers.opticon.gui.component.OpticonServiceModule;
import com.edgestackers.opticon.gui.view.OpticonGuiController;
import com.edgestackers.tote.hub.core.parameters.CaesarApiProvider;

import java.util.Map;

public class OpticonGuiInitializer {
    private static final Map<String, String> ENV_VARS = System.getenv();
    private static final String BASE_REST_ENDPOINT = ENV_VARS.get("OPTICON_SERVICE_BASE_REST_ENDPOINT");
    private static final String BASE_WS_ENDPOINT = ENV_VARS.get("OPTICON_SERVICE_BASE_WS_ENDPOINT");
    private static final String CAESAR_BASE_ENDPOINT = ENV_VARS.get("CAESAR_BRIDGE_BASE_REST_ENDPOINT");
    private static final CaesarApiProvider CAESAR_API_PROVIDER = new CaesarApiProvider(CAESAR_BASE_ENDPOINT);

    public void initialize() {
        OpticonGuiComponent opticonGuiComponent = DaggerOpticonGuiComponent.builder()
                .caesarModule(new CaesarModule(CAESAR_API_PROVIDER))
                .opticonServiceModule(new OpticonServiceModule(BASE_WS_ENDPOINT, BASE_REST_ENDPOINT))
                .build();
        OpticonGuiController opticonGuiController = opticonGuiComponent.opticonGuiController();
        opticonGuiController.initialize();
    }
}
