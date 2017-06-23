package com.vinhle.smn.api.springconfig.resolver;

import java.lang.annotation.*;

/**
 * Created by nguyenthuantan on 9/22/16.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonBody {
    String defaultValue() default "{}";
}
