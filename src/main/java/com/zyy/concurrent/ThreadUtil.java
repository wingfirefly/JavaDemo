package com.zyy.concurrent;

import java.util.List;

class ThreadUtil {

    public static void wait(Object obj) {
        try { obj.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public static void sleep(int seconds) {
        try { Thread.sleep(seconds * 1000); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }

    public static void startTasks(List<? extends Runnable> tasks) {
        for (Runnable task : tasks) {
            new Thread(task).start();
        }
    }
}
