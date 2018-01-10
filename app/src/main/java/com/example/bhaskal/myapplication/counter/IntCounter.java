package com.example.bhaskal.myapplication.counter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bhaskal on 10-01-2018.
 */

public class IntCounter implements Runnable {

    private final AtomicInteger counterVal;

    private List<CounterObserver> observers;

    public IntCounter() {
        counterVal = new AtomicInteger();
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

    public int getCounterValue() {
        return counterVal.get();
    }

    public void resetCounterValue() {
        counterVal.set(0);
    }

    @Override
    public void run() {
        counterVal.incrementAndGet();
    }
}
