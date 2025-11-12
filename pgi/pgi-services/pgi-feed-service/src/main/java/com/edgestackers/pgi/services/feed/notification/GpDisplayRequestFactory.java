package com.edgestackers.pgi.services.feed.notification;

import com.amtote.gws.GpDisplayRequest;
import com.edgestackers.pgi.services.common.datamodel.RaceOfficialNotificationMessage;
import com.edgestackers.pgi.services.feed.datamodel.PgiRaceInformation;
import com.edgestackers.pgi.services.common.datamodel.CycleDataUpdatedNotificationMessage;

public enum GpDisplayRequestFactory {
    ;

    static GpDisplayRequest createDisplayRequestFor(CycleDataUpdatedNotificationMessage notificationMessage,
                                                    PgiRaceInformation raceMetadata) {
        GpDisplayRequest displayRequest = new GpDisplayRequest();
        displayRequest.setProgramName(notificationMessage.programName());
        displayRequest.setRace(notificationMessage.race());
        displayRequest.setDate(raceMetadata.programDateYmd());
        // TODO: Set bet type
        return displayRequest;
    }

    static GpDisplayRequest createDisplayRequestFor(RaceOfficialNotificationMessage raceOfficialNotificationMessage,
                                                    PgiRaceInformation raceInformation)
    {
        GpDisplayRequest displayRequest = new GpDisplayRequest();
        displayRequest.setProgramName(raceOfficialNotificationMessage.programName());
        displayRequest.setRace(raceOfficialNotificationMessage.race());
        displayRequest.setDate(raceInformation.programDateYmd());
        return displayRequest;
    }

    static GpDisplayRequest createDisplayRequestFor(String programName, int race, String date) {
        GpDisplayRequest displayRequest = new GpDisplayRequest();
        displayRequest.setProgramName(programName);
        displayRequest.setRace(race);
        displayRequest.setDate(date);
        return displayRequest;
    }
}
