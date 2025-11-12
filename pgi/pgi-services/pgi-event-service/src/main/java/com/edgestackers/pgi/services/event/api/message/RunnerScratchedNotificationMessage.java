package com.edgestackers.pgi.services.event.api.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notifyScratchedRunner", namespace = "http://gws.amtote.com/")
public record RunnerScratchedNotificationMessage(@XmlElement(name = "ToteID") String toteID,
                                                 @XmlElement(name = "programName") String programName,
                                                 @XmlElement(name = "race") int race,
                                                 @XmlElement(name = "runner") int runner) implements PgiNotificationMessage {}