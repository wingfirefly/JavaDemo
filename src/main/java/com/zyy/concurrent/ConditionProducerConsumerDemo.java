package com.zyy.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 生产者消费者
 * @author Shinelon
 *
 */
public class ConditionProducerConsumerDemo {

    public static void main(String[] args) {
        ConditionProducerConsumerDemoResource r = new ConditionProducerConsumerDemoResource();

        ConditionProducerConsumerDemoProducer producer = new ConditionProducerConsumerDemoProducer(r);
        ConditionProducerConsumerDemoConsumer consumer = new ConditionProducerConsumerDemoConsumer(r);

        new Thread(producer).start();
        new Thread(producer).start();
        new Thread(producer).start();

        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(consumer).start();

/*        ArrayList<Runnable> producerList = new ArrayList<>();
        producerList.add(new ConditionProducerConsumerDemoProducer(r));
        producerList.add(new ConditionProducerConsumerDemoProducer(r));
        producerList.add(new ConditionProducerConsumerDemoProducer(r));

        ArrayList<Runnable> consumerList = new ArrayList<>();
        consumerList.add(new ConditionProducerConsumerDemoConsumer(r));
        consumerList.add(new ConditionProducerConsumerDemoConsumer(r));
        consumerList.add(new ConditionProducerConsumerDemoConsumer(r));

        ThreadUtil.startTasks(producerList);
        ThreadUtil.startTasks(consumerList);*/
    }

}

class ConditionProducerConsumerDemoResource {
    public String id;
    public String name;
    public boolean hasResource;

    private Lock lock = new ReentrantLock();

    private Condition conditionPro = lock.newCondition();
    private Condition conditionCon = lock.newCondition();

    public void produce(int x) {
        lock.lock();
        try {
            while (hasResource) {
                try {
                    conditionPro.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (x == 0) {
                id = "艾迪";
                name = "xiyang";
            } else {
                id = "id";
                name = "夕阳";
            }
            hasResource = true;

            conditionCon.signal();
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            while (!hasResource) {
                try {
                    conditionCon.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(toString());
            hasResource = false;

            conditionPro.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}

class ConditionProducerConsumerDemoProducer implements Runnable {
    private ConditionProducerConsumerDemoResource r;

    public ConditionProducerConsumerDemoProducer(ConditionProducerConsumerDemoResource r) {
        this.r = r;
    }

    @Override
    public void run() {
        int x = 0;
        while (true) {
            r.produce(x);
            x = 1 - x;
        }
    }
}

class ConditionProducerConsumerDemoConsumer implements Runnable {
    private ConditionProducerConsumerDemoResource r;

    public ConditionProducerConsumerDemoConsumer(ConditionProducerConsumerDemoResource r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.consume();
        }
    }
}
