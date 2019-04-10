package com.zyy.generic;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ListMap<T, K, V> {
    void add(T t) { }

    void put(K k, V v) { }
    @Test
    public void test() {
        Map<String, String> map = newInstance();
        System.out.println(map);

        Map<?, ?> map2 = newInstance();
        System.out.println(map2);

        Map map3 = newInstance();
        System.out.println(map3);

        // <Object, Object>
        newInstance();

        // new ListMap<Object, Object, Object>().<String, String>newInstance();

        ListMap.<String, String> newInstance();
    }

    // 类型推导 可以减少代码 但 java7 之后的写法也简单 Map<String, String> map = new HashMap<>();
    public static <K2, V2> Map<K2, V2> newInstance() {
        return new HashMap<K2, V2>();
    }

}
