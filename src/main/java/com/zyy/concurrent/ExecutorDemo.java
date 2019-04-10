package com.zyy.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * @author Shinelon
 *
 */
public class ExecutorDemo {

    public static void main(String[] args) {
        /*
         * ExecutorService executor = Executors.newSingleThreadExecutor();
         * executor.execute(new ExecutorDemoA()); executor.shutdown();
         * executor.shutdownNow();
         */

//        ExecutorService executor = Executors.newSingleThreadExecutor();
        ExecutorService executor = Executors.newFixedThreadPool(12);

        ArrayList<Callable<String>> tasks = new ArrayList<Callable<String>>();
        tasks.add(new ExecutorDemoB(1));
        tasks.add(new ExecutorDemoB(2));
        tasks.add(new ExecutorDemoB(3));
        tasks.add(new ExecutorDemoB(4));

        try {
            List<Future<String>> list = executor.invokeAll(tasks);
            for (Future<String> future : list) {
                String result = future.get();
                System.out.println(result + " finish"); // 1 2 3 4 finish
            }
            System.out.println(tasks.size());
            System.out.println(list.size());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
//        executor.shutdownNow();
    }

}

class ExecutorDemoA implements Runnable {

    @Override
    public void run() {
    }

}

class ExecutorDemoB implements Callable<String> {

    private int id;
    public ExecutorDemoB(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        System.out.println(id + " in call");
        Thread.sleep((5 - id) * 1000);
        return id + "";
    }

}