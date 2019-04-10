package com.zyy.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class DynamicProxyDemo {

    @Test
    public void testGetProxyClass() {
        @SuppressWarnings("unchecked")
        Class<Collection<String>> clazz = (Class<Collection<String>>) Proxy.getProxyClass(
                Collection.class.getClassLoader(),
                new Class[] { Collection.class });
        System.out.println(clazz.getName());

        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            // [interface java.lang.reflect.InvocationHandler]
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            System.out.println(Arrays.toString(parameterTypes));
        }
    }

    Object proxy2;
    @Test
    public void testNewProxyInstance() {
        @SuppressWarnings("unchecked")
        Collection<String> coll = (Collection<String>) Proxy.newProxyInstance(Collection.class.getClassLoader(),
                new Class[] { Collection.class },
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        proxy2 = proxy;
                        System.out.println(args);
                        System.out.println(method.getName());
                        return null;
                    }
                });
        // hashCode toString equals
        System.out.println(coll.getClass().getClassLoader());

        System.out.println(coll);
        // System.out.println(coll.equals(null));
//         System.out.println(coll.hashCode());
//        System.out.println(coll.size()); // Integer a = null; int b = a;
        System.out.println(coll == proxy2); // true
    }

    public static void main(String[] args) {
        new DynamicProxyDemo().testProxyName(); // $Proxy0 从0开始
    }

    @Test
    public void testProxyName() {
        System.out.println(getProxy(Collection.class.getClassLoader(), List.class).getClass().getName()); // $Proxy{num} 不是从0开始 junit
        System.out.println(getProxy(Collection.class.getClassLoader(), Set.class).getClass().getName());
//        System.out.println(getProxy(Collection.class.getClassLoader(), ArrayList.class).getClass().getName()); // not ok
    }

    @Test
    public void testCache() {
        @SuppressWarnings("unchecked")
        Class<Runnable> clazz = (Class<Runnable>) Proxy.getProxyClass(
                Runnable.class.getClassLoader(),
                new Class[] { Runnable.class });

        @SuppressWarnings("unchecked")
        Class<Runnable> clazz2 = (Class<Runnable>) Proxy.getProxyClass(
                Runnable.class.getClassLoader(),
                new Class[] { Runnable.class });

        Runnable runnableProxy =  (Runnable) Proxy.newProxyInstance(
                Runnable.class.getClassLoader(),
            new Class[] { Runnable.class },
            new InvocationHandler() {

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return null;
                }
            }
        );

        System.out.println(clazz.getName());
        System.out.println(clazz == clazz2);
        System.out.println(clazz == runnableProxy.getClass());
    }

    private Object getProxy(ClassLoader loader, Class<?> ...interfaces) {
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        };
        return Proxy.newProxyInstance(loader, interfaces, h);
    }

    @Test
    public void testMultipleInterface() {
        // methods with same signature aa() but incompatible return types: void and others
        System.out.println(getProxy(DaynamicProxyDemoI1.class.getClassLoader(), DaynamicProxyDemoI1.class, DaynamicProxyDemoI2.class).getClass().getName());
    }
}

interface DaynamicProxyDemoI1 {
    void aa();
}

interface DaynamicProxyDemoI2 {
    int aa();
}
