package com.zyy.coll;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
/**
 * 可以模拟生产者消费者模式
 * @author Shinelon
 *
 */
public class BlockQueueDemo {
	// 阻塞队列，JUC包下的，每次操作都会上锁
    private BlockingDeque<String> queue; // BlockingQueue

    public static void main(String[] args) {
        new BlockQueueDemo().run();
    }

    private void run() {
        queue = new LinkedBlockingDeque<>();

        new Thread(() -> {
            System.out.println("start push");
            for (int i=0; i<12; i++) {
                queue.push("msg" + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            System.out.println("start take");
            for (int i=0; i<12; i++) {
                try {
                    String msg = queue.take();
                    System.out.println(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
