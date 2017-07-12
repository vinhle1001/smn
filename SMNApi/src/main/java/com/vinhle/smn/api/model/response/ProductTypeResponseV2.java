package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by nguyenthuantan on 7/8/17.
 */
public class ProductTypeResponseV2 extends BaseResponse {
    private List<ProductTypeResponse> productTypes;

    public ProductTypeResponseV2(int code, String message, List<ProductTypeResponse> productTypes) {
        super(code, message);

        this.productTypes = productTypes;
    }

    @JsonProperty("product_types")
    public List<ProductTypeResponse> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<ProductTypeResponse> productTypes) {
        this.productTypes = productTypes;
    }
}
