package com.example.bhaskal.myapplication.counter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bhaskal on 10-01-2018.
 */

public class IntCounter implements Runnable {

    private final AtomicInteger counterVal;
    private final CounterCallback counterCallback;

    public IntCounter(CounterCallback counterCallback) {
        this.counterCallback = counterCallback;
        counterVal = new AtomicInteger();
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
        counterCallback.newCounterValue(counterVal.get());
    }
}
