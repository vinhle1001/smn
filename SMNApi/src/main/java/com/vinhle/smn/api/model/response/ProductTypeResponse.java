package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/12/2017.
 */
public class ProductTypeResponse implements Serializable {

    private Integer productTypeId;
    private String productTypeName;
    private String productTypeNotation;

    public ProductTypeResponse(Integer productTypeId, String productTypeName, String productTypeNotation) {
        this.productTypeId = productTypeId;
        this.productTypeName = productTypeName;
        this.productTypeNotation = productTypeNotation;
    }

    @JsonProperty("product_type_id")
    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    @JsonProperty("product_type_name")
    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    @JsonProperty("product_type_notation")
    public String getProductTypeNotation() {
        return productTypeNotation;
    }

    public void setProductTypeNotation(String productTypeNotation) {
        this.productTypeNotation = productTypeNotation;
    }
}
