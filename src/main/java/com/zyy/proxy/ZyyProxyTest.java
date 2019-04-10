package com.zyy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

public class ZyyProxyTest {

    @Test
    public void testProxyClass() {
        ZyyProxyTestI target = new ZyyPRoxyTestIImpl();
        InvocationHandler h = new ZyyProxyTestInvocationHandler(target /*advice*/);

        ZyyProxyTestI proxy = (ZyyProxyTestI) ZyyProxy.newProxyInstance(ZyyProxyTestI.class.getClassLoader(),
                new Class[] { ZyyProxyTestI.class }, h);

        System.out.println(proxy);
        System.out.println(proxy.add(2));
        System.out.println(proxy.add(2f));
        proxy.add("sfs", "fsf");
    }

}

class ZyyProxyTestInvocationHandler implements InvocationHandler {
    private ZyyProxyTestI target;
    public ZyyProxyTestInvocationHandler(ZyyProxyTestI target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        System.out.println(Arrays.toString(args));
        return method.invoke(target, args);
    }
}

class ZyyPRoxyTestIImpl implements ZyyProxyTestI {
    @Override
    public void add() { }
    @Override
    public void add(ZyyProxyTestE e, ZyyProxyTestE2 e2) { }
    @Override
    public void add(ZyyProxyTestE[] e) { }
    @Override
    public void add(boolean age) { }
    @Override
    public void add(byte age) { }
    @Override
    public String add(char age) {
        return null;
    }
    @Override
    public void add(String name) {
    }
    @Override
    public void add(double age) {
    }
    @Override
    public Object add(float age) {
        return null;
    }
    @Override
    public long add(int age) {
        return 0;
    }
    @Override
    public void add(long age) { }
    @Override
    public void add(short age) { }
    @Override
    public String[][][] add2(int[] age) {
        return null;
    }
    @Override
    public void add(Object... age) { }
    @Override
    public void add(String[] args) { }
    @Override
    public void add(String[][] args) { }
    @Override
    public void add(String[][][] args) { }
}