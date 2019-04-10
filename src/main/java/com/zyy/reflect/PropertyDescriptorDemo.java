package com.zyy.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class PropertyDescriptorDemo {

    @Test
    public void testField()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field field = PropertyDescriptorDemoBean.class.getField("exist");
//        PropertyDescriptorDemoBean.class.getDeclaredField(name)
        field.setBoolean(new PropertyDescriptorDemoBean(), true);
    }

    @Test
    public void testProperty() throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        PropertyDescriptor pdAge = new PropertyDescriptor("age", PropertyDescriptorDemoBean.class);
        System.out.println(pdAge.getName());

        PropertyDescriptor pdExist = new PropertyDescriptor("exist", PropertyDescriptorDemoBean.class);
        System.err.println(pdExist.getName());

        PropertyDescriptorDemoBean bean = new PropertyDescriptorDemoBean();

        Method readMethod = pdExist.getReadMethod();
        readMethod.invoke(bean);

        Method writeMethod = pdExist.getWriteMethod();
        writeMethod.invoke(bean, false);

        PropertyDescriptor pdAa = new PropertyDescriptor("aa", PropertyDescriptorDemoBean.class);
        pdAa.getReadMethod().invoke(bean);
        pdAa.getWriteMethod().invoke(bean, "aabb");
    }
}

class PropertyDescriptorDemoBean {
    private int agex;
    private boolean exist;

    public int getAge() {
        return agex;
    }

    public void setAge(int age) {
        this.agex = age;
    }

    public boolean isExist() {
        System.out.println("boolean isExist");
        return exist;
    }

    public boolean getExist() {
        System.out.println("boolean getExist");
        return exist;
    }

    public void setExist(boolean exist) {
        System.out.println("void setExist");
        this.exist = exist;
    }

    public String getAa() {
        System.out.println("getAa");
        return "aa";
    }

    public void setAa(String aa) {
        System.out.println("setAa: " + aa);
    }
}