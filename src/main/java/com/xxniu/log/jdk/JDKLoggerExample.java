package com.xxniu.log.jdk;

import java.util.logging.Logger;

public class JDKLoggerExample {
	private static Logger logger = MyLoggerUtil.setLoggerHanlder(Logger.getLogger("my.logger"));  
	  
    public static void main(String[] args) {  
          
        logger.info("JDK Logger is logging information at INFO Level");   
   }  
}
