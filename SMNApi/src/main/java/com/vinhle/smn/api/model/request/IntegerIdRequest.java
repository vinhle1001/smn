package com.vinhle.smn.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nguyenthuantan on 7/8/17.
 */
public class IntegerIdRequest extends BaseRequest {
    private Integer id;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
