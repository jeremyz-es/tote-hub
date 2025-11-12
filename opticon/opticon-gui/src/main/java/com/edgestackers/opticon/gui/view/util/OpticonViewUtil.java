package com.edgestackers.opticon.gui.view.util;

import jakarta.annotation.Nullable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public enum OpticonViewUtil {
    ;

    public static void setAnchorsAndSize(Region region, @Nullable Integer height, @Nullable Integer width, @Nullable Integer top, @Nullable Integer left, @Nullable Integer bottom, @Nullable Integer right) {
        if (height != null) region.setPrefHeight(height);
        if (width != null) region.setPrefWidth(width);
        if (top != null) AnchorPane.setTopAnchor(region, Double.valueOf(top));
        if (left != null) AnchorPane.setLeftAnchor(region, Double.valueOf(left));
        if (bottom != null) AnchorPane.setBottomAnchor(region, Double.valueOf(bottom));
        if (right != null) AnchorPane.setRightAnchor(region, Double.valueOf(right));
    }

    public static void setAnchorsAndSize(Region region, @Nullable Double height, @Nullable Double width, @Nullable Double top, @Nullable Double left, @Nullable Double bottom, @Nullable Double right) {
        if (height != null) region.setPrefHeight(height);
        if (width != null) region.setPrefWidth(width);
        if (top != null) AnchorPane.setTopAnchor(region, top);
        if (left != null) AnchorPane.setLeftAnchor(region, left);
        if (bottom != null) AnchorPane.setBottomAnchor(region, bottom);
        if (right != null) AnchorPane.setRightAnchor(region, right);
    }
}
