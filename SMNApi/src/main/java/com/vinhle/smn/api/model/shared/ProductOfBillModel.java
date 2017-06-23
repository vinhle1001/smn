package com.vinhle.smn.api.model.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/12/2017.
 */
public class ProductOfBillModel implements Serializable {

    private Integer billItemId;
    private Integer productId;
    private String productName;
    private Integer productQuantity;
    private Long productPrice;
    private Long productTotalPrice;
    private String productNote;
    private byte isActive;

    @JsonProperty("bill_item_id")
    public Integer getBillItemId() {
        return billItemId;
    }

    public void setBillItemId(Integer billItemId) {
        this.billItemId = billItemId;
    }

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

    @JsonProperty("product_quantity")
    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @JsonProperty("product_price")
    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    @JsonProperty("product_total_price")
    public Long getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(Long productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    @JsonProperty("product_note")
    public String getProductNote() {
        return productNote;
    }

    public void setProductNote(String productNote) {
        this.productNote = productNote;
    }

    @JsonProperty("is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }
}
