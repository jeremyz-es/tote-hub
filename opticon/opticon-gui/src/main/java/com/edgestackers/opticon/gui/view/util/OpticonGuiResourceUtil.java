package com.edgestackers.opticon.gui.view.util;

import com.edgestackers.opticon.gui.OpticonGuiApplication;

import java.util.Objects;

public enum OpticonGuiResourceUtil {
    ;
    public static final String OPTICON_CSS = resourceUrl("main.css");

    private static String resourceUrl(String resourceFile) {
        return Objects.requireNonNull(OpticonGuiApplication.class.getResource(resourceFile)).toExternalForm();
    }
}
