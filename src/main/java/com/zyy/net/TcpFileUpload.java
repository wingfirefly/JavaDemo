package com.zyy.net;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public interface TcpFileUpload {
    String ServerHost = "127.0.0.1";
    int ServerPort = 10086;
    String ServerFilename = "C:\\Users\\work\\Desktop\\server.png";
    String ClientFilename = "C:\\Users\\work\\Desktop\\client.jpg";
}

class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(TcpFileUpload.ServerPort);
        for (int i=0; i<100; i++) {
            Socket socket = s.accept();
            handelRequest(socket);
        }
        s.close();
    }

    private static void handelRequest(Socket socket) throws IOException {
        FileOutputStream fos = new FileOutputStream(TcpFileUpload.ServerFilename);

        InputStream in = socket.getInputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = in.read(b)) != -1) {
            fos.write(b, 0, len);
        }
        socket.close();
        fos.close();
    }
}

class TcpClient {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket(TcpFileUpload.ServerHost, TcpFileUpload.ServerPort);

        FileInputStream fis = new FileInputStream(TcpFileUpload.ClientFilename);

        OutputStream out = s.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = fis.read(b)) != -1) {
            out.write(b, 0, len);
        }
        // s.shutdownOutput();
        s.close();

        fis.close();
    }
}