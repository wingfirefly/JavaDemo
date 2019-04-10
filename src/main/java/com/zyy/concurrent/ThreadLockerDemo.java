package com.zyy.concurrent;

/**
 * 死锁
 * @author Shinelon
 *
 */
public class ThreadLockerDemo {

    public static void main(String[] args) {
        new ThreadLockerDemo().testLocker();
    }

    public void testLocker() {
        MulthreadA a = new MulthreadA();
        new Thread(new MulthreadBA(a)).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new MulthreadBB(a)).start();
    }

}

class MulthreadA {

    public void ma() {
        System.out.println("ma1");
        synchronized (this) {
            System.out.println("ma2");
            while(true) {
            }
        }
    }

    public void mb() {
        System.out.println("mb1");
        synchronized (this) {
            System.out.println("mb2");
            while(true) {
            }
        }
    }

}

class MulthreadBA implements Runnable {
    private MulthreadA a;

    public MulthreadBA(MulthreadA a) {
        this.a = a;
    }

    @Override
    public void run() {
        a.ma();
    }

}

class MulthreadBB implements Runnable {
    private MulthreadA a;

    public MulthreadBB(MulthreadA a) {
        this.a = a;
    }

    @Override
    public void run() {
        a.mb();
    }

}
