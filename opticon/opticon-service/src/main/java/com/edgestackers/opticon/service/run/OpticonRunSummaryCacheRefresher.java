package com.edgestackers.opticon.service.run;

import com.edgestackers.opticon.common.datamodel.OpticonRunSummary;
import com.edgestackers.opticon.service.metadata.MasterFieldsCache;
import com.edgestackers.tote.hub.core.metadata.MasterField;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OpticonRunSummaryCacheRefresher {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpticonRunSummaryCacheRefresher.class);
    private final OpticonRunSummaryFactory opticonRunSummaryFactory;
    private final OpticonRunSummaryProcessor opticonRunSummaryProcessor;
    private final MasterFieldsCache masterFieldsCache;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public OpticonRunSummaryCacheRefresher(OpticonRunSummaryFactory opticonRunSummaryFactory,
                                           OpticonRunSummaryProcessor opticonRunSummaryProcessor,
                                           MasterFieldsCache masterFieldsCache)
    {
        this.opticonRunSummaryFactory = opticonRunSummaryFactory;
        this.opticonRunSummaryProcessor = opticonRunSummaryProcessor;
        this.masterFieldsCache = masterFieldsCache;
    }

    public void start() {
        executorService.scheduleWithFixedDelay(this::refresh, 1L, 15L, TimeUnit.SECONDS);
        LOGGER.info("{} has started!", getClass().getSimpleName());
    }

    private void refresh() {
        List<MasterField> masterFields = masterFieldsCache.getAll();
        for (MasterField masterField : masterFields) {
            @Nullable OpticonRunSummary runSummary = opticonRunSummaryFactory.createOpticonRunSummary(masterField);
            opticonRunSummaryProcessor.process(runSummary);
        }
    }
}
