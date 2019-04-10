package com.zyy.math;

import org.junit.Test;

public class NumberDemo {

    @Test
    public void testFloat() {
        // 精度问题
        System.out.println(999.9999999999999900); // 1000.0
    }

    @Test
    public void testIntOverflow() {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;

        System.out.println(Integer.MIN_VALUE + Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE + Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE + Integer.MAX_VALUE);

        int r = x + y;

        System.out.println(x ^ r);
        System.out.println(y ^ r);

        // 正数溢出 负数溢出
        // 加数 和 和 不能同时变号
        if (((x ^ r) & (y ^ r)) < 0) {
            throw new ArithmeticException("integer overflow");
        }
    }
}
