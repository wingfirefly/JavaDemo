package com.zyy.coll;

import java.util.TreeSet;

import org.junit.Test;

/**
 * 树 tree中key是对象的时候,需要实现Comparable接口 TreeMap是根据key排序的:
 * 因为TreeMap是按Key来排序的，那么Key对象必须可以和另一个Key对象作比较，因此必须实现Comparable接口。
 * 可以使用String对象作为Key，因为String已经实现了Comparable接口
 * 
 * @author Shinelon
 *
 */
public class TreeDemo {

    @Test
    public void testTree() {
        TreeSet<TreeDemoA> set = new TreeSet<>();
        set.add(new TreeDemoA());
    }
}

class TreeDemoA implements Comparable<TreeDemoA> {

    @Override
    public int compareTo(TreeDemoA o) {
        return 0;
    }

}