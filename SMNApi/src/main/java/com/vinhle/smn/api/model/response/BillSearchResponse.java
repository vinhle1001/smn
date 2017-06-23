package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by VinhLe on 5/18/2017.
 */
public class BillSearchResponse implements Serializable {

    private Integer customerId;
    private String customerName;
    private Integer billId;
    private Long billPrice;
    private Timestamp createDate;
    private Integer billStepId;

    public BillSearchResponse(Integer customerId, String customerName, Integer billId, Long billPrice, Timestamp createDate, Integer billStepId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.billId = billId;
        this.billPrice = billPrice;
        this.createDate = createDate;
        this.billStepId = billStepId;
    }

    @JsonProperty("customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("customer_name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonProperty("bill_id")
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    @JsonProperty("bill_price")
    public Long getBillPrice() {
        return billPrice;
    }

    public void setBillPrice(Long billPrice) {
        this.billPrice = billPrice;
    }

    @JsonProperty("create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Integer getBillStepId() {
        return billStepId;
    }

    public void setBillStepId(Integer billStepId) {
        this.billStepId = billStepId;
    }
}

