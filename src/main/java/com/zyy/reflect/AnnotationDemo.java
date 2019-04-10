package com.zyy.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

public class AnnotationDemo {

    @Test
    public void testAnnonParam() {
        Class<AnnotationDemoController> clazz = AnnotationDemoController.class;
        try {
            Method method = clazz.getMethod("list", String.class);
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                for (Annotation annotation : parameterAnnotations[i]) {
                    Class<? extends Annotation> annotationType = annotation.annotationType();
                    System.out.println(annotationType.getName());
                }
            }
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAnnonExtends() throws NoSuchMethodException, SecurityException {
        Class<AnnotationDemoB> clazz = AnnotationDemoB.class;

        // annotions size is 0

        Annotation[] classAnnos = clazz.getAnnotations();
        System.out.println(Arrays.toString(classAnnos));

        Method method = clazz.getMethod("add");
        Annotation[] methodAnnos = method.getAnnotations();
        System.out.println(Arrays.toString(methodAnnos));
    }
}

class AnnotationDemoController {
    public void list(@RequestParam("name") @RequestParam2("name") String name) {
    }
}

@RequestParam("class")
interface AnnotationDemoA {
    @RequestParam("method_aa")
    void add();
}

class AnnotationDemoB implements AnnotationDemoA {

    @Override
    public void add() {
    }
}
