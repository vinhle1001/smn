package com.vinhle.smn.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by VinhLe on 5/6/2017.
 */
public class JacksonHelper {

    private static JacksonHelper ourInstance;
    private ObjectMapper mapper;


    public static JacksonHelper getInstance() {
        if (ourInstance == null) {
            ourInstance = new JacksonHelper();
            ourInstance.mapper = new ObjectMapper();
        }
        return ourInstance;
    }

    public ObjectMapper getObjectMapper() {
        return mapper;
    }

    private JacksonHelper() {
    }
}
