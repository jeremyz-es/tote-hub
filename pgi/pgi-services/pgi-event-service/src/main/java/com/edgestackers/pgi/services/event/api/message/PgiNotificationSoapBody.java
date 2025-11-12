package com.edgestackers.pgi.services.event.api.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

public class PgiNotificationSoapBody {

    @XmlElements({
            @XmlElement(name = "notifyStartBetting", type = BettingStartedNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "notifyCycleData", type = CycleDataUpdatedNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "notifyEntryRunnerStatus", type = EntryRunnerStatusNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "notifyPoolAdvisory", type = PoolAdvisoryNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "notifyPoolCarryIn", type = PoolCarryInNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "notifyPoolHolder", type = PoolHolderNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "notifyProvisionalResults", type = ProvisionalResultsNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "notifyRaceOfficial", type = RaceOfficialNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "updateRaceTimes", type = RaceTimeUpdatedNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "notifyRunnerChange", type = RunnerChangedNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "notifyScratchedRunner", type = RunnerScratchedNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "notifyRunnerStatus", type = RunnerStatusNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "notifyScanInfo", type = ScanInfoNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "notifyTopN", type = TopNNotificationMessage.class, namespace = "http://gws.amtote.com/"),
            @XmlElement(name = "notifyWillPays", type = WillPaysNotificationMessage.class, namespace = "http://gws.amtote.com/"),

    })
    public PgiNotificationMessage message;
}
