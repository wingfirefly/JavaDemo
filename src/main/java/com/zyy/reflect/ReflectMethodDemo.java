package com.zyy.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

public class ReflectMethodDemo {

    @Test
    public void testMethodException()
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException {
        Method method = ReflectMethodDemoA.class.getMethod("aa");
        try {
            method.invoke(null);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            System.out.println(ReflectMethodDemoA.e == targetException);
            System.out.println("target is " + targetException.getMessage());
        }
    }

    @Test
    public void testReflectParam() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Method method = ReflectMethodDemoA.class.getMethod("bb", String[].class);
        method.invoke(null, new Object[] { new String[] { "a", "b" } });
        method.invoke(null, (Object) new String[] { "a", "b" });
    }
}

class ReflectMethodDemoA {

    public static Exception e;

    public static void aa() throws Exception {
        System.out.println("aa");
        e = new Exception("我是异常啊");
        throw e;
    }

    public static void bb(String... args) {
        System.out.println("bb " + Arrays.toString(args));
    }
}