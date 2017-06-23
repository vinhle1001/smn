package com.vinhle.smn.common;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * Created by VinhLe on 5/7/2017.
 */

public class HttpBodyHelper {

    public static String encrypt(Object data) throws JsonProcessingException {
        return JacksonHelper.getInstance().getObjectMapper().writeValueAsString(data);
    }

    public static Object decrypt(String data, Class clazz) throws IOException {
        return JacksonHelper.getInstance().getObjectMapper().readValue(data, clazz);
    }

    public static Object decrypt(byte[] data, Class clazz) throws IOException {
        return JacksonHelper.getInstance().getObjectMapper().readValue(data, clazz);
    }
}
