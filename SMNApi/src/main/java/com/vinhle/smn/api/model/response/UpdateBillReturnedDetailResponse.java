package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 6/3/2017.
 */
public class UpdateBillReturnedDetailResponse implements Serializable {

    private Integer billReturnedDetailId;
    private Integer billDetailId;

    public UpdateBillReturnedDetailResponse(Integer billReturnedDetailId, Integer billDetailId) {
        this.billReturnedDetailId = billReturnedDetailId;
        this.billDetailId = billDetailId;
    }

    @JsonProperty("bill_returned_detail_id")
    public Integer getBillReturnedDetailId() {
        return billReturnedDetailId;
    }

    public void setBillReturnedDetailId(Integer billReturnedDetailId) {
        this.billReturnedDetailId = billReturnedDetailId;
    }

    @JsonProperty("bill_detail_id")
    public Integer getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Integer billDetailId) {
        this.billDetailId = billDetailId;
    }
}
