package com.zyy.concurrent;
/**
 * 保证可见性
 * @author Shinelon
 *
 */
public class VolatileDemo {

    private volatile static boolean isRun = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRun /*()*/) {
                }
            }
        }).start();

        Thread.sleep(1000);
        isRun = false;
    }

    public synchronized static boolean isRun() {
        return isRun;
    }

    public synchronized static void setRun(boolean isRun) {
        VolatileDemo.isRun = isRun;
    }
}
