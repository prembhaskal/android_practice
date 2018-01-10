package com.example.bhaskal.myapplication.counter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by bhaskal on 10-01-2018.
 */

public class CounterController implements CounterCallback {

    private final ScheduledExecutorService executorService;
    private final IntCounter counter;
    private final List<CounterObserver> observers;

    private ScheduledFuture<?> startedCounter;
    private static CounterController counterController;

    private CounterController() {
        counter = new IntCounter(this);
        executorService = Executors.newSingleThreadScheduledExecutor();
        observers = new ArrayList<>();
    }

    public static synchronized CounterController getInstance() {
        if (counterController == null) {
            counterController = new CounterController();
        }
        return counterController;
    }

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

    public int getCounterValue() {
        return counter.getCounterValue();
    }

    public synchronized void addObserver(CounterObserver observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    public synchronized void removeObserver(CounterObserver observer) {
        if (observer != null) {
            observers.remove(observer);
        }
    }

    private synchronized void notifyObservers() {
        for (CounterObserver observer : observers) {
            observer.notifyChanged();
        }
    }

    @Override
    public void newCounterValue(int value) {
        notifyObservers();
    }
}
