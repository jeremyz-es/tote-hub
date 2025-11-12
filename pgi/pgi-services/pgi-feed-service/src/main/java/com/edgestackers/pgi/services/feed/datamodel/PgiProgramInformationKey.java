package com.edgestackers.pgi.services.feed.datamodel;

import java.util.Objects;

public record PgiProgramInformationKey(String programName, String programDate) {

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PgiProgramInformationKey that = (PgiProgramInformationKey) object;
        return Objects.equals(programName, that.programName) && Objects.equals(programDate, that.programDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programName, programDate);
    }
}
