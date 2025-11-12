package com.edgestackers.pgi.services.event.api.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notifyRunnerChange", namespace = "http://gws.amtote.com/")
public record RunnerChangedNotificationMessage(@XmlElement(name = "ToteID") String toteID,
                                               @XmlElement(name = "programName") String programName) implements PgiNotificationMessage {}