package com.vinhle.smn.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/7/2017.
 */

public class CustomerSearchResponse implements Serializable {

    @JsonProperty("customer_id")
    public Integer customerId;
    @JsonProperty("customer_full_name")
    public String fullName;
    @JsonProperty("customer_phone_number")
    public String phoneNumber;

}
