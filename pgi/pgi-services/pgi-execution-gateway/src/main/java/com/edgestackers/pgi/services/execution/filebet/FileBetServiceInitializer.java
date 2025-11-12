package com.edgestackers.pgi.services.execution.filebet;

import com.amtote.filebet.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileBetServiceInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileBetServiceInitializer.class);
    private final IService iService;

    public FileBetServiceInitializer(IService iService) {
        this.iService = iService;
    }

    public void initialize() {
        initializeFileBet();
        initializeChannel();
    }

    private void initializeFileBet() {
        int maxNumChannels = iService.initialize();
        LOGGER.info("{} has started! Max # of wagering channels: {}", getClass().getSimpleName(), maxNumChannels);
    }

    private void initializeChannel() {
        LOGGER.info("Initializing channel...");
        String response = iService.openChannel();
        LOGGER.info("Channel initialization response: {}", response);
    }
}
