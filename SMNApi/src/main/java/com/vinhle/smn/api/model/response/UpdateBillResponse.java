package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.api.setting.AppSetting;

import java.util.List;

/**
 * Created by VinhLe on 5/15/2017.
 */
public class UpdateBillResponse extends BaseResponse {

    private Integer billId;
    private String billCode;
    private List<UpdateBillDetailResponse> billDetails;

    public UpdateBillResponse(int code, String message) {
        super(code, message);
    }

    public UpdateBillResponse(Integer billId, String billCode, List<UpdateBillDetailResponse> billDetails) {
        super(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE);

        this.billId = billId;
        this.billCode = billCode;
        this.billDetails = billDetails;
    }

    @JsonProperty("bill_id")
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    @JsonProperty("bill_code")
    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    @JsonProperty("bill_details")
    public List<UpdateBillDetailResponse> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<UpdateBillDetailResponse> billDetails) {
        this.billDetails = billDetails;
    }
}
