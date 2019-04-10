package com.zyy.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import org.junit.Test;

public class GenericDemo {

    @Test
    public void testGeneric() {
        Class<?> clazz = GenericDemoListMap.class;
        try {
            Method addMethod = clazz.getMethod("add2", GenericDemoA.class, Object.class);
            Type[] genericParameterTypes = addMethod.getGenericParameterTypes();
            System.out.println("genericParameterTypes");
            for (Type type : genericParameterTypes) {
                System.out.println(type.getClass().getName());
            }
            TypeVariable<Method>[] typeParameters = addMethod.getTypeParameters();
            System.out.println("typeParameters");
            for (TypeVariable<Method> typeParameter : typeParameters) {
                System.out.println(typeParameter.getName());
            }
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }
}

class GenericDemoListMap<E, K, V> {
    public void add(E e) { }
    public void put(K k, V v) { }
    public <T extends GenericDemoA> void add2(T t, K k) { }
}

class GenericDemoA {

}