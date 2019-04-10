package com.zyy.math;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalDemo {

    /**
     * see FormatDemo.testDecimalFormat
     */
    @Test
    public void testIrrationalNubmer() {
        BigDecimal a = new BigDecimal("2");
        BigDecimal b = new BigDecimal("13");

        a.divide(b, 6, BigDecimal.ROUND_HALF_UP);

        // a.divide(b); // Non-terminating decimal expansion
    }

    @Test
    public void testScale() {
        BigDecimal b = new BigDecimal(2.326);
        System.out.println(new BigDecimal(b.toString()).setScale(2, BigDecimal.ROUND_UP)); // 2.33
        System.out.println(new BigDecimal(b.toString()).setScale(2, BigDecimal.ROUND_DOWN)); // 2.32
        System.out.println(new BigDecimal(b.toString()).setScale(2, BigDecimal.ROUND_CEILING)); // 2.33
        System.out.println(new BigDecimal(b.toString()).setScale(2, BigDecimal.ROUND_FLOOR)); // 2.32
        System.out.println(new BigDecimal(b.toString()).setScale(2, BigDecimal.ROUND_HALF_UP)); // 2.33
        System.out.println(new BigDecimal(b.toString()).setScale(2, BigDecimal.ROUND_HALF_DOWN)); // 2.33
        System.out.println(new BigDecimal(b.toString()).setScale(2, BigDecimal.ROUND_HALF_EVEN)); // 2.33
        System.out.println(new BigDecimal("2.330").setScale(2, BigDecimal.ROUND_UNNECESSARY)); // 2.33 inexact 有损失则报 ArithmeticException
    }

}
