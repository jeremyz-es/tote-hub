package com.edgestackers.pgi.services.feed.notification;

import com.amtote.gws.*;
import com.edgestackers.pgi.common.client.GwsClientInfoProvider;
import com.edgestackers.pgi.common.datamodel.PgiBetType;
import com.edgestackers.pgi.common.datamodel.metadata.PgiRaceMetadata;
import com.edgestackers.pgi.services.common.datamodel.CycleDataUpdatedNotificationMessage;
import com.edgestackers.pgi.services.common.datamodel.PgiTotePoolId;
import com.edgestackers.pgi.services.common.datamodel.RaceOfficialNotificationMessage;
import com.edgestackers.pgi.services.feed.datamodel.PgiRaceInformationKey;
import com.edgestackers.pgi.services.feed.metadata.PgiRaceMetadataCache;
import com.edgestackers.pgi.services.feed.publisher.PgiMessageNatsPublisher;
import com.edgestackers.tote.hub.core.datamodel.core.ToteBetType;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.official.ToteRaceOfficial;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static com.edgestackers.pgi.common.util.PgiBetTypeConverter.convertToToteBetType;
import static com.edgestackers.pgi.services.feed.notification.GpDisplayRequestFactory.createDisplayRequestFor;

public class RaceOfficialNotificationMessageProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RaceOfficialNotificationMessageProcessor.class);
    private final GwsSoap gwsSoap;
    private final GwsClientInfoProvider gwsClientInfoProvider;
    private final PgiTotePoolId pgiTotePoolId;
    private final PgiRaceMetadataCache pgiRaceMetadataCache;
    private final PgiMessageNatsPublisher natsPublisher;
    private final TotePoolJurisdiction totePoolJurisdiction;

    public RaceOfficialNotificationMessageProcessor(GwsSoap gwsSoap,
                                                    GwsClientInfoProvider gwsClientInfoProvider,
                                                    PgiRaceMetadataCache pgiRaceMetadataCache,
                                                    PgiTotePoolId pgiTotePoolId,
                                                    PgiMessageNatsPublisher natsPublisher,
                                                    TotePoolJurisdiction totePoolJurisdiction)
    {
        this.gwsSoap = gwsSoap;
        this.gwsClientInfoProvider = gwsClientInfoProvider;
        this.pgiRaceMetadataCache = pgiRaceMetadataCache;
        this.pgiTotePoolId = pgiTotePoolId;
        this.natsPublisher = natsPublisher;
        this.totePoolJurisdiction = totePoolJurisdiction;
    }

    public void process(RaceOfficialNotificationMessage notification) {
        LOGGER.info("Received {} for {} {}", notification.getClass().getSimpleName(), notification.programName(), notification.race());
        if (pgiTotePoolId != notification.toteId()) {
            return;
        }
        @Nullable PgiRaceMetadata pgiRaceMetadata = pgiRaceMetadataCache.getBy(new PgiRaceInformationKey(notification.programName(), notification.race()));
        if (pgiRaceMetadata == null) {
            LOGGER.error("Could not handle [{}] for Program Name/Race: [{}/{}] as no matching {} was found.",
                    CycleDataUpdatedNotificationMessage.class.getSimpleName(),
                    notification.programName(),
                    notification.race(),
                    PgiRaceMetadata.class.getSimpleName()
            );
            return;
        }
        GpDisplayRequest gpDisplayRequest = createDisplayRequestFor(pgiRaceMetadata.programName(), pgiRaceMetadata.raceNumber(), pgiRaceMetadata.date());
        GpPrices gpPrices = gwsSoap.amTrequestFlatPrices(gwsClientInfoProvider.gpHeader(), gpDisplayRequest);
        processGpPrices(pgiRaceMetadata, gpPrices);
    }

    private void processGpPrices(PgiRaceMetadata pgiRaceMetadata, GpPrices gpPrices) {
        if (gpPrices.getPrices() == null || gpPrices.getPrices().getPriceRecords() == null || gpPrices.getPrices().getPriceRecords().getPriceRecord() == null) return;
        List<PriceRecord> priceRecords = gpPrices.getPrices().getPriceRecords().getPriceRecord();
        for (PriceRecord priceRecord : priceRecords) {
            processPriceRecord(pgiRaceMetadata, priceRecord);
            String desc = String.format("Base Val: %s | Name: %s | Pool ID: %s | Paid: %s | Results: %s | X: %s | Y: %s | Z: %s", priceRecord.getBaseValue(), priceRecord.getName(), priceRecord.getPoolID(), priceRecord.getPaid(), priceRecord.getResults(), priceRecord.getReasonX(), priceRecord.getReasonY(), priceRecord.getReasonZ());
            LOGGER.info("Price Record: {}", desc);
        }
    }

    private void processPriceRecord(PgiRaceMetadata pgiRaceMetadata, PriceRecord priceRecord) {
        @Nullable PgiBetType pgiBetType = PgiBetType.convertFromString(priceRecord.getPoolID());
        if (pgiBetType == null) return;
        @Nullable ToteBetType toteBetType = convertToToteBetType(pgiBetType);
        if (toteBetType == null) return;
        ToteRaceOfficial toteRaceOfficial = new ToteRaceOfficial(
                pgiRaceMetadata.esRaceId(),
                pgiRaceMetadata.track(),
                pgiRaceMetadata.raceNumber(),
                toteBetType,
                totePoolJurisdiction,
                Arrays.stream(priceRecord.getResults().split(",")).map(Integer::valueOf).toList(),
                Double.parseDouble(priceRecord.getPaid())
        );
        natsPublisher.publish(toteRaceOfficial);

    }
}
