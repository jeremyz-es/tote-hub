package com.edgestackers.pgi.services.event.datamodel.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ScratchedRunner", namespace = "http://gws.amtote.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class NotifyRunnerScratchedEventMessage {

    @XmlElement(name = "ToteID", namespace = "http://gws.amtote.com/")
    private String toteId;

    @XmlElement(name = "programName", namespace = "http://gws.amtote.com/")
    private String programName;

    @XmlElement(name = "race", namespace = "http://gws.amtote.com/")
    private int race;

    @XmlElement(name = "runner", namespace = "http://gws.amtote.com/")
    private int runner;

    public String getToteId() {
        return toteId;
    }

    public void setToteId(String toteId) {
        this.toteId = toteId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public int getRunner() {
        return runner;
    }

    public void setRunner(int runner) {
        this.runner = runner;
    }
}
