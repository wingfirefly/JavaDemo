package com.zyy.reflect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// @Target({ ElementType.PARAMETER, ElementType.TYPE }) // is ok
// @Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {

    String value();

}
