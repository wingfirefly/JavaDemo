package com.zyy.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import org.junit.Test;

public class FileOperateDemo {

    @Test
    public void testCopy() throws IOException {
        // 装饰设计模式
        // BufferedInputStream
        InputStream bis = new BufferedInputStream(new FileInputStream("c:\\banzou.mp3"));
        OutputStream bos = new BufferedOutputStream(new FileOutputStream("c:\\banzou_1.mp3"));

        int data = 0;
        while ((data = bis.read()) != -1) {
            bos.write(data);
        }

        bos.close();
        bis.close();
    }

    @Test
    public void testSplit() throws IOException {
        split("c:\\banzou.mp3", 5);
    }

    private void split(String name, int partition) throws IOException {
        File file = new File(name);
        long length = file.length();
        long partitionSize = length / partition;
        String postfix = name.substring(name.lastIndexOf('.'));

        InputStream in = new BufferedInputStream(new FileInputStream(file));

        for (int i=0; i<partition; i++) {
            OutputStream bufos = new BufferedOutputStream(new FileOutputStream(String.format("c:\\partition_%d%s", i, postfix)));

            long start = partitionSize * i;
//            long end = start + partitionSize;
            long end = (i == partition - 1) ? length : start + partitionSize; // read != -1

            int data;
            for (long j=start; j<end; j++) {
                data = in.read();
                bufos.write(data);
            }
            bufos.close();
        }

        in.close();
    }

    @Test
    public void testDownload() throws IOException {
        download(); // junit not ok
    }

    public static void main(String[] args) throws IOException {
        new FileOperateDemo().testDownload();
    }

    private void download() throws IOException {
        RandomAccessFile file = new RandomAccessFile("c:\\banzou2.mp3", "rw");
        long length = getLength();
        file.setLength(length);
        file.close();

        final int nThread = 5;
        long partitionSize = length / nThread;
        for (int i=0; i<nThread; i++) {
            long start = partitionSize * i;
            long end = (i == nThread - 1) ? length : start + partitionSize;
            // String.format("c:\\banzou_%d.mp3", i)
            new Thread(new DownloadTask( "c:\\banzou2.mp3" , start, end)).start();
        }
    }

    private class DownloadTask implements Runnable {
        private String name;
        private long start;
        private long end;

        public DownloadTask(String name, long start, long end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                InputStream in = getInputStream();
                in.skip(start);

                RandomAccessFile file = new RandomAccessFile(name, "rw");
                file.seek(start);

                int data;
                for (long i=start; i<end; i++) {
                    data = in.read();
                    file.write(data);
                }
                file.close();

                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private long getLength() {
        // 假设 来自网络
        File file = new File("c:\\banzou.mp3");
        return file.length();
    }

    private InputStream getInputStream() throws IOException {
        // 假设 来自网络
        FileInputStream in = new FileInputStream("c:\\banzou.mp3");
        return in;
    }
}
