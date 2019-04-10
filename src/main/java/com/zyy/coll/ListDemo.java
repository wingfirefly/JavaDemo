package com.zyy.coll;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
/**
 * ArrayList泛型的应用
 * @author Shinelon
 *
 */
public class ListDemo {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testRemove() {
        ArrayList<String> list = new ArrayList<>();
        list.add("abc123");
        list.add("abd456");
        list.add("abc789");
        remove(list, "abc");

        ArrayList<String> list2 = new ArrayList<String>();
        ArrayList list3 = list2;
        ArrayList<Long> list4 = list3;

        list2.add("2");
        Long l = list4.get(0);// 这里会报错，因为泛型只能兼容自动类型转换，需要强转的类型，用泛型进行转换时会报类型转换异常
        System.out.println(l);
    }

    public void remove(List<String> list, String prefix) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String str = it.next();
            if (str.startsWith(prefix)) {
                it.remove();
            }
        }
        System.out.println(list.size());
        ArrayList<String> removedList = new ArrayList<>();
        for (String str : list) {
            if (str.startsWith(prefix)) {
                removedList.add(str);
            }
        }
        list.removeAll(removedList);
        System.out.println(list.size());
    }
}
