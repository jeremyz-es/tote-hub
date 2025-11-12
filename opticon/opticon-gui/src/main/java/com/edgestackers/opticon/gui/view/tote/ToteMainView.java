package com.edgestackers.opticon.gui.view.tote;

import com.edgestackers.opticon.gui.datamodel.filter.ToteJurisdictionKey;
import com.edgestackers.opticon.gui.settings.OpticonSceneSettings;
import com.edgestackers.opticon.gui.view.race.RaceViewPane;
import com.edgestackers.opticon.gui.view.util.AccountBalanceTile;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import eu.hansolo.tilesfx.Tile;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.edgestackers.opticon.gui.view.tote.ToteMainViewInitializer.createAndInitializeView;
import static com.edgestackers.opticon.gui.view.util.ViewTileFactory.createLedTile;

public class ToteMainView {
    private final Stage stage = new Stage();
    private final AnchorPane rootPane = new AnchorPane();
    private final Scene scene = new Scene(rootPane);
    private final Map<ToteJurisdictionKey, AccountBalanceTile> balanceTiles = new LinkedHashMap<>();
    private final AccountBalanceTile nswTile = new AccountBalanceTile(TotePoolJurisdiction.NSW.name());
    private final AccountBalanceTile qldTile = new AccountBalanceTile(TotePoolJurisdiction.QLD.name());
    private final AccountBalanceTile vicTile = new AccountBalanceTile(TotePoolJurisdiction.VIC.name());
    private final Tile approximatesTile = createLedTile("Approximates");
    private final Tile pricingTile = createLedTile("Pricing");
    private final Tile triggerTile = createLedTile("Trigger");
    private final RaceViewPane raceViewPane;

    public ToteMainView(RaceViewPane raceViewPane) {
        this.raceViewPane = raceViewPane;
    }

    public void initialize(OpticonSceneSettings sceneSettings) {
        initializeTiles();
        createView(sceneSettings);
    }

    public Map<ToteJurisdictionKey, AccountBalanceTile> getBalanceTiles() { return balanceTiles; }

    public Tile getApproximatesTile() { return approximatesTile; }

    public Tile getPricingTile() { return pricingTile; }

    public Tile getTriggerTile() { return triggerTile; }

    public Stage getStage() { return stage; }

    private void createView(OpticonSceneSettings sceneSettings) {
        createAndInitializeView(stage, rootPane, scene, sceneSettings, raceViewPane, nswTile, qldTile, vicTile);
    }

    private void initializeTiles() {
        balanceTiles.put(new ToteJurisdictionKey(ToteProvider.PGI, TotePoolJurisdiction.NSW), nswTile);
        balanceTiles.put(new ToteJurisdictionKey(ToteProvider.PGI, TotePoolJurisdiction.QLD), qldTile);
        balanceTiles.put(new ToteJurisdictionKey(ToteProvider.PGI, TotePoolJurisdiction.VIC), vicTile);
    }
}
