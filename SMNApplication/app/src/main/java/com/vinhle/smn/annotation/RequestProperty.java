package com.vinhle.smn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by VinhLe on 5/22/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface RequestProperty {

    int minLength() default -1;

    int maxLength() default -1;

    boolean checkNull() default false;

    boolean checkNullAndEmpty() default false;

    String contentRequired() default "";

    String fieldRequired() default "";

    String pattern() default "";

}
