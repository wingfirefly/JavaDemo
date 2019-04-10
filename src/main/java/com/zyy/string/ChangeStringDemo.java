package com.zyy.string;

import java.io.PrintStream;
import java.lang.reflect.Field;

public class ChangeStringDemo {

    public static void main(String[] args) {
//        doSth1();
//        doSth2();
        doSth3();
//        B.doSth3(); // aaa
        // doSth1();
        // doSth2();
        // doSth3();
        B.doSth3();
        System.out.println("aaa");
    }

    public static void doSth1() {
        System.out.println("bbb");
        System.exit(0);
    }

    public static void doSth2() {
        System.setOut(new PrintStream(System.out) {
            @Override
            public void println(String x) {
                if (x.equals("aaa")) {
                    x = "bbb";
                }
                super.println(x);
            }
        });
    }

    public static void doSth3() {
        try {
            Field field = String.class.getDeclaredField("value");
            field.setAccessible(true);
            char[] value = (char[]) field.get("aaa");
            value[0] = 'b';
            value[1] = 'b';
            value[2] = 'b';
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static class B {
        public static void doSth3() {
            try {
                Field field = String.class.getDeclaredField("value");
                field.setAccessible(true);
                char[] value = (char[]) field.get("aaa");
                value[0] = 'b';
                value[1] = 'b';
                value[2] = 'b';
            } catch (NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
