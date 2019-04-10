package com.zyy.exception;

import java.io.FileInputStream;
import java.io.IOException;
/**
 * 异常
 * @author Shinelon
 *
 */
public class ExceptionDemo {

    public int testException() throws Exception {
        throw new ZyyException("runtime exception");
    }

    public int testRuntime() {
        throw new RuntimeException("runtime exception");
    }

    public void add(String e) {
        // AbstractList
        throw new UnsupportedOperationException();
    }

    public void testTry() {
        try {

        } finally {

        }

        // java7
        // AutoCloseable
        try (FileInputStream fis = new FileInputStream("");
            FileInputStream fis2 = new FileInputStream("")) {
        } catch(IOException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void aa() throws ZyyException {}

}
