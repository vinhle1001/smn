package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.api.setting.AppSetting;

/**
 * Created by VinhLe on 5/31/2017.
 */
public class UpdateSupplierResponse extends BaseResponse {

    private Integer supplierId;

    public UpdateSupplierResponse(int code, String message) {
        super(code, message);
    }

    public UpdateSupplierResponse(Integer supplierId) {
        super(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE);

        this.supplierId = supplierId;
    }

    @JsonProperty("supplier_id")
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
}
