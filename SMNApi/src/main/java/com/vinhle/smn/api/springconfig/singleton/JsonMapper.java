package com.vinhle.smn.api.springconfig.singleton;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {

    private final static ObjectMapper mapper = new ObjectMapper();

    public static ObjectMapper getInstant() {
        return mapper;
    }

}
