package com.xxniu.log.jdk;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;

public class SocketHandlerTest {
	private SocketHandler handler = null;  
	  
    private static Logger logger = Logger  
            .getLogger("my.logger.SocketHandlerTest");  
  
    public SocketHandlerTest(String host, int port) {  
        try {  
            handler = new SocketHandler(host, port);  
            logger.addHandler(handler);  
            logger.info("SocketHandler���гɹ�......");  
        } catch (IOException e) {  
            logger.severe("�����ַ�Ͷ˿��Ƿ���ȷ......");  
              
            StringBuilder sb = new StringBuilder();  
            sb.append(e.toString()).append("\n");  
            for(StackTraceElement elem : e.getStackTrace())  
            {  
                sb.append("\tat ").append(elem).append("\n");  
            }  
            logger.severe(sb.toString());  
        }  
    }  
  
    public static void main(String args[]) {  
        new SocketHandlerTest("192.168.1.253", 70001);  
    }  
}
