package com.zyy.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class NIODemo {

    @Test
    public void testChannel() throws IOException {
        write("c:/a.txt");
        read("c:/a.txt");
    }

    private void write(String name) throws IOException {
        FileOutputStream out = new FileOutputStream(name);
        FileChannel channel = out.getChannel();

        ByteBuffer src = ByteBuffer.wrap("abcdef啊ghijklmn".getBytes());
        channel.write(src);

        channel.close();
        out.close();
    }

    private void read(String name) throws IOException {
        FileInputStream in = new FileInputStream(name);
        FileChannel channel = in.getChannel();

        ByteBuffer dst = ByteBuffer.allocate(1024);
        while (channel.read(dst) > 0) {
            dst.flip();
            while (dst.hasRemaining()) {
                System.out.print((char)dst.get());
            }
//            dst.flip();
            dst.rewind();
        }
        channel.close();
        in.close();
    }

}
