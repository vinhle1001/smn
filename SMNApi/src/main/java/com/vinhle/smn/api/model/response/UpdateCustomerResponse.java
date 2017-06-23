package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.api.setting.AppSetting;

/**
 * Created by VinhLe on 5/7/2017.
 */
public class UpdateCustomerResponse extends BaseResponse {

    private Integer customerId;

    public UpdateCustomerResponse(Integer customerId) {
        super(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE);

        this.customerId = customerId;
    }

    public UpdateCustomerResponse(int code, String message) {
        super(code, message);
    }

    @JsonProperty("customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
