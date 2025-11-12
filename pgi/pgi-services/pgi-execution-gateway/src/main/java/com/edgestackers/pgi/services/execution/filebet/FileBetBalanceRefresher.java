package com.edgestackers.pgi.services.execution.filebet;

import com.amtote.filebet.IService;
import com.edgestackers.pgi.common.client.GwsAccountInfo;
import com.edgestackers.pgi.services.execution.nats.NatsPublisher;
import com.edgestackers.tote.hub.core.datamodel.core.TotePoolJurisdiction;
import com.edgestackers.tote.hub.core.datamodel.core.ToteProvider;
import com.edgestackers.tote.hub.core.datamodel.message.ToteAccountBalanceSummary;
import com.edgestackers.tote.hub.core.datamodel.message.ToteAccountBalanceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public class FileBetBalanceRefresher {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileBetBalanceRefresher.class);
    private static final long PERIODIC_REFRESH_SECONDS = 300L;
    private final IService iService;
    private final GwsAccountInfo masterAccountInfo;
    private final GwsAccountInfo rebateAccountInfo;
    private final TotePoolJurisdiction totePoolJurisdiction;
    private final NatsPublisher natsPublisher;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public FileBetBalanceRefresher(IService iService,
                                   TotePoolJurisdiction totePoolJurisdiction,
                                   GwsAccountInfo masterAccountInfo,
                                   GwsAccountInfo rebateAccountInfo,
                                   NatsPublisher natsPublisher) {
        this.iService = iService;
        this.totePoolJurisdiction = totePoolJurisdiction;
        this.masterAccountInfo = masterAccountInfo;
        this.rebateAccountInfo = rebateAccountInfo;
        this.natsPublisher = natsPublisher;
    }

    public void start() {
        executorService.scheduleAtFixedRate(this::refresh, 1L, PERIODIC_REFRESH_SECONDS, TimeUnit.SECONDS);
        LOGGER.info("{} has started! Refreshing account balance for {} every {}s.", getClass().getSimpleName(), totePoolJurisdiction, PERIODIC_REFRESH_SECONDS);
    }

    public void refresh() {
        refreshFor(ToteAccountBalanceType.MASTER, masterAccountInfo);
        refreshFor(ToteAccountBalanceType.REBATE, rebateAccountInfo);
    }

    private void refreshFor(ToteAccountBalanceType toteAccountType, GwsAccountInfo accountInfo) {
        try {
            double accountBalance = Double.parseDouble(iService.updateBalance(accountInfo.accountNumber(), accountInfo.accountPin()));
            ToteAccountBalanceSummary pgiAccountBalanceSummary = new ToteAccountBalanceSummary(
                    ToteProvider.PGI,
                    totePoolJurisdiction,
                    toteAccountType,
                    accountBalance,
                    generateEpochNanosTimestamp()
            );
            natsPublisher.publish(pgiAccountBalanceSummary);
        }
        catch (Exception e) {
            LOGGER.error("Failed to poll {} account balance for {} due to {} - {}", toteAccountType, totePoolJurisdiction, e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
