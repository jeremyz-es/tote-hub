package com.edgestackers.opticon.gui.view.util;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.scene.paint.Color;

public enum ViewTileFactory {
    ;

    public static Tile createLedTile(String component) {
        Tile tile = TileBuilder.create()
                .skinType(Tile.SkinType.LED)
                .activeColor(Color.LIMEGREEN)
                .description(component)
                .build();
        tile.setMaxWidth(Double.MAX_VALUE);
        return tile;
    }
}
