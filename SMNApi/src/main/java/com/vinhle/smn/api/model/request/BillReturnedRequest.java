package com.vinhle.smn.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by VinhLe on 6/3/2017.
 */
public class BillReturnedRequest extends BaseRequest {

    private Integer billId;
    private Long billRefundCost;
    private List<BillReturnedDetailRequest> billReturnedDetails;

    @JsonProperty("bill_id")
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    @JsonProperty("bill_refund_cost")
    public Long getBillRefundCost() {
        return billRefundCost;
    }

    public void setBillRefundCost(Long billRefundCost) {
        this.billRefundCost = billRefundCost;
    }

    @JsonProperty("bill_returned_details")
    public List<BillReturnedDetailRequest> getBillReturnedDetails() {
        return billReturnedDetails;
    }

    public void setBillReturnedDetails(List<BillReturnedDetailRequest> billReturnedDetails) {
        this.billReturnedDetails = billReturnedDetails;
    }
}
