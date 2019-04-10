package com.zyy.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class ReaderWriterDemo {

    @Test
    public void testFile() throws IOException {
        FileWriter writer = new FileWriter("c:/a.txt", true);
        writer.write("你好");
        writer.flush();
        writer.close(); // 不close 或者 flush没内容

        FileReader reader = new FileReader("c:/a.txt");
        char[] cbuf = new char[20];
        int len = reader.read(cbuf);
        System.out.println(new String(cbuf, 0, len));
        reader.close();
    }

    @Test
    public void testBuffered() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("c:/a.txt"));
        writer.write("你");
        writer.newLine(); // 跨平台 line.separator
        writer.write("好");
        writer.close();

        BufferedReader reader = new BufferedReader(new FileReader("c:/a.txt"));
        String line;
        while ((line=reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }

    @Test
    public void testStream() throws IOException {
        //  new OutputStreamWriter(out, charset) // 可以指定字符集
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("c:/a.txt")));
        writer.close();

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream("c:/a.txt")));
        reader.close();
    }
}
