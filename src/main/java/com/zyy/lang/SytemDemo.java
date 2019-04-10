package com.zyy.lang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class SytemDemo {

    @Test
    public void testShutdown() {
        try {
            Class<?> clazz = Class.forName("java.lang.Shutdown");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                method.setAccessible(true);
                method.invoke(null, 2);
                System.out.println(method);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
