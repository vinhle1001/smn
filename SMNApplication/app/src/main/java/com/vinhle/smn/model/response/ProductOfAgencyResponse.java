package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.searchablespinner.BaseSearchableModel;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/17/2017.
 */
public class ProductOfAgencyResponse extends BaseSearchableModel {

    private Integer productId;
    private String productName;
    private Long productPrice;
    private Long productCost;
    private Integer productQuantity;
    private Byte isActive;

    @JsonProperty("product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("product_price")
    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    @JsonProperty("product_cost")
    public Long getProductCost() {
        return productCost;
    }

    public void setProductCost(Long productCost) {
        this.productCost = productCost;
    }

    @JsonProperty("product_quantity")
    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @JsonProperty("is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }


    @Override
    @JsonIgnore
    public Integer getId() {
        return productId;
    }

    @Override
    @JsonIgnore
    public String getDisplayText() {
        return productName;
    }

    @Override
    @JsonIgnore
    public String getSubTitle() {
        return "QTY: " + productQuantity;
    }
}
