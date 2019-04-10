package com.zyy.designpattern;

import org.junit.Test;
/**
 * 单例模式
 * @author Shinelon
 *
 */
public class SingletonDemo {

    @Test
    public void testStatic() {
        System.out.println(SingletonStatic.class.getName());
        System.out.println("-------");
        SingletonStatic.getInstance();
    }
}

class Singleton {
    private static final Singleton INSTANCE = new Singleton();
    private static boolean isInit;

    private Singleton() {
        if (isInit) {
            throw new RuntimeException("yijingchushihuale");
        }
        isInit = true;
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}

class SingletonLazy {

    private SingletonLazy() { }

    private static SingletonLazy instance;

    public static SingletonLazy getInstance() {
        if (instance == null) {
            synchronized(SingletonLazy.class) {
                if (instance == null) {
                    instance = new SingletonLazy();
                }
            }
        }
        return instance;
    }
}

class SingletonStatic {
    private SingletonStatic() {
        System.out.println("SingletonStatic construct");
    }
    public static class SingletonStaticHolder {
        private static final SingletonStatic INSTANCE = new SingletonStatic();
    }
    public static SingletonStatic getInstance() {
        return SingletonStaticHolder.INSTANCE;
    }
}