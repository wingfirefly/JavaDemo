package com.zyy.concurrent;

public class MulProducerConsumerDemo {

    public static void main(String[] args) {
        MulProducerConsumerDemoResource r = new MulProducerConsumerDemoResource();

        MulProducerConsumerDemoProducer producer = new MulProducerConsumerDemoProducer(r);
        MulProducerConsumerDemoConsumer consumer = new MulProducerConsumerDemoConsumer(r);

        new Thread(producer).start();
        new Thread(producer).start();

        new Thread(consumer).start();
        new Thread(consumer).start();

// is same
/*        ArrayList<Runnable> producerList = new ArrayList<>();
        producerList.add(new MulProducerConsumerDemoProducer(r));
        producerList.add(new MulProducerConsumerDemoProducer(r));

        ArrayList<Runnable> consumerList = new ArrayList<>();
        consumerList.add(new MulProducerConsumerDemoConsumer(r));
        consumerList.add(new MulProducerConsumerDemoConsumer(r));

        ThreadUtil.startTasks(producerList);
        ThreadUtil.startTasks(consumerList);*/
    }

}

class MulProducerConsumerDemoResource {
    public String id;
    public String name;
    public boolean hasResource;

    public void produce(int x) {
        synchronized (this) {
            while (hasResource) {
                ThreadUtil.wait(this);
            }
            if (x == 0) {
                id = "艾迪";
                name = "xiyang";
            } else {
                id = "id";
                name = "夕阳";
            }
            hasResource = true;
            notifyAll();
        }
    }

    public void consume() {
        synchronized (this) {
            while (!hasResource) {
               ThreadUtil.wait(this);
            }
            System.out.println(toString());
            hasResource = false;
            notifyAll();
        }
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}

class MulProducerConsumerDemoProducer implements Runnable {
    private MulProducerConsumerDemoResource r;

    public MulProducerConsumerDemoProducer(MulProducerConsumerDemoResource r) {
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

class MulProducerConsumerDemoConsumer implements Runnable {
    private MulProducerConsumerDemoResource r;

    public MulProducerConsumerDemoConsumer(MulProducerConsumerDemoResource r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.consume();
        }
    }
}
