package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by VinhLe on 5/7/2017.
 */
public class CustomerSearchResponseV2 extends BaseResponse {

    private List<CustomerSearchResponse> data;

    public CustomerSearchResponseV2(int code, String message, List<CustomerSearchResponse> data) {
        super(code, message);

        this.data = data;
    }

    @JsonProperty("data")
    public List<CustomerSearchResponse> getData() {
        return data;
    }

    public void setData(List<CustomerSearchResponse> data) {
        this.data = data;
    }
}
