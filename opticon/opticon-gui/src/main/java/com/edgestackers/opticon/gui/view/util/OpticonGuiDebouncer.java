package com.edgestackers.opticon.gui.view.util;

import jakarta.annotation.Nullable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class OpticonGuiDebouncer {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @Nullable
    private ScheduledFuture<?> scheduledTask;

    public void debounce(Runnable task) {
        if (scheduledTask != null && !scheduledTask.isDone()) {
            scheduledTask.cancel(false);
        }
        scheduledTask = scheduler.schedule(task, 250, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
