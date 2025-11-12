package com.edgestackers.pgi.services.execution.filebet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileBetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileBetService.class);
    private final FileBetServiceInitializer fileBetServiceInitializer;
    private final FileBetBalanceRefresher fileBetBalanceRefresher;
    private final FileBetStatusChecker fileBetStatusChecker;

    public FileBetService(FileBetServiceInitializer fileBetServiceInitializer,
                          FileBetBalanceRefresher fileBetBalanceRefresher,
                          FileBetStatusChecker fileBetStatusChecker) {
        this.fileBetServiceInitializer = fileBetServiceInitializer;
        this.fileBetBalanceRefresher = fileBetBalanceRefresher;
        this.fileBetStatusChecker = fileBetStatusChecker;
    }

    public void start() {
        fileBetServiceInitializer.initialize();
        fileBetBalanceRefresher.start();
        fileBetStatusChecker.start();
        LOGGER.info("{} has started!", getClass().getSimpleName());
    }
}
