package com.vinhle.smn.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/15/2017.
 */
public class BillDetailRequest implements Serializable {

    private Integer billDetailId;
    private Integer productId;
    private String description;
    private Long productPrice;
    private Integer productQuantity;
    private byte isActive;
    private String uid;
//    private byte isModified;

    public BillDetailRequest(Integer billDetailId, Integer productId, String description, Long productPrice, Integer productQuantity, byte isActive/*, byte isModified*/, String uid) {
        this.billDetailId = billDetailId;
        this.productId = productId;
        this.description = description;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.isActive = isActive;
//        this.isModified = isModified;
        this.uid = uid;
    }

    @JsonProperty("bill_detail_id")
    public Integer getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Integer billDetailId) {
        this.billDetailId = billDetailId;
    }

    @JsonProperty("product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("product_price")
    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    @JsonProperty("product_quantity")
    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @JsonProperty("is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    /*@JsonProperty("is_modified")
    public byte getIsModified() {
        return isModified;
    }

    public void setIsModified(byte isModified) {
        this.isModified = isModified;
    }*/
}
