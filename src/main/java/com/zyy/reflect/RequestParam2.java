package com.zyy.reflect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam2 {

    String value();

}
