package com.vinhle.smn.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VinhLe on 5/24/2017.
 */
public class CustomerIdRequest extends BaseRequest {

    private Integer customerId;

    @JsonProperty("customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
