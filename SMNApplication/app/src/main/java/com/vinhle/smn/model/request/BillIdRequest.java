package com.vinhle.smn.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VinhLe on 5/24/2017.
 */
public class BillIdRequest extends BaseRequest {

    private Integer billId;

    @JsonProperty("bill_id")
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }
}
