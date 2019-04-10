package com.zyy.enumanno;

public enum IntDemo {
    A {
        @Override
        public String toString() {
            return "a";
        }
    }, B, C, D;

    public static void main(String[] args) {
        System.out.println(A); // a
        System.out.println(B); // B
    }
}
