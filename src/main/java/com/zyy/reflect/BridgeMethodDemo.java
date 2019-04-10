package com.zyy.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class BridgeMethodDemo {

    @Test
    public void testBridgeMethod() {
        Class<BridgeMethodDemoB> clazz = BridgeMethodDemoB.class;
        Method[] methods = clazz.getDeclaredMethods();
        // get(java.lang.String) false, get(java.lang.Object) true
        for (Method method : methods) {
            System.out.println(method);
            System.out.println(method.isBridge());
        }
    }

    @Test
    public void testBridgeMethod2() {
        Class<BridgeMethodDemoC> clazz = BridgeMethodDemoC.class;
        Method[] methods = clazz.getMethods();
        // get(java.lang.String) false, get(java.lang.Object) true
        for (Method method : methods) {
            System.out.println(method);
            System.out.println(method.isBridge());
        }
    }

    @Test
    public void testBridgeMethod3() {
        Class<BridgeMethodDemoC> clazz = BridgeMethodDemoC.class;
        Method[] methods = clazz.getMethods();

        Method m1 = BridgeMethodDemoA.class.getDeclaredMethods()[0];
        Method m2 = methods[1];

        BridgeMethodDemoC c = new BridgeMethodDemoC();

        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m1 == m2); // false

        try {
            m1.invoke(c, 2);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
/*
 * error
interface CC<T, E> {
    void get(T t);
    void get(E t);
}
*/

abstract class BridgeMethodDemoA<T> {
    abstract T get(T t);
}

class BridgeMethodDemoB extends BridgeMethodDemoA<String> {
    @Override
    String get(String s) {
        System.out.println("get BridgeMethodDemoB");
        return "BridgeMethodDemoB";
    }
}

class BridgeMethodDemoC extends BridgeMethodDemoA<Integer> {
    @Override
    public Integer get(Integer s) {
        System.out.println("get BridgeMethodDemoC");
        return 222;
    }
}