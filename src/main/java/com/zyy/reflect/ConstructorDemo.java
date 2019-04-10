package com.zyy.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.junit.Test;

public class ConstructorDemo {

    @Test
    public void testInstance() {
        try {
            ConstructorDemoA.class.newInstance();
            ConstructorDemoB.class.newInstance();
            ConstructorDemoC.class.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConstructor() {
        Class<ConstructorDemoB> clazz = ConstructorDemoB.class;
        try {
            Constructor<ConstructorDemoB> cons0 = clazz.getConstructor();
            cons0.newInstance();

            Constructor<ConstructorDemoB> cons2 = clazz.getConstructor(String.class);
            cons2.newInstance("xiyang");

            Constructor<ConstructorDemoB> cons3 = clazz.getConstructor(String[].class);
            cons3.newInstance((Object) new String[] { "xiyang" });
            cons3.newInstance(new Object[] { new String[] { "xiyang" } });
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}

class ConstructorDemoA {
}

class ConstructorDemoB {
    public ConstructorDemoB() {
        System.out.println("ConstructorDemoB 0");
    }

    public ConstructorDemoB(String name) {
        System.out.println("ConstructorDemoB, name is " + name);
    }

    public ConstructorDemoB(String... names) {
        System.out.println("ConstructorDemoB, names is " + Arrays.toString(names));
    }
}

class ConstructorDemoC {
    public ConstructorDemoC(String name) {
        System.out.println("ConstructorDemoC 1");
    }
}