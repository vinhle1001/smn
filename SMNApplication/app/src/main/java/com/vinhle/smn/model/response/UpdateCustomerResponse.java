package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VinhLe on 5/7/2017.
 */

public class UpdateCustomerResponse extends BaseResponse {

    private Integer customerId;

    @JsonProperty("customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
