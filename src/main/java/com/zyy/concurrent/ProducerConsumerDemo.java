package com.zyy.concurrent;

public class ProducerConsumerDemo {

    public static void main(String[] args) {
        ProducerConsumerDemoResource r = new ProducerConsumerDemoResource();

        ProducerConsumerDemoConsumer consumer = new ProducerConsumerDemoConsumer(r);
        ProducerConsumerDemoProducer producer = new ProducerConsumerDemoProducer(r);

        new Thread(consumer).start();
        new Thread(producer).start();
    }
}

class ProducerConsumerDemoResource {
    public String id;
    public String name;
    public boolean hasResource;

    @Override
    public String toString() {
        return id + " " + name;
    }
}

class ProducerConsumerDemoProducer implements Runnable {
    private ProducerConsumerDemoResource r;

    public ProducerConsumerDemoProducer(ProducerConsumerDemoResource r) {
        this.r = r;
    }

    @Override
    public void run() {
        int x = 0;
        while (true) {
            synchronized (r) {
                if (r.hasResource) {
                    try { r.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
                }
                if (x == 0) {
                    r.id = "艾迪";
                    r.name = "xiyang";
                } else {
                    r.id = "id";
                    r.name = "夕阳";
                }
                x = 1 - x;
                r.hasResource = true;
                r.notifyAll();
            }
        }
    }
}

class ProducerConsumerDemoConsumer implements Runnable {
    private ProducerConsumerDemoResource r;

    public ProducerConsumerDemoConsumer(ProducerConsumerDemoResource r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (r) {
                if (!r.hasResource) {
                    try { r.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
                }
                System.out.println(r);
                r.hasResource = false;
                r.notifyAll();
            }
        }
    }
}
