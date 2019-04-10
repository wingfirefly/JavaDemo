package com.zyy.concurrent;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {

    public static void main(String[] args) {
        testTimeBomb();
        System.out.println("----------------");
        testTimer();
        // quartz
    }

    private static void testTimer() {
        Timer timer = new Timer();
        timer.schedule(new RemindTask(), 2000, 500);
    }

    public static void testTimeBomb() {
        Timer timer = new Timer();
        timer.schedule(new RemindTask(), 2000);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }

    private static class RemindTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("Timer is run!");
        }
    }
}
