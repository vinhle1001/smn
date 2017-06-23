package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.searchablespinner.BaseSearchableModel;

/**
 * Created by VinhLe on 5/12/2017.
 */
public class ProductTypeResponse extends BaseSearchableModel {

    private Integer productTypeId;
    private String productTypeName;
    private String productTypeNotation;

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

    @Override
    @JsonIgnore
    public Integer getId() {
        return productTypeId;
    }

    @Override
    @JsonIgnore
    public String getDisplayText() {
        return productTypeName;
    }

    @Override
    @JsonIgnore
    public String getSubTitle() {
        return null;
    }
}
