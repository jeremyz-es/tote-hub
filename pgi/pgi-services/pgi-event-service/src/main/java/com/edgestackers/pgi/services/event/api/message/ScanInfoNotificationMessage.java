package com.edgestackers.pgi.services.event.api.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notifyScanInfo", namespace = "http://gws.amtote.com/")
public record ScanInfoNotificationMessage(@XmlElement(name = "ToteID") String toteID,
                                          @XmlElement(name = "programName") String programName,
                                          @XmlElement(name = "race") int race,
                                          @XmlElement(name = "leg") int leg,
                                          @XmlElement(name = "betType") String betType) implements PgiNotificationMessage {}
