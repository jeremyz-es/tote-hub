package com.edgestackers.opticon.gui.view.race;

import com.edgestackers.opticon.common.datamodel.OpticonRunSummary;
import com.edgestackers.opticon.common.datamodel.OpticonWinPoolUpdate;
import com.edgestackers.opticon.gui.control.listener.OpticonRaceSwitchListener;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchKey;
import com.edgestackers.opticon.gui.datamodel.OpticonRaceSwitchType;
import com.edgestackers.opticon.gui.datamodel.filter.RaceViewFilter;
import com.edgestackers.opticon.gui.datamodel.run.OpticonRunSummaryEntry;
import com.edgestackers.tote.hub.core.datamodel.core.RaceType;
import com.edgestackers.tote.hub.core.metadata.EsRaceMetadata;
import jakarta.annotation.Nullable;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RaceViewPaneController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RaceViewPaneController.class);
    private final RaceViewPane view;
    private final RaceViewPaneModel viewModel;
    private final Set<OpticonRaceSwitchListener> opticonRaceSwitchListeners = new HashSet<>();
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public RaceViewPaneController(RaceViewPane view,
                                  RaceViewPaneModel viewModel)
    {
        this.view = view;
        this.viewModel = viewModel;
    }

    public void initialize() {
        initializeBindings();
        initializeListeners();
        view.initialize();
        viewModel.initialize();
        executorService.scheduleWithFixedDelay(() -> { try { viewModel.updateTimeUntilRace(); } catch (Exception ignored) {}} , 1L, 1L, TimeUnit.SECONDS);
        LOGGER.info("[{}] has initialized!", getClass().getSimpleName());
    }

    public void registerRaceSwitchListener(OpticonRaceSwitchListener raceSwitchListener) {
        opticonRaceSwitchListeners.add(raceSwitchListener);
    }

    public void handle(OpticonRunSummary opticonRunSummary) {
        viewModel.handle(opticonRunSummary);
    }

    public void handle(OpticonWinPoolUpdate winPoolUpdate) {
        viewModel.handle(winPoolUpdate);
    }

    public void handle(List<EsRaceMetadata> esRaceMetadata) {
        esRaceMetadata.forEach(viewModel::handle);
    }

    private void initializeBindings() {
        view.getTableView().setItems(viewModel.getSortedEntries());
        view.getRaceTypeSelector().setItems(viewModel.getSelectableRaceTypes());
        view.getTrackSelector().setItems(viewModel.getSelectableTracks());
        view.getRaceSelector().setItems(viewModel.getSelectableRaces());
        view.getTimeUntilRaceLabel().textProperty().bind(viewModel.timeUntilRaceDescriptionProperty());
        viewModel.getSortedEntries().setComparator(Comparator.comparingInt(OpticonRunSummaryEntry::getTab));
        view.getNswWinPoolColumn().textProperty().bind(viewModel.nswWinPoolHeaderDescProperty());
        view.getVicWinPoolColumn().textProperty().bind(viewModel.vicWinPoolHeaderDescProperty());
        view.getQldWinPoolColumn().textProperty().bind(viewModel.qldWinPoolHeaderDescProperty());
    }

    private void initializeListeners() {
        view.getRaceTypeSelector().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateFilterPredicate());
        view.getTrackSelector().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateFilterPredicate());
        view.getRaceSelector().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateFilterPredicate());
        view.getRaceTypeSelector().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateSelectableTracks());
        view.getTrackSelector().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateSelectableRaces());
        view.getPreviousRaceButton().setOnAction(event -> executeRaceSwitch(OpticonRaceSwitchType.PREVIOUS));
        view.getNextRaceButton().setOnAction(event -> executeRaceSwitch(OpticonRaceSwitchType.NEXT));
        view.getPlayRaceButton().setOnAction(event -> executeRaceSwitch(OpticonRaceSwitchType.PLAY));
        viewModel.selectedRaceProperty().addListener((obs, oldVal, newVal) -> opticonRaceSwitchListeners.forEach(listener -> listener.on(newVal)));
    }

    private void updateFilterPredicate() {
        @Nullable RaceType selectedRaceType = view.getRaceTypeSelector().getSelectionModel().getSelectedItem();
        @Nullable String selectedTrack = view.getTrackSelector().getSelectionModel().getSelectedItem();
        @Nullable Integer selectedRace = view.getRaceSelector().getSelectionModel().getSelectedItem();
        if (selectedRaceType == null || selectedTrack == null || selectedRace == null) {
            viewModel.clearFilter();
            return;
        }
        RaceViewFilter viewFilter = new RaceViewFilter(selectedRaceType, selectedTrack, selectedRace);
        viewModel.switchToRace(viewFilter);
    }

    private void updateSelectableTracks() {
        @Nullable RaceType selectedRaceType = view.getRaceTypeSelector().getSelectionModel().getSelectedItem();
        if (selectedRaceType == null) return;
        viewModel.setTracksFilter(selectedRaceType);
        updateSelectableRaces();
    }

    private void updateSelectableRaces() {
        @Nullable RaceType raceType = view.getRaceTypeSelector().getSelectionModel().getSelectedItem();
        @Nullable String selectedTrack = view.getTrackSelector().getSelectionModel().getSelectedItem();
        if (raceType == null || selectedTrack == null) return;
        viewModel.setRacesFilter(raceType, selectedTrack);
    }

    private void executeRaceSwitch(OpticonRaceSwitchType switchType) {
        @Nullable OpticonRaceSwitchKey newRace = viewModel.executeRaceSwitch(switchType);
        if (newRace == null) return;
        // TODO: Fix below - shouldn't fire off view changes
        updateSequentially(
                () -> view.getRaceTypeSelector().setValue(newRace.raceType()),
                () -> view.getTrackSelector().setValue(newRace.track()),
                () -> view.getRaceSelector().setValue(newRace.race())
        );
    }

    private void updateSequentially(Runnable... uiUpdates) {
        if (uiUpdates.length == 0) return;
        Runnable firstUpdate = uiUpdates[0];
        Runnable[] remainingUpdates = new Runnable[uiUpdates.length - 1];
        System.arraycopy(uiUpdates, 1, remainingUpdates, 0, uiUpdates.length - 1);
        Platform.runLater(() -> { firstUpdate.run(); updateSequentially(remainingUpdates); });
    }
}
