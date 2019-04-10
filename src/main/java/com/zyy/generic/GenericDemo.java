package com.zyy.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class GenericDemo {

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
    public void testDemo1() {
        ArrayList<String> sList = new ArrayList<String>();
        sList.add("zhangsan");
        ArrayList list = sList;
        ArrayList<Integer> iList = list;
        // ArrayList<Integer> iList = sList;
        System.out.println(iList.get(0));
        // iList 实际装的是 String
        // java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
        for (Integer i : iList) { System.out.println(i); }
    }

    @Test
    public void testRawType() {
        // 实际放的类型不只 String
        List<Object> list = getRowTypeList();
        String str = (String) list.get(0);
        System.out.println(str);
    }

    private List<Object> getRowTypeList() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(2323);
        list.add("111");
        return list;
    }

    @Test
    public void testDemo2() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        // 绕过泛型检查
        toutouAdd(list, "aaaa");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void toutouAdd(List list, Object o) {
        list.add(o);
    }

    @Test
    public void testNoRawType() {
        // 尽量具体类型  不要用 <Object>
        ArrayList<Object> list =new ArrayList<Object>();
        list.add(232);
        list.add("aaa");

//        List<String> sList = list;
    }

    @Test
    public void testIntersection() {
        HashSet<String> set1 = new HashSet<String>();
        HashSet<String> set2 = new HashSet<String>();
        set1.add("aaa");
        set1.add("bbb");
        set2.add("aaa");
        int count = intersection(set1, set2);
        System.out.println(count);

        HashSet<Long> set3 = new HashSet<Long>();
        HashSet<Long> set4 = new HashSet<Long>();
        set3.add(111L);
        set3.add(222L);
        set4.add(111L);
        set4.add(222L);
        count = intersection(set3, set4);
        System.out.println(count);
    }

    /**
     * 求两个集合交集的个数
     * Set<?> 巧妙的解决了集合里不同类型参数的问题
     */
    public int intersection(Set<?> set1, Set<?> set2) {
        int count = 0;
        for (Object obj : set1) {
            if (set2.contains(obj)) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void testGeneric() {
        // String 或者 String 的父类
        List<? super String> list = new ArrayList<>();
        list.add("sf");
//        String str = list.get(0);
        Object obj = list.get(0);
        String str = (String) obj;
        System.out.println(str);
    }

    @Test
    public void testArr() {
//        ArrayList<Object> list = new ArrayList<Long>(); // 编译时就能检测出来

//        Object[] arr = new Long[20];
//        arr[0] = 2L;
//        arr[1] = "aa";

        // 集合转 数组的用法
        ArrayList<String> list = new ArrayList<String>();
        list.add("xiyang");
        Object[] strs = list.toArray();
//        String[] strs = (String[]) list.toArray();
        String[] arr = new String[list.size()];
        String[] array = list.toArray(arr);

        System.out.println(arr == array); // true

        System.out.println(strs.length);
        System.out.println(array.length);
    }

    @Test
    public void testArrEx() {
        Class<? super String[]> clazz = String[].class.getSuperclass();
        System.out.println(clazz.getName()); // java.lang.Object
        System.out.println(clazz.isArray()); // false
    }

    @Test
    public void testAsList() {
        // 可变参数 基本数据类型
        // asList(T... a)
        // size 不同

        int[] a = { 1, 2, 3, 4, 5, 6 }; // Integer[] a error
        List<int[]> ilist = Arrays.asList(a); // 基本数据数组类型 把参数当作 int[]... a
        System.out.println(ilist.size()); // 1

        String[] str = { "xiyang1", "xiyang2", "xiyang3" };
        List<String> sList = Arrays.asList(str); // 引用数据类型数组 把参数当作 String... a
        System.out.println(sList.size()); // 3

        float[] f = { 1.0f, 2.0f };
        Arrays.asList(f);
    }

}
