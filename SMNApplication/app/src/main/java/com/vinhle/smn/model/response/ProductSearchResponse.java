package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/11/2017.
 */
public class ProductSearchResponse implements Serializable {

    private Integer productId;
    private String productName;
    private Long productPrice;
    private String productTypeName;
    private String productTypeIcon;
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

    @JsonProperty("product_type_name")
    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    @JsonProperty("product_type_icon")
    public String getProductTypeIcon() {
        return productTypeIcon;
    }

    public void setProductTypeIcon(String productTypeIcon) {
        this.productTypeIcon = productTypeIcon;
    }

    @JsonProperty("is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }
}
