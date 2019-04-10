package com.zyy.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Test;

public class EncodeDemo {

    @Test
    public void testEncode() {
        final String str = "你好";

        byte[] b = encode(str, "UTF-8");
        System.out.println(Arrays.toString(b));

        String s = decode(b, "UTF-8");
        System.out.println(s);

        System.out.println(Arrays.toString(encode(str, "ISO8859-1")));
        System.out.println(Arrays.toString(encode("谢谢", "ISO8859-1")));
        System.out.println(Arrays.toString(encode("可以", "ISO8859-1")));
    }

    @Test
    public void testEncodeDecodeISO8859() {
        encodeDecodeISO8859("你好");
        encodeDecodeISO8859("水神");
    }

    @Test
    public void testEncodeDecodeUTF8() {
        encodeDecodeUTF8("你好");
        encodeDecodeUTF8("水神");
    }

    private void encodeDecodeISO8859(String str) {
        byte[] b = encode(str, "gb2312");

        String st2 = decode(b, "ISO8859-1");
        byte[] b2 = encode(st2, "ISO8859-1");

        String str3 = decode(b2, "gb2312");
        System.out.println(str3);

        try {
            System.out.println(new String(str.getBytes("ISO-8859-1"), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void encodeDecodeUTF8(String str) {
        byte[] b = encode(str, "gb2312");
        System.out.println(Arrays.toString(b));

        String str2 = decode(b, "UTF-8");
        System.out.println(str2);
        byte[] b2 = encode(str2, "UTF-8");

        System.out.println(Arrays.toString(b2));

        String str3 = decode(b2, "gb2312");
        System.out.println(str3);
    }

    private String decode(byte[] b, String charsetName) {
        try {
            return new String(b, charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] encode(String str, String charsetName) {
        try {
            return str.getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testCanEncode() {
        System.out.println(Charset.forName("iso8859-1").newEncoder().canEncode("你好"));
        System.out.println(Charset.forName("GBK").newEncoder().canEncode("你好"));
    }

    @Test
    public void testConvert() throws IOException {
        writeString("c:/a.txt", "utf-8", "你好");
        String str = readString("c:/a.txt", "utf-8");
        writeString("c:/b.txt", "gb2312", str);
    }

    private void writeString(String name, String charset, String content) throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(name), charset));
        bw.write(content);
        bw.close();
    }

    private String readString(String name, String charsetName) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(name), charsetName));
        String line = br.readLine();
        br.close();
        return line;
    }

}
