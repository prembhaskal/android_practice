package com.example.bhaskal.myapplication.counter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bhaskal on 11-01-2018.
 */
public class CounterControllerTest {

    @Test
    public void testCounterController() throws InterruptedException {
        final CounterController counterController = CounterController.getInstance();

        counterController.addObserver(new CounterObserver() {
            @Override
            public void notifyChanged() {
                System.out.println(" Update from counter controller --> " + counterController.getCounterValue());
            }
        });

        counterController.start();
        Thread.sleep(5 * 1000);

        System.out.println("stopping the counter...");
        counterController.stop();

        System.out.println("restarting the counter ...");
        counterController.start();
        Thread.sleep(5 * 1000);

        System.out.println("stopping again...");
        counterController.stop();

        System.out.println("resetting ... ");

        counterController.resetCounter();

        System.out.println("starting controller from scratch again ...");
        counterController.start();
        Thread.sleep(5 * 1000);
    }


}