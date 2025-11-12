package com.edgestackers.pgi.services.event.api.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "updateRaceTimes", namespace = "http://gws.amtote.com/")
public record RaceTimeUpdatedNotificationMessage(@XmlElement(name = "toteID") String toteID,
                                                 @XmlElement(name = "programName") String programName,
                                                 @XmlElement(name = "race") int race,
                                                 @XmlElement(name = "MTP") int mtp,
                                                 @XmlElement(name = "postTime") String postTime) {}