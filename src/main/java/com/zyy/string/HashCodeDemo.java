package com.zyy.string;

import org.junit.Test;

public class HashCodeDemo {

    @Test
    public void testSameHashCode() {
        String a = "\u0002\u0020";
        String b = "\u0003\u0001";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }
}
