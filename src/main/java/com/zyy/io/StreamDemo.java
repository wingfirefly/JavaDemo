package com.zyy.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.junit.Test;

public class StreamDemo {
    // BufferedInputStream
    // BufferedOutputStream

    @Test
    public void testFile() throws IOException {
        FileOutputStream out = new FileOutputStream("c:/a.txt");
        out.write(("你好").getBytes());
        // out.flush(); // 没实现
        out.close();

        FileOutputStream out2 = new FileOutputStream("c:/a.txt", true);
        out2.write(("啊").getBytes());
        // out.flush(); // 没实现
        out2.close();

        InputStream in = new FileInputStream("c:/a.txt");
        int len;
        byte[] b = new byte[1024];
        while ((len = in.read(b)) != -1) {
            System.out.print(new String(b, 0, len/* , "UTF-8" */));
        }
        in.close();
    }

    @Test
    public void testByteArray() {
        // getBytes("utf-8");
        byte[] inBuf = "hello".getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(inBuf);
        int data;
        while ((data = in.read()) != -1) {
            System.out.print((char) data);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            out.write("world".getBytes());
            byte[] buf = out.toByteArray();
            System.out.println(" " + new String(buf));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testData() throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream("c:/a.txt"));
        out.writeDouble(2.22);
        out.writeInt(20);
        out.writeUTF("你好");
        out.close();

        DataInputStream in = new DataInputStream(new FileInputStream("c:/a.txt"));
        System.out.println(in.readDouble());
        System.out.println(in.readInt());
        System.out.println(in.readUTF());
        in.close();
    }

    @Test
    public void testGZip() throws IOException {
        byte[] bData = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa".getBytes();
        System.out.println("before length is " + bData.length);

        // compress the data
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gout = new GZIPOutputStream(out);
        gout.write(bData);
        gout.close(); // must close

        byte[] buf = out.toByteArray();
        System.out.println("after length is " + buf.length);

        // uncompress the byte data
        GZIPInputStream gin = new GZIPInputStream(new ByteArrayInputStream(buf));
        byte[] b = new byte[1024];
        int len = gin.read(b);

        System.out.println(new String(b, 0, len));
    }

    @Test
    public void testPrint() throws IOException {
        PrintStream out = new PrintStream("c:/a.txt");
        // System.out
        out.println("你好");
        out.close();
    }
}
