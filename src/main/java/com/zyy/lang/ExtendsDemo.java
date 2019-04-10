package com.zyy.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;

public class ExtendsDemo {

    @Test
    public void testSet() {
        // 继承注意多态

        // ExtendsDemoA 继承于 HashSet, 有自己  count
        ExtendsDemoA<String> set = new ExtendsDemoA<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        set.addAll(list);
        System.out.println(set.getCount()); // 6
        // HashSet 的 addAll 调用了  add 所以导致 count 数量不对
    }

}

class ExtendsDemoA<E> extends HashSet<E> {
    private static final long serialVersionUID = 1L;

    private int count;
    @Override
    public boolean add(E e) {
        count++;
        return super.add(e);
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        count += c.size();
        return super.addAll(c);
    }
    public int getCount() {
        return count;
    }
}