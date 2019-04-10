package com.zyy.generic;

import java.util.HashMap;
import java.util.Map;

public class TypeInferenceDemo {

    // 通过 Class 对象创建一个实例
    public <T> T getMap(Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    // 类型推导
    public <K, V> Map<K, V> newMapInstance() {
        return new HashMap<K, V>();
    }
}
