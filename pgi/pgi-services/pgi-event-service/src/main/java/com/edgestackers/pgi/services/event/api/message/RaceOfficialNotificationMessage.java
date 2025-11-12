package com.edgestackers.pgi.services.event.api.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notifyRaceOfficial", namespace = "http://gws.amtote.com/")
public record RaceOfficialNotificationMessage(@XmlElement(name = "ToteID") String toteID,
                                              @XmlElement(name = "programName") String programName,
                                              @XmlElement(name = "race") int race) implements PgiNotificationMessage {}
