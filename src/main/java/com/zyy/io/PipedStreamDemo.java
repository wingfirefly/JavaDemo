package com.zyy.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamDemo {

    public static void main(String[] args) throws IOException {
        testPipedStreamDemo();
    }

    public static void testPipedStreamDemo() throws IOException {
        PipedInputStream pin = new PipedInputStream();
        PipedOutputStream pout = new PipedOutputStream();
        pin.connect(pout);

        new Thread(new PipedStreamDemoReader(pin)).start();
        new Thread(new PipedStreamDemoWriter(pout)).start();
    }

}

class PipedStreamDemoReader implements Runnable {

    private InputStream in;

    public PipedStreamDemoReader(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            int data;
            while ((data=in.read()) != -1) {
                System.out.println(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("reader end");
    }
}

class PipedStreamDemoWriter implements Runnable {

    private OutputStream out;

    public PipedStreamDemoWriter(OutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                out.write(i);
                Thread.sleep(1000);
            }
            out.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("writer end");
    }

}