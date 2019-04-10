package com.zyy.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;
/**
 * 使用二进制读写
 * @author Shinelon
 *
 */
public class SerializableDemo {

    @Test
    public void testWriter() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\work\\Desktop\\a.hobj"));
            SerializableDemoA obj = new SerializableDemoA(111, "aaa");
            out.writeObject(obj);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\work\\Desktop\\a.hobj"));
            SerializableDemoA obj = (SerializableDemoA) in.readObject();
            System.out.println(obj);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class SerializableDemoA implements Serializable {

    private static final long serialVersionUID = 7324467025273830961L;

    private int id;
    private transient String name;

    public SerializableDemoA(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("writeObject");
        out.defaultWriteObject(); // 序列化所有非 transient 字段
        out.writeObject(name); // 序列化 transient 字段
    }

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        System.out.println("readObject");
        in.defaultReadObject(); // 反序列化所有非 transient 字段
        name = (String) in.readObject(); // 反序列化 transient 字段
    }

    private Object readResolve() {
        System.out.println("readResolve");
        return new SerializableDemoA(2, "aaa");
    }

    @Override
    public String toString() {
        return "SerializableDemoA [id=" + id + ", name=" + name + "]";
    }

}
