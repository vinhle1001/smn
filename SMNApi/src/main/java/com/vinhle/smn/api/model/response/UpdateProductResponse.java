package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.api.setting.AppSetting;

import java.util.List;

/**
 * Created by VinhLe on 5/11/2017.
 */
public class UpdateProductResponse extends BaseResponse {

    private Integer productId;
    private String productCode;

    public UpdateProductResponse(int code, String message) {
        super(code, message);
    }

    public UpdateProductResponse(Integer productId, String productCode) {
        super(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE);

        this.productId = productId;
        this.productCode = productCode;
    }

    @JsonProperty("product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("product_code")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
