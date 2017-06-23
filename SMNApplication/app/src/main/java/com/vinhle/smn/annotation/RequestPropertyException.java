package com.vinhle.smn.annotation;

/**
 * Created by VinhLe on 5/22/2017.
 */

public class RequestPropertyException extends RuntimeException {

    enum Flag {
        INVALID, NOT_NULL, NOT_EMPTY /*Null&Empty*/, MIN, MAX
    }

    public Flag flag;
    public String message;
    public String field;

    public RequestPropertyException(String message, String field, Flag flag) {
        super(message);

        this.message = message;
        this.field = field;
        this.flag = flag;
    }
}
