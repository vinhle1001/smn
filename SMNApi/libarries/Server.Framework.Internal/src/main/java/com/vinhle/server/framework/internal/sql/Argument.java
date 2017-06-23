package com.vinhle.server.framework.internal.sql;

/**
 * Created by VinhLe on 4/21/2017.
 */
public class Argument {

    enum Mode {IN, OUT, INOUT}

    private int index;
    private String parameterName;
    private Object value;
    private Class type;
    private Mode mode;

    public Argument(int index, Object value, Class type) {
        this (index, (String)null, value, type, Mode.IN);
    }

    public Argument(String parameterName, Object value, Class type) {
        this (-1, parameterName, value, type, Mode.IN);
    }

    public Argument(int index, Object value, Class type, Mode mode) {
        this(index, (String)null, value, type, mode);
    }

    public Argument(String parameterName, Object value, Class type, Mode mode) {
        this(-1, parameterName, value, type, mode);
    }

    protected Argument(int index, String parameterName, Object value, Class type, Mode mode) {
        this.index = index;
        this.parameterName = parameterName;
        this.value = value;
        this.type = type;
        this.mode = mode;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public boolean hasArgumentName() {
        return parameterName != null && !parameterName.isEmpty();
    }
}
