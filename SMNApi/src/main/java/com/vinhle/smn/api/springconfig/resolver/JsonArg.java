package com.vinhle.smn.api.springconfig.resolver;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.ValueConstants;

import java.lang.annotation.*;

/**
 * Created by nguyenthuantan on 9/22/16.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonArg {
    @AliasFor("value")
    String name() default "";

    @AliasFor("name")
    String value() default "";

    String defaultValue() default ValueConstants.DEFAULT_NONE;
}
