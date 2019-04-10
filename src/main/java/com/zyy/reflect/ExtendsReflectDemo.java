package com.zyy.reflect;

import java.lang.reflect.Method;

import org.junit.Test;

public class ExtendsReflectDemo {

    @Test
    public void testExtendsReflect() {
        try {
            Method method = ExtendsReflectDemoB.class.getDeclaredMethod("init"); // java.lang.NoSuchMethodException
            System.out.println(method);
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }
}

class ExtendsReflectDemoA {
    public ExtendsReflectDemoA() {
        init();
    }

    public void init() { }
}

class ExtendsReflectDemoB extends ExtendsReflectDemoA {
}
