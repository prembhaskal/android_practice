package com.example.bhaskal.myapplication.counter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by bhaskal on 10-01-2018.
 */

public class CounterController {
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private IntCounter counter = new IntCounter();
    private ScheduledFuture<?> startedCounter;

    public synchronized void start() {
        if (startedCounter == null)
            startedCounter = executorService.schedule(counter, 1, TimeUnit.SECONDS);
    }

    public synchronized void stop() {
        startedCounter.cancel(true);
        startedCounter = null;
    }

    public synchronized void resetCounter() {
        startedCounter.cancel(true);
        counter.resetCounterValue();
    }

}
