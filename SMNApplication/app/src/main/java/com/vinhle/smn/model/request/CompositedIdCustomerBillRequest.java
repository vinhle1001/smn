package com.vinhle.smn.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/15/2017.
 */
public class CompositedIdCustomerBillRequest extends BaseRequest {

    private Integer customerId;
    private Integer billId;

    public CompositedIdCustomerBillRequest() {
    }

    public CompositedIdCustomerBillRequest(Integer customerId, Integer billId) {
        this.customerId = customerId;
        this.billId = billId;
    }

    @JsonProperty("customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("bill_id")
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }
}
