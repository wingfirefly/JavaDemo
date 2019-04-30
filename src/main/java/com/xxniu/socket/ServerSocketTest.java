package com.xxniu.socket;


import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetAddress;

class ServerSocketTest extends Thread {
	public static void main(String[] args) {
		if (args.length > 0) {
			service();
		} else {
			client();
		}
	}

	private Socket s;

	ServerSocketTest(Socket s) {
		this.s = s;
	}

	public void run() {
		try {
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			byte[] b = new byte[100];
			int len = is.read(b);
			System.out.println(new String(b, 0, len));
			os.write("Hello!".getBytes());
			//os.close();
			//is.close();
			//s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void service() {
		try {
			ServerSocket ss = new ServerSocket(5550);
			Socket s;
			while (true) {
				s=ss.accept();
				new ServerSocketTest(s).start();
				//ss.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void client() {
		try {
			Socket s = new Socket(InetAddress.getByName("127.0.0.1"), 5550);
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			
			byte[] buf = new byte[100];
			int data;
			int i;
			while(true){
				i = 0;
				while((data = System.in.read())!=13){
				buf[i]=(byte)data;
				i++;
				}
				//os.write("Hello!Lee".getBytes());
				os.write(buf,0,i);
				i = is.read(buf);
				System.out.println(new String(buf, 0, i));
			}
			
			//os.close();
			//is.close();
			//s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

