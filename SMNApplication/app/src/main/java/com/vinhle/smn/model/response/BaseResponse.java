package com.vinhle.smn.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/6/2017.
 */
public abstract class BaseResponse implements Serializable {

    @JsonProperty("code")
    public int code;
    @JsonProperty("message")
    private String message;

}
