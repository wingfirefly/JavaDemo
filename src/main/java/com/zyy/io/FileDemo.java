package com.zyy.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import org.junit.Test;

public class FileDemo {

    @Test
    public void testFile() {
        System.out.println(File.separator);

        File file = new File("c:/filedemo"); // c:\\filedemo
        System.out.println(file.exists());
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());

        System.out.println(new File("").getAbsolutePath());
        System.out.println(new File("/").getAbsolutePath());
        System.out.println(new File("\\").getAbsolutePath());

        System.out.println(new File("").isFile());
        System.out.println(new File("").isDirectory());
        System.out.println(new File("/").isDirectory());

        /*
        file.length()
        file.renameTo(dest)
        file.mkdir();
        file.mkdirs();
        file.delete();
        file.getAbsolutePath();*/
    }

    @Test
    public void testList() {
        File file = new File("c:\\filedemo");
//        String[] list = file.list();
//        File[] listFiles = file.listFiles();
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".class");
            }

        };

        String[] list = file.list(filenameFilter);
        System.out.println(Arrays.toString(list));

        File[] listFiles = file.listFiles(filenameFilter);
        System.out.println(Arrays.toString(listFiles));

        File[] listFiles2 = file.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.getName().endsWith(".class") && pathname.getName().length() < 1024;
            }
        });
        System.out.println(Arrays.toString(listFiles2));
    }

    @Test
    public void testScanPath() {
        File file = new File("c:\\filedemo");
        scanPath(file);
    }

    public void scanPath(File file) {
        if (file.isFile()) {
            System.out.println(file.getName());
            return;
        }

        for (File listFile : file.listFiles()) {
            scanPath(listFile);
        }
    }

    @Test
    public void testScanCompoments() {
        String classpath = "c:\\filedemo";
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() ||
                    (pathname.getName().endsWith(".class") && !pathname.getName().contains("$"));
            }
        };

        Stack<String> stack = new Stack<>();
        stack.push(classpath);

        ArrayList<Class<?>> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            String pathname = stack.pop();

            for (File listFile : new File(pathname).listFiles(fileFilter)) {
                String listFilename = pathname + File.separatorChar + listFile.getName() ;
                if (listFile.isDirectory()) {
                    stack.push(listFilename);
                } else {
                    list.add(getClass());
                    /*Class<?> clazz = loadClass(in);
                    Controller annotation = clazz.getAnnotation(Controller.class);
                    if (annotation != null) {
                        list.add(clazz);
                    }*/
                }
            }
        }

        System.out.println(Arrays.toString(list.toArray()));
    }
}
