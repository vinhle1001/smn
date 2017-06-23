package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/18/2017.
 */
public class BillStepResponse implements Serializable {

    private Integer billStepId;
    private String billStepText;
    private String billStepColor;
    private Integer billStepOrder;

    public BillStepResponse(Integer billStepId, String billStepText, String billStepColor, Integer billStepOrder) {
        this.billStepId = billStepId;
        this.billStepText = billStepText;
        this.billStepColor = billStepColor;
        this.billStepOrder = billStepOrder;
    }

    @JsonProperty("bill_step_id")
    public Integer getBillStepId() {
        return billStepId;
    }

    public void setBillStepId(Integer billStepId) {
        this.billStepId = billStepId;
    }

    @JsonProperty("bill_step_text")
    public String getBillStepText() {
        return billStepText;
    }

    public void setBillStepText(String billStepText) {
        this.billStepText = billStepText;
    }

    @JsonProperty("bill_step_color")
    public String getBillStepColor() {
        return billStepColor;
    }

    public void setBillStepColor(String billStepColor) {
        this.billStepColor = billStepColor;
    }

    @JsonProperty("bill_step_order")
    public Integer getBillStepOrder() {
        return billStepOrder;
    }

    public void setBillStepOrder(Integer billStepOrder) {
        this.billStepOrder = billStepOrder;
    }
}
