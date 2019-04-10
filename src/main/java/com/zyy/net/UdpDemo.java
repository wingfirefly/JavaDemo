package com.zyy.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public interface UdpDemo {
    String RecieverHost = "127.0.0.1";
    int RecieverPort = 10086;
}

class UdpDemoSender {
    public static void main(String[] args) throws IOException {
        byte[] buf = "联通".getBytes();
        InetAddress netAddress = InetAddress.getByName(UdpDemo.RecieverHost);
        DatagramPacket dp = new DatagramPacket(buf, buf.length, netAddress, UdpDemo.RecieverPort);

        DatagramSocket ds = new DatagramSocket(/*8888*/);
        ds.send(dp);
        ds.close();
    }
}

class UdbReciever {
    public static void main(String[] args) throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);

        DatagramSocket ds = new DatagramSocket(UdpDemo.RecieverPort);
        ds.receive(dp); // 阻塞式方法

        String ip = dp.getAddress().getHostAddress();
        int port = dp.getPort();
        String data = new String(dp.getData(), 0, dp.getLength());
        System.out.println(ip + "::" + port + "::" + data);

        ds.close();
    }
}