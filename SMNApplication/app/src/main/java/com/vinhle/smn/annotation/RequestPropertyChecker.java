package com.vinhle.smn.annotation;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by VinhLe on 5/22/2017.
 */
public class RequestPropertyChecker {

    public static boolean CheckRequestProperty(Object obj) {

        if (obj == null || obj.getClass() == Integer.class
                || obj.getClass() == Boolean.class
                || obj.getClass() == Float.class
                || obj.getClass() == Double.class
                || obj.getClass() == String.class) return Boolean.TRUE;

        try {
            for (final Method method : obj.getClass().getDeclaredMethods()) {
                if (!method.getName().startsWith("get") || method.getParameterTypes().length > 0)
                    continue;
//                method.setAccessible(true);
                Object value = method.invoke(obj);
                RequestProperty property = method.getAnnotation(RequestProperty.class);
                if (property != null) {
                    if (property.checkNullAndEmpty()) {
                        if (value == null || (value instanceof String && ((String) value).isEmpty()))
                            throw new RequestPropertyException(property.contentRequired(), property.fieldRequired(), RequestPropertyException.Flag.NOT_EMPTY);
                    } else if (property.checkNull()) {
                        if (value == null)
                            throw new RequestPropertyException(property.contentRequired(), property.fieldRequired(), RequestPropertyException.Flag.NOT_NULL);
                    } else if (!property.pattern().equals("")) {
                        if (value == null || (value instanceof String && Pattern.matches(property.pattern(), (CharSequence) value)))
                            throw new RequestPropertyException(property.contentRequired(), property.fieldRequired(), RequestPropertyException.Flag.INVALID);
                    } else if (property.minLength() > -1) {
                        if (value == null || (value instanceof String && ((String) value).length() < property.minLength()))
                            throw new RequestPropertyException(property.contentRequired(), property.fieldRequired(), RequestPropertyException.Flag.MIN);
                    } else if (property.maxLength() > -1) {
                        if (value == null || (value instanceof String && ((String) value).length() > property.maxLength()))
                            throw new RequestPropertyException(property.contentRequired(), property.fieldRequired(), RequestPropertyException.Flag.MAX);
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.e("RequestPropertyChecker", e.getMessage(), e);
        }
        return Boolean.TRUE;
    }

    public static List GetErrorRequestProperty(Object obj) {
        if (obj == null || obj.getClass() == Integer.class
                || obj.getClass() == Boolean.class
                || obj.getClass() == Float.class
                || obj.getClass() == Double.class
                || obj.getClass() == String.class) return new ArrayList();

        List<RequestPropertyException> exceptions = new ArrayList<>();

        try {
            for (final Method method : obj.getClass().getDeclaredMethods()) {
                if (!method.getName().startsWith("get") || method.getParameterTypes().length > 0)
                    continue;
//                method.setAccessible(true);
                Object value = method.invoke(obj);
                RequestProperty property = method.getAnnotation(RequestProperty.class);
                if (property != null) {
                    if (property.checkNullAndEmpty()) {
                        if (value == null || (value instanceof String && ((String) value).isEmpty()))
                            exceptions.add(new RequestPropertyException(property.contentRequired(), property.fieldRequired(), RequestPropertyException.Flag.NOT_EMPTY));
                    } else if (property.checkNull()) {
                        if (value == null)
                            exceptions.add(new RequestPropertyException(property.contentRequired(), property.fieldRequired(), RequestPropertyException.Flag.NOT_NULL));
                    } else if (!property.pattern().equals("")) {
                        if (value == null || (value instanceof String && !((String) value).isEmpty() && !Pattern.matches(property.pattern(), (CharSequence) value)))
                            exceptions.add(new RequestPropertyException(property.contentRequired(), property.fieldRequired(), RequestPropertyException.Flag.INVALID));
                    } else if (property.minLength() > -1) {
                        if (value == null || (value instanceof String && ((String) value).length() < property.minLength()))
                            exceptions.add(new RequestPropertyException(property.contentRequired(), property.fieldRequired(), RequestPropertyException.Flag.MIN));
                    } else if (property.maxLength() > -1) {
                        if (value == null || (value instanceof String && ((String) value).length() > property.maxLength()))
                            exceptions.add(new RequestPropertyException(property.contentRequired(), property.fieldRequired(), RequestPropertyException.Flag.MAX));
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.e("RequestPropertyChecker", e.getMessage(), e);
        }
        return exceptions;
    }

}
