package com.zyy.proxy;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

public class ZyyProxy {

    private static final AtomicLong nextUniqueNumber = new AtomicLong();

    private static final String lineSeparator = System.getProperty("line.separator");

    private static final String packageName = "com.zyy.zproxy";

    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
            throws IllegalArgumentException {
        if (interfaces == null || interfaces.length != 1) {
            throw new IllegalArgumentException("length of interfaces must be 1");
        }

        if (h == null) {
            throw new NullPointerException();
        }

        // inner class
        try {
            Class<?> interfaceClass = Class.forName(interfaces[0].getName(), false, loader);

            String imports = getImports();
            String proxyClassName = getProxyClassName();
            String fields = getFields();
            String constructor = getConstructor(proxyClassName, interfaceClass.getName());
            String methods = getMethods(interfaceClass.getMethods());

            // extends Proxy
            String code = String.format("package %s;" + repeat(lineSeparator, 2)
                        + "%s" + lineSeparator
                        + "public class %s implements %s {" + lineSeparator
                        + "%s%s%s}",
                    packageName, imports, proxyClassName, interfaceClass.getName(),
                    fields, constructor, methods);

            boolean success = compile(code, proxyClassName);
            FileWriter fw = new FileWriter("C:/java/bin/" + proxyClassName + ".java");
            fw.write(code);
            fw.close();
            if (success) {
                URL[] urls = new URL[] { new URL("file:///C:/java/bin/") };
                URLClassLoader classLoader = new URLClassLoader(urls);
//                Class<?> clazz = loader.loadClass(packageName + "." + proxyClassName); // class not found
                Class<?> clazz = classLoader.loadClass(packageName + "." + proxyClassName);
                Constructor<?> cons = clazz.getConstructor(InvocationHandler.class);
                classLoader.close();
                return cons.newInstance(h);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static boolean compile(String code, String className) {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        JavaFileObject fileObject = new JavaStringObject(className, code);
        CompilationTask task = javaCompiler.getTask(null, null, null, Arrays.asList("-d","C:/java/bin/"), null, Arrays.asList(fileObject));
        return task.call();
    }

    private static class JavaStringObject extends SimpleJavaFileObject {
        private String content;

        public JavaStringObject(String _javaFileName, String _content) {
            super(createStringJavaObjectUri(_javaFileName), Kind.SOURCE);
            content = _content;
        }

        private static URI createStringJavaObjectUri(String name) {
            return URI.create("String:///" + name + Kind.SOURCE.extension);
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return content;
        }
    }

    private static String getImports() {
        return "import java.lang.reflect.InvocationHandler;" + lineSeparator
                + "import java.lang.reflect.Method;" + lineSeparator
                + "import java.lang.reflect.UndeclaredThrowableException;" + lineSeparator;
    }

    private synchronized static String getProxyClassName() {
        return "$Proxy" + nextUniqueNumber.incrementAndGet();
    }

    private static String getConstructor(String proxyClassName, String className) {
        return String.format("public %s(InvocationHandler h) {" + lineSeparator
               + "this.h = h;" + lineSeparator
               + "if (methods == null) {" + lineSeparator
               + "try { methods = Class.forName(\"%s\").getMethods(); } "
               + "catch (java.lang.ClassNotFoundException e) { throw new UndeclaredThrowableException(e); }" + lineSeparator
               + "}" + lineSeparator
               + "}" + lineSeparator, proxyClassName, className);
    }

    private static String getFields() {
        return "private InvocationHandler h;" + lineSeparator +
               "private static Method[] methods;" + lineSeparator;
    }

    private static String getMethods(Method[] methods) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<methods.length; i++) {
            String methodCode = getMethod(methods[i], i);
            sb.append(methodCode);
            sb.append(lineSeparator);
        }
        return sb.toString();
    }

    private static String getMethod(Method method, int index) {
        String returnType = getType(method.getReturnType());
        List<String> argsTypeList = getArgsTypeList(method);
        String args = getArgs(argsTypeList);
        return String.format("public %s %s(%s) {"+ lineSeparator +"%s}" + lineSeparator,
                returnType, method.getName(), args,
                getBody(returnType, argsTypeList, index));
    }

    private static List<String> getArgsTypeList(Method method) {
        ArrayList<String> list = new ArrayList<>();
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (Class<?> parameterType : parameterTypes) {
            list.add(getType(parameterType));
        }
        return list;
    }

    private static String getArgs(List<String> argsTypeList) {
        StringBuilder args = new StringBuilder();
        int argsCount = 0;
        for (String type : argsTypeList) {
            args.append(String.format("%s arg%d, ", type, argsCount++));
        }
        if (argsTypeList.size() > 0) {
            args.setLength(args.length() - 2);
        }
        return args.toString();
    }

    private static String getBody(String returnType, List<String> argsTypeList, int index) {
        String argsValue = getArgsValue(argsTypeList.size());
        boolean isReturnVoid = returnType.equals("void");
        return String.format("try { %sh.invoke(this, methods[%d], %s); }" + lineSeparator
                + "catch (Error | RuntimeException v0) { throw v0; }" + lineSeparator
                + "catch (Throwable var2_2) { throw new UndeclaredThrowableException(var2_2); }" + lineSeparator,
                isReturnVoid ? "" : String.format("return (%s)", returnType), index, argsValue);
    }

    private static String getArgsValue(int amount) {
        if (amount == 0) {
            return "null";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("new Object[] { ");
        for (int i=0; i<amount; i++) {
            sb.append("arg" + i + ", ");
        }
        sb.setLength(sb.length()-2);
        sb.append(" }");
        return sb.toString();
    }

    private static String getType(Class<?> clazz) {
        String name = clazz.getName();

        // int index = name.lastIndexOf('[');
        // if (chs[index+1] != 'L') return clazz.getSimpleName();
        if (name.matches("\\[+L.+")) {
            return name.substring(name.lastIndexOf('[')+2, name.length()-1) + getArray(clazz);
        }

        if (name.matches("\\[+[ZBCDFIJS]")) {
            return clazz.getSimpleName();
        }
        return name;
    }

    private static String getArray(Class<?> clazz) {
        String name = clazz.getName();
        StringBuilder sb = new StringBuilder();
        for (char ch : name.toCharArray()) {
            if (ch == '[') {
                sb.append("[]");
            } else {
                break;
            }
        }
        return sb.toString();
    }

    private static String repeat(String str, int time) {
        StringBuilder sb = new StringBuilder();
        while (time > 0) {
            sb.append(str);
            time--;
        }
        return sb.toString();
    }

}
