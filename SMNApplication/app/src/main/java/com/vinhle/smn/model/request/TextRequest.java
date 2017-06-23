package com.vinhle.smn.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VinhLe on 5/24/2017.
 */
public class TextRequest extends BaseRequest {

    private String text;

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
