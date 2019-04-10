package com.zyy.text;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.junit.Test;

public class FormatDemo {

    @Test
    public void testNewLine() {
        // String newLine = java.security.AccessController
        // .doPrivileged(new
        // sun.security.action.GetPropertyAction("line.separator"));
        String newLine = System.getProperty("line.separator");
        for (char ch : newLine.toCharArray()) {
            System.out.println((int) ch);
        }
    }

    // java.util.Formatter inner class private static class Conversion

    @Test
    public void testStringFormat() {
        String time = String.format("%02d:%02d:%02d", 9, 10, 20);
        System.out.println(time);
        System.out.println(String.format("%.03f", 23.6));
    }

    @Test
    public void testNumberFormat() {
        NumberFormat instance = NumberFormat.getInstance(Locale.US);
        String currecy = instance.format(1245600000.033);
        System.out.println(currecy);
    }

    @Test
    public void testDecimalFormat() {
        // # 为 0 则不显示
        // new DecimalFormat().setRoundingMode(RoundingMode.FLOOR);
        System.out.println(new DecimalFormat("##.###").format(3.15551111111)); // 3.156
        System.out.println(new DecimalFormat("00.000").format(3.15)); // 03.150
        System.out.println(new DecimalFormat("##0.00#####").format(3.15551111111)); // 3.1555111
        System.out.println(new DecimalFormat("##0.00#####").format(new BigDecimal("3.15551111111")));
    }

    @Test
    public void testPrintf() {
        Object obj = 2.3;
        System.out.printf("%.3f\n", obj);
        System.out.println(String.format("%%"));
        System.out.println(String.format("%.03f", 0f));
        double value = 2;
        System.out.println(String.format("%.03f", value));
        System.out.println(String.format("b2015%02d", 3));
        System.out.println(String.format("b2015%02d", 12));
    }
}
