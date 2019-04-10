package com.zyy.enumanno;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 注解RequestParam的原理
 * @author Shinelon
 *
 */
//@Target({ ElementType.PARAMETER, ElementType.TYPE }) // is ok
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    // String value();
    String value() default "asc";

    String name();
}

class BaseController {

    RequestParam param = new RequestParam() {

        @Override
        public Class<? extends Annotation> annotationType() {
            return null;
        }

        @Override
        public String value() {
            return null;
        }

        @Override
        public String name() {
            return null;
        }
    };

    public void list(@RequestParam(name = "") String sort) {

    }
}