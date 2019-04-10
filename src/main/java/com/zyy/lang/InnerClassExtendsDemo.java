package com.zyy.lang;

import org.junit.Test;

public class InnerClassExtendsDemo {

    @Test
    public void testInnerClassExtends() {
        InnerClassExtendsDemoS.X x = new InnerClassExtendsDemoC(new InnerClassExtendsDemoS());
        x.aa();
    }
}

class InnerClassExtendsDemoS {

    class A {
        public A() { }
        public A(int a) { }
    }

    static class B { }

    class C extends A {
        public C(int a) {
            super(a);
        }
    }

    class D extends B { }

    class E extends A {
        public E(int a) {
            super(a);
        }
    }

    static class F extends B { }

    // static class G extends A { } // error

    class X {
        void aa() { System.out.println("xx.aa"); }
    }
}

class InnerClassExtendsDemoC extends InnerClassExtendsDemoS.X {
    public InnerClassExtendsDemoC(InnerClassExtendsDemoS x) {
        x.super();
    }
}
