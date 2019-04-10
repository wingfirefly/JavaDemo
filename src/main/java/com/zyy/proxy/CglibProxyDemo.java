package com.zyy.proxy;

import org.junit.Test;

public class CglibProxyDemo {

    // compile group: 'cglib', name: 'cglib', version: '3.2.4'

    @Test
    public void testCreate() {
        // public final class
        // interface is ok
        /*Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(StudentServiceImpl.class);
        enhancer.setCallback(new MethodInterceptor () {

            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println(method.getName());
                System.out.println(Arrays.toString(args));
                return null;
            }
        });
        StudentService service = (StudentService) enhancer.create();
        System.out.println(service);
        service.add();*/
    }
}
