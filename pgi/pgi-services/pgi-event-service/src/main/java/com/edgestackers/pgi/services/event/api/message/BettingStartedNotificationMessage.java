package com.edgestackers.pgi.services.event.api.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notifyStartBetting", namespace = "http://gws.amtote.com/")
public record BettingStartedNotificationMessage(@XmlElement(name = "ToteID") String toteID,
                                                @XmlElement(name = "programName") String programName,
                                                @XmlElement(name = "race") int race,
                                                @XmlElement(name = "time") String time) implements PgiNotificationMessage {}
