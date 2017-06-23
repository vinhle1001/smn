package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by VinhLe on 6/5/2017.
 */
public class UpdateBillReturnedResponse extends BaseResponse {

    private List<UpdateBillReturnedDetailResponse> billReturnedDetailResponses;

    @JsonProperty("bill_returned_details")
    public List<UpdateBillReturnedDetailResponse> getBillReturnedDetailResponses() {
        return billReturnedDetailResponses;
    }

    public void setBillReturnedDetailResponses(List<UpdateBillReturnedDetailResponse> billReturnedDetailResponses) {
        this.billReturnedDetailResponses = billReturnedDetailResponses;
    }
}
