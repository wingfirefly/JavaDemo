package com.xxniu.classloader;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * 自定义类加载器
 * 说明: TODO
 * @author niuxinxing
 * @version
 */
public class Main {
	static class MyClassLoader extends ClassLoader {
		private String classPath;

		public MyClassLoader(String classPath) {
			this.classPath = classPath;
		}

		private byte[] loadByte(String name) throws Exception {
			name = name.replaceAll("\\.", "/");
			FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
			int len = fis.available();
			byte[] data = new byte[len];
			fis.read(data);
			fis.close();
			return data;

		}

		protected Class<?> findClass(String name) throws ClassNotFoundException {
			try {
				byte[] data = loadByte(name);
				return defineClass(name, data, 0, data.length);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ClassNotFoundException();
			}
		}

	};

	public static void main(String args[]) throws Exception {
		MyClassLoader classLoader = new MyClassLoader("E:\\eclipse\\workspace\\JavaDemo\\src");
		Class<?> clazz = classLoader.loadClass("com.xxniu.classloader.DemoObj");
		Object obj = clazz.newInstance();
		Method helloMethod = clazz.getDeclaredMethod("toString", null);
		helloMethod.invoke(obj, null);
		Main m = new Main();
		m.main();
	}
	
	public void main() {
		try {
			ClassLoader classLoader = this.getClass().getClassLoader();
			Class<?> clazz = classLoader.loadClass("com.xxniu.classloader.DemoObj");
			Object obj = clazz.newInstance();
			Method helloMethod = clazz.getDeclaredMethod("toString", null);
			helloMethod.invoke(obj, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
