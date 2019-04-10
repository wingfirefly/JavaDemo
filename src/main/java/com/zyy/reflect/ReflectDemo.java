package com.zyy.reflect;

import java.util.Arrays;

import org.junit.Test;

public class ReflectDemo {

    @Test
    public void testForName() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.zyy.reflect.ReflectDemo");
        System.out.println(clazz == getClass());
        System.out.println(clazz == ReflectDemo.class);
        System.out.println(clazz);
    }

    @Test
    public void testGetClass() {
        System.out.println(ReflectDemo.class == getClass());
    }

    @Test
    public void testClassName() {
        Class<ReflectDemo> clazz = ReflectDemo.class;
        System.out.println(clazz.getName()); // com.zyy.reflect.ReflectDemo
        System.out.println(clazz.getSimpleName()); // ReflectDemo
        System.out.println(clazz.getModifiers()); // 1: Modifier.PUBLIC
    }

    @Test
    public void testClass() {
        Class<?> iClazz = int.class;
        System.out.println(iClazz.getName()); // int
        System.out.println(iClazz.getSimpleName()); // int

        Class<ReflectDemo> clazz = ReflectDemo.class;
        System.out.println(Arrays.toString(clazz.getClasses())); // ReflectDemoA ReflectDemoC

        Class<? super ReflectDemo> superclass = clazz.getSuperclass();
        System.out.println(superclass.getName()); // java.lang.Object
    }

    public class ReflectDemoA { }
    class ReflectDemoB { }
    public static class ReflectDemoC { }

    @Test
    public void testArray() {
        Class<int[]> clazzIntArr = int[].class;
        System.out.println(clazzIntArr.getName()); // [I
        System.out.println(clazzIntArr.getSimpleName()); // int[]
        System.out.println(clazzIntArr.isArray());

        Class<Object[]> clazzObjArr = Object[].class;
        System.out.println(clazzObjArr.getName()); // [Ljava.lang.Object;
        System.out.println(clazzObjArr.getSimpleName()); // Object[]
        System.out.println(clazzObjArr.isArray());

        Class<int[][]> clazzInt2Arr = int[][].class;
        System.out.println(clazzInt2Arr.getName()); // [[I
    }

    @Test
    public void testBox() {
        Class<Integer> ic = int.class;
        Class<Integer> iC = Integer.class;
        System.out.println(ic == iC); // false
    }

/*
 *   * <tr><th> Element Type <th> &nbsp;&nbsp;&nbsp; <th> Encoding
     * <tr><td> boolean      <td> &nbsp;&nbsp;&nbsp; <td align=center> Z
     * <tr><td> byte         <td> &nbsp;&nbsp;&nbsp; <td align=center> B
     * <tr><td> char         <td> &nbsp;&nbsp;&nbsp; <td align=center> C
     * <tr><td> class or interface
     *                       <td> &nbsp;&nbsp;&nbsp; <td align=center> L<i>classname</i>;
     * <tr><td> double       <td> &nbsp;&nbsp;&nbsp; <td align=center> D
     * <tr><td> float        <td> &nbsp;&nbsp;&nbsp; <td align=center> F
     * <tr><td> int          <td> &nbsp;&nbsp;&nbsp; <td align=center> I
     * <tr><td> long         <td> &nbsp;&nbsp;&nbsp; <td align=center> J
     * <tr><td> short        <td> &nbsp;&nbsp;&nbsp; <td align=center> S
 * */
}
