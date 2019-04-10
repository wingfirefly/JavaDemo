package com.zyy.enumanno;

public enum MessageWrapper {

    SUCCESS(0, "success"),
    FAILURE(1, "fail");

    private int code;
    private String message;

    private MessageWrapper(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
