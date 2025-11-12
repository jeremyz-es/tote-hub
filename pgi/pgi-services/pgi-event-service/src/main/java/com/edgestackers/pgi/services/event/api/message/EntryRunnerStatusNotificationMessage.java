package com.edgestackers.pgi.services.event.api.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notifyEntryRunnerStatus", namespace = "http://gws.amtote.com/")
public record EntryRunnerStatusNotificationMessage(@XmlElement(name = "ToteID") String toteID,
                                                   @XmlElement(name = "programName") String programName,
                                                   @XmlElement(name = "race") int race,
                                                   @XmlElement(name = "runner") String runner,
                                                   @XmlElement(name = "status") String status) implements PgiNotificationMessage {}
