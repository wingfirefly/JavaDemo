package com.zyy.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.junit.Test;

public class URLConnectionDemo {

    @Test
    public void testUrl() throws IOException {
        URL url = new URL("http://127.0.0.1:8080/spring/index.html?name=xiyang&age=16");
        printUrlInfo(url);
        printUrlInfo(new URL("https://127.0.0.1/spring/index.html?name=xiyang&age=16"));
        printUrlInfo(new URL("file://:8080/C:/demo"));
        printUrlInfo(new URL("mailto:java-net@java.sun.com"));
        printUrlInfo(new URL("gopher://127.0.0.1"));
        // ftp
//        printUrlInfo(new URL("jdbc:mysql://localhost:3306:test"));
//        printUrlInfo(new URL("fisfle:f/sfsf/sdfsfsfsf:8080/C:/demo"));
    }

    private void printUrlInfo(URL url) throws IOException {
        System.out.println(url);
        String info = String.format("protocol:%s, host:%s, port:%s, defaultPort: %d, file:%s, query:%s", url.getProtocol(), url.getHost(),
                url.getPort(), url.getDefaultPort(), url.getFile(), url.getQuery());
        System.out.println(info);
        /*
        url.getDefaultPort()
        url.getProtocol();
        url.getUserInfo();
        url.getHost();
        url.getPort(); */
    }

    @Test
    public void testURLConnection() throws IOException {
        URL url = new URL("file://:8080/C:/demo");
        URLConnection conn = url.openConnection();
        System.out.println(conn);
        // FileURLConnection HttpURLConnection
    }

    @Test
    public void testSendGet() throws IOException {
        URL url = new URL("http://www.baidu.com");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        printResponse(conn.getInputStream());
        conn.disconnect();
    }

    @Test
    public void testSendPostForm() throws IOException {
        URL url = new URL("http://127.0.0.1:8180/springmvc/hello/login");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setConnectTimeout(5000);
        conn.setDoOutput(true);

//        String params = String.format("username=%s&password=%s", "admin", "管理员"); // is ok
        String params = String.format("username=%s&password=%s", "admin", URLEncoder.encode("管理员", "utf-8"));
        OutputStream out = conn.getOutputStream();
        System.out.println(params);
        out.write(params.getBytes());

        printResponse(conn.getInputStream());
        conn.disconnect();
    }

    @Test
    public void testSendPostJson() throws IOException {
        URL url = new URL("http://127.0.0.1:8180/springmvc/hello/login3");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setConnectTimeout(5000);
        conn.setDoOutput(true);

        String body = String.format("{ \"username\": \"%s\", \"password\": \"%s\" }", "管理员", "123456");
//        String body2 = String.format("username=%s&password=%s", "admin", URLEncoder.encode("管理员", "utf-8"));
        OutputStream out = conn.getOutputStream();
        out.write(body.getBytes());

        printResponse(conn.getInputStream());
        conn.disconnect();
    }

    private void printResponse(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

}
