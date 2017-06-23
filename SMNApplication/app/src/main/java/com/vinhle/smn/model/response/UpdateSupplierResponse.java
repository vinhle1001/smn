package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VinhLe on 5/31/2017.
 */
public class UpdateSupplierResponse extends BaseResponse {

    private Integer supplierId;

    @JsonProperty("supplier_id")
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
}
