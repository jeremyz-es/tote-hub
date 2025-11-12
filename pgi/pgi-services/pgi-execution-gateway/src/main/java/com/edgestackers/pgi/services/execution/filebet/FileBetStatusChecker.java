package com.edgestackers.pgi.services.execution.filebet;

import com.amtote.filebet.IService;
import com.edgestackers.pgi.services.execution.nats.NatsPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.edgestackers.tote.hub.core.util.TimeUtil.generateEpochNanosTimestamp;

public class FileBetStatusChecker {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileBetStatusChecker.class);
    private static final Long PERIODIC_CHECK_SECONDS = 15L;
    private final IService iService;
    private final NatsPublisher natsPublisher;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public FileBetStatusChecker(IService iService, NatsPublisher natsPublisher) {
        this.iService = iService;
        this.natsPublisher = natsPublisher;
    }

    public void start() {
        executorService.scheduleWithFixedDelay(this::checkAndReport, 1L, PERIODIC_CHECK_SECONDS, TimeUnit.SECONDS);
        LOGGER.info("{} has started! Checking file bet status every {}s.", getClass().getSimpleName(), PERIODIC_CHECK_SECONDS);
    }

    private void checkAndReport() {
        String statusCheck = iService.checkStatus();
        long timestamp = generateEpochNanosTimestamp();
        FileBetStatusCheck fileBetStatusCheck = new FileBetStatusCheck(timestamp, statusCheck);
        natsPublisher.publish(fileBetStatusCheck);
    }
}
