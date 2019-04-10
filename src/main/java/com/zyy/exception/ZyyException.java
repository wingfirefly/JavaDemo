package com.zyy.exception;
/**
 * 自定义异常
 * @author Shinelon
 *
 */
public class ZyyException extends Exception {

    private static final long serialVersionUID = 1L;

    public ZyyException(String message) {
        super(message);
    }

}
