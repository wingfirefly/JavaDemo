package com.zyy.coll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.junit.Test;

/**
 * 对于类对象的排序，该类必须要实现Comparator接口
 * @author Shinelon
 *
 */
public class CollsDemo {

    @Test
    public void testSortComparable() {
        ArrayList<CollsDemoA> list = new ArrayList<>();
        list.add(new CollsDemoA(1));
        list.add(new CollsDemoA(6));
        list.add(new CollsDemoA(3));
        list.add(new CollsDemoA(8));
        list.add(new CollsDemoA(2));
        Collections.sort(list);
        String result = Arrays.toString(list.toArray());
        System.out.println(result);
    }

    @Test
    public void testSortComparator() {
        ArrayList<CollsDemoA> list = new ArrayList<>();
        list.add(new CollsDemoA(1));
        list.add(new CollsDemoA(6));
        list.add(new CollsDemoA(3));
        list.add(new CollsDemoA(8));
        list.add(new CollsDemoA(2));
        Collections.sort(list, new CollsDemoComparator());
        String result = Arrays.toString(list.toArray());
        System.out.println(result);
    }

    @Test
    public void testSortComparator2() {
        ArrayList<CollsDemoA> list = new ArrayList<>();
        list.add(new CollsDemoA(1));
        list.add(new CollsDemoA(6));
        list.add(new CollsDemoA(3));
        list.add(new CollsDemoA(8));
        list.add(new CollsDemoA(2));
        // 假如有了一个不能改的比较器, 但想要逆序可以这样来
        Collections.sort(list, new CollsDemoComparator2(new CollsDemoComparator()));
//        Collections.sort(list, new CollsDemoComparator().reversed()); // is ok
        String result = Arrays.toString(list.toArray());
        System.out.println(result);
    }

    @Test
    public void testShuffle() {
        // 打散 随机
        // Collections.shuffle(list);
    }
}

class CollsDemoA implements Comparable<CollsDemoA>  {

    private int age;

    public CollsDemoA(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(CollsDemoA o) {
        return age - o.age;
    }

    @Override
    public String toString() {
        return String.valueOf(age);
    }
}

class CollsDemoComparator implements Comparator<CollsDemoA> {

    @Override
    public int compare(CollsDemoA o1, CollsDemoA o2) {
        // if( d[j-1].compareTo(d[j]) > 0) swap
        return o2.getAge() - o1.getAge();
    }

}

class CollsDemoComparator2 implements Comparator<CollsDemoA> {

    private Comparator<CollsDemoA> comparator;

    public CollsDemoComparator2(Comparator<CollsDemoA> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(CollsDemoA o1, CollsDemoA o2) {
        return -comparator.compare(o1, o2);
    }

}
