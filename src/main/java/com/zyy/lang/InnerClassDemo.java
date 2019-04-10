package com.zyy.lang;

public class InnerClassDemo {

    @Override
    public boolean equals(Object obj) {
        return false;
    }

}

class A {
    private int a;
    private static int b;

    class AA {
        public void aa() {
            System.out.println(a);
            System.out.println(A.this.a);
            System.out.println(b);
            System.out.println(A.b);
        }
        // public static void bb() { } // can not be static, can not AA.bb
    }

    public void bb() {
        AA objA = new AA();
        objA.aa();
    }
}

class B {
    int a;
    private static int b;

    static class BB {
        public void aa() {
            // System.out.println(a); Cannot make a static reference to the
            // non-static field
            System.out.println(b);
            System.out.println(B.b);
        }

        public static void bb() {
            // System.out.println(a); Cannot make a static reference to the
            // non-static field
            System.out.println(b);
            System.out.println(B.b);
        }
    }

    public void bb() {
        BB objB = new BB();
        objB.aa();
        BB.bb();
    }
}