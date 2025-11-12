package com.edgestackers.pgi.services.feed.information;

import com.amtote.gws.GpProgramDetail;
import com.edgestackers.pgi.common.datamodel.PgiCountryCode;
import com.edgestackers.pgi.services.feed.datamodel.PgiProgramInformation;
import com.edgestackers.pgi.common.datamodel.PgiRaceType;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public enum GpProgramDetailConverter {
    ;
    private static final Logger LOGGER = LoggerFactory.getLogger(GpProgramDetailConverter.class);
    private static final DateTimeFormatter GP_PROGRAM_DETAIL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter PGI_PROGRAM_METADATA_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static PgiProgramInformation convertToPgiProgramMetadata(GpProgramDetail detail) throws GpProgramDetailConversionException {
        @Nullable PgiRaceType pgiRaceType = PgiRaceType.getApiMapping(detail.getProgramType());
        if (pgiRaceType == null) {
            String errorMessage = String.format("Failed to convert [%s] to [%s] for Name/Long/Type/Date/Country: [%s|%s|%s|%s|%s] as PGI Program Type [%s] could not be mapped.",
                    GpProgramDetail.class.getSimpleName(),
                    PgiProgramInformation.class.getSimpleName(),
                    detail.getProgramName(),
                    detail.getProgramLongName(),
                    detail.getProgramType(),
                    detail.getProgramDate(),
                    detail.getCountryCode(),
                    detail.getProgramType()
            );
            LOGGER.error(errorMessage);
            throw new GpProgramDetailConversionException(errorMessage);
        }
        return new PgiProgramInformation(
                detail.getProgramName(),
                detail.getProgramLongName(),
                LocalDate.parse(detail.getProgramDate(), GP_PROGRAM_DETAIL_DATE_FORMATTER).format(PGI_PROGRAM_METADATA_DATE_FORMATTER),
                pgiRaceType,
                PgiCountryCode.valueOf(detail.getCountryCode())
        );
    }
}
