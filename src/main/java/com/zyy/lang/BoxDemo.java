package com.zyy.lang;

import org.junit.Test;

public class BoxDemo {

    @Test
    public void testNull() {
        Integer ii = BoxDemo.getIi();
        int i = ii; // java.lang.NullPointerException
        System.out.println(i);
    }

    private static Integer getIi() {
        return null;
    }

}
