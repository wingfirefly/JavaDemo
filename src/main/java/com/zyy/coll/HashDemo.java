package com.zyy.coll;

import java.util.HashMap;

import org.junit.Test;

/**
 * HashMap 如果HashMap中的key使用的是自定义的类对象，那么需要遵守equals()与hashCode()规范. 默认的 hashCode()
 * 方法实现对每个不同的对象返回不同的整数. 默认的 equals() 方法只比较两个引用是否指向同一个实际对象
 * 
 * @author Shinelon
 *
 */
public class HashDemo {

    @Test
    public void testNull() {
        HashMap<String, String> map = new HashMap<>();
        map.put(null, "zhangsan");
        map.put(null, "zhangsan2");
        map.put("name", null);

        String name = map.get(null);
        System.out.println(name); // zhangsan2

        name = map.get("name");
        System.out.println(name);

//        Hashtable<String, String> table = new Hashtable<String, String>();
//        table.put(null, "zhangsan");  // NullPointerException
//        table.put("name", null); // NullPointerException
    }

    @Test
    public void testHash() {
        HashMap<HashDemoA, String> map = new HashMap<>();

        HashDemoA key1 = new HashDemoA(1, "111");
        HashDemoA key2 = new HashDemoA(2, "222");
        HashDemoA key3 = new HashDemoA(1, "111");

        map.put(key1, "111");
        map.put(key2, "222");
        map.put(key3, "1111");

        System.out.println("start get....");

        key1.age++;
        key2.age++;
        key3.age++;

        String value1 = map.get(key1);
        String value2 = map.get(key2);
        String value3 = map.get(key3);

        System.out.println(key1.name + " : " + value1);
        System.out.println(key2.name + " : " + value2);
        System.out.println(key3.name + " : " + value3);
    }
}

/**
 * 自定义对象做HashMap的key的时候，需要实现hashCode方法和equals方法，而且返回的int值尽量随机，不然HashMap通过hash计算后得到的值过于集中，那么就会导致HashMap的效率过低
 * 在HashMap取值的时候，会
 * @author Shinelon
 *
 */
class HashDemoA {
    public int age;
    public String name;
    public HashDemoA(int age, String name) {
        this.age = age;
        this.name = name;
    }
    @Override
    public int hashCode() {
        System.out.println("hashCode " + name);
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        System.out.println("equals " + name);
        //return true;
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HashDemoA other = (HashDemoA) obj;
        if (age != other.age)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}