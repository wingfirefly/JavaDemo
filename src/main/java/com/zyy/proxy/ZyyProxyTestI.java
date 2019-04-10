package com.zyy.proxy;

public interface ZyyProxyTestI {
    void add();

    void add(ZyyProxyTestE e, ZyyProxyTestE2 e2);
    void add(ZyyProxyTestE[] e);
    void add(boolean age);
    void add(byte age);
    String add(char age);
    void add(String name);

    void add(double age);
    Object add(float age);
    long add(int age);
    void add(long age);
    void add(short age);

    String[][][] add2(int[] age);
    void add(Object ...age);
    void add(String[] args);
    void add(String[][] args);
    void add(String[][][] args);
/*    void add(A a);
    class A {
    }*/
}

enum ZyyProxyTestE {
}
enum ZyyProxyTestE2 {
}
