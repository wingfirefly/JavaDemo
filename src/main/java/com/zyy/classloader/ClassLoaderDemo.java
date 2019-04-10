package com.zyy.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

public class ClassLoaderDemo {

    @Test
    public void testSystemClassLoader() {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader.getClass().getName()); // AppClassLoader
    }

    @Test
    public void testClassLoader() {
        System.out.println(String.class.getClassLoader()); // null, why文档
        // System.out.println(netscape.javascript.JSException.class.getClassLoader()); // ExtClassLoader // windows jre
        System.out.println(ClassLoaderDemo.class.getClassLoader());
    }

    @Test
    public void testParentClassLoader() {
        ClassLoader loader = ClassLoaderDemo.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.getClass().getName());
            loader = loader.getParent();
        }
        // AppClassLoader ExtClassLoader
    }

    @Test
    public void testClassLoaderDelegate() {
        /* ClassLoaderExt extends ClassLoaderBaseEntity
         * ClassLoaderRef 引用 ClassLoaderBaseEntity
         * 将两个类导出jar, 放在jre ext目录
         */
        /* ClassLoaderBase 由 AppClassLoader 加载
           ClassLoaderExt 和 ClassLoaderRef 在ext目录, 由 ExtClassLoader 加载.
                    在加载 ClassLoaderExt 和 ClassLoaderRef 时, 会用 ExtClassLoader 加载 ClassLoaderBase,
                    而 ClassLoaderBase 由 AppClassLoader 加载, 所以报 NoClassDefFoundError
        */
        try {
            Class.forName("com.zyy.classloader.ClassLoaderExt");
        } catch (ClassNotFoundException | java.lang.NoClassDefFoundError e) {
            System.out.println("can not load ClassLoaderExt");
            System.out.println(e);
        }
        try {
            Class.forName("com.zyy.classloader.ClassLoaderRef");
        } catch (ClassNotFoundException | java.lang.NoClassDefFoundError e) {
            System.out.println("can not load ClassLoaderRef");
            System.out.println(e);
        }
    }

    @Test
    public void testResource() {
        printResouce(""); // /bin/com/zyy/classloader/
        printResouce("/"); // /bin/
        printResouce("file.txt");
        printResouce("/com/zyy/classloader/file.txt");

        System.out.println("-------------------");

        printClassLoaderResouce(""); // /bin/
        printClassLoaderResouce("/"); // null
        printClassLoaderResouce("com/zyy/classloader/file.txt");
        printClassLoaderResouce("/com/zyy/classloader/file.txt"); // null
    }

    private void printResouce(String name) {
        URL url = getClass().getResource(name);
        System.out.println(url);
    }

    private void printClassLoaderResouce(String name) {
        URL url = getClass().getClassLoader().getResource(name);
        System.out.println(url);
    }

}

// 自己写 ClassLoader
class WebContextClassLoader extends ClassLoader {

    public WebContextClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class<?> load(String path) {
        FileInputStream in = null;
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            in = new FileInputStream(path);
            int len = 0;
            byte buffer[] = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
                bout.write(buffer, 0, len);
            }
            byte b[] = bout.toByteArray();
            return super.defineClass(null, b, 0, b.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
