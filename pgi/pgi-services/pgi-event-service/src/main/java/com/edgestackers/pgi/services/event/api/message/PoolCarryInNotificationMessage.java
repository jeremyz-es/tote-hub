package com.edgestackers.pgi.services.event.api.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notifyPoolCarryIn", namespace = "http://gws.amtote.com/")
public record PoolCarryInNotificationMessage(@XmlElement(name = "ToteID") String toteID,
                                             @XmlElement(name = "programName") String programName,
                                             @XmlElement(name = "race") int race,
                                             @XmlElement(name = "carryIn") String carryIn) implements PgiNotificationMessage {}