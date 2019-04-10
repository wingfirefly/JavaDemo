package com.zyy.net;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TomcatDemo {

    public static final String ServerHost = "127.0.0.1";
    public static final int ServerPort = 10086;
    public static final String ClientFilename = "C:\\Users\\work\\Desktop\\hello.html";

    public static void main(String[] args) throws IOException {
        System.out.println(ServerHost + ":" + ServerPort);
        ServerSocket s = new ServerSocket(ServerPort);
        for (int i = 0; i < 100; i++) {
            Socket socket = s.accept();
            handelRequest(socket);
        }
        s.close();
    }

    private static void handelRequest(Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.length() == 0) {
                break;
            }
        }

        FileInputStream fis = new FileInputStream(ClientFilename);
        OutputStream out = socket.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = fis.read(b)) != -1) {
            out.write(b, 0, len);
        }
        fis.close();
        socket.shutdownOutput();
    }

}

class TomcatDemoClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket(TomcatDemo.ServerHost, TomcatDemo.ServerPort);

        FileOutputStream fos = new FileOutputStream(TomcatDemo.ClientFilename);

        System.out.println("start");
        InputStream in = s.getInputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = in.read(b)) != -1) {
            fos.write(b, 0, len);
        }
        // s.shutdownOutput();
        System.out.println("end");

        s.close();
        fos.close();
    }
}
