package com.zyy.concurrent;

public class ThreadLocalDemo {

    private ThreadLocal<String> context = new ThreadLocal<String>();

    public static void main(String[] args) {
        new ThreadLocalDemo().test();
    }

    public void test() {
        for (int i = 0; i < 5; i++) {
            final String name = "thread " + i;
            new Thread(new RequestTask(name)).start();
            ThreadUtil.sleep(1);
        }
    }

    private class RequestTask implements Runnable {
        private String name;

        public RequestTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            context.set(name + " 1");
            dispatch();
        }

        private void dispatch() {
            String name = context.get();
            System.out.println(name);
        }
    }
}
