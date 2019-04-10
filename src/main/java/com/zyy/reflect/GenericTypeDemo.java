package com.zyy.reflect;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;

public class GenericTypeDemo {

    @Test
    public void testGenericType() {
        ZStudentDao dao = new ZStudentDao();
        dao.save("bbb");
    }
}

interface ZBaseDao<T> {
    public T get(Serializable id);

    public void save(T t);
}

class ZBaseDaoImpl<T> implements ZBaseDao<T> {

    public ZBaseDaoImpl() {
        Class<?> clazz = getClass();
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Class<?> type = (Class<?>) parameterizedType.getActualTypeArguments()[0];
            System.out.println(type);
        }
    }

    @Override
    public T get(Serializable id) {
        return null;
    }

    @Override
    public void save(T t) {
    }
}

class ZStudentDao extends ZBaseDaoImpl<String> {
}