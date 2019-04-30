package com.xxniu.classloader;

import java.io.*;

/**
 * ִ��ʱ��Ҫ��·��������Ӧ��·��
 * Created by zejian on 2017/6/21.
 * Blog : http://blog.csdn.net/javazejian [ԭ�ĵ�ַ,������ԭ��]
 */
public class FileClassLoader extends ClassLoader {
    private String rootDir;

    public FileClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    /**
     * ��дfindClass�������߼�
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // ��ȡ���class�ļ��ֽ�����
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            //ֱ������class����
            return defineClass(name, classData, 0, classData.length);
        }
    }

    /**
     * ��д��ȡclass�ļ���ת��Ϊ�ֽ��������߼�
     * @param className
     * @return
     */
    private byte[] getClassData(String className) {
        // ��ȡ���ļ����ֽ�
        String path = classNameToPath(className);
        try {
            @SuppressWarnings("resource")
			InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            // ��ȡ���ļ����ֽ���
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ���ļ�����ȫ·��
     * @param className
     * @return
     */
    private String classNameToPath(String className) {
        return rootDir + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String rootDir=System.getProperty("user.dir");
        //�����Զ����ļ��������
        FileClassLoader loader = new FileClassLoader(rootDir);
        try {
            //����ָ����class�ļ�
            Class<?> object1=loader.loadClass("com.xxniu.classloader.DemoObj");
            System.out.println(object1.newInstance().toString());
            //������:I am DemoObj
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}