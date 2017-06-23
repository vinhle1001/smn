package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.api.model.shared.ResourceModel;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/15/2017.
 */
public class BillDetailResponse implements Serializable {

    private Integer billDetailId;
    private ResourceModel product;
    private Long productPrice;
    private Integer productQuantity;
    private Integer productReturnedQuantity;
    private Long refundCost;
    private String description;
//    private Byte isModified;
    private Byte isActive;

    public BillDetailResponse(Integer billDetailId, ResourceModel product, Long productPrice, Integer productQuantity, Integer productReturnedQuantity, Long refundCost, String description, Byte isActive) {
        this.billDetailId = billDetailId;
        this.product = product;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productReturnedQuantity = productReturnedQuantity;
        this.refundCost = refundCost;
        this.description = description;
        this.isActive = isActive;
    }

    @JsonProperty("bill_detail_id")
    public Integer getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Integer billDetailId) {
        this.billDetailId = billDetailId;
    }

    @JsonProperty("product")
    public ResourceModel getProduct() {
        return product;
    }

    public void setProduct(ResourceModel product) {
        this.product = product;
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

    @JsonProperty("product_returned_quantity")
    public Integer getProductReturnedQuantity() {
        return productReturnedQuantity;
    }

    public void setProductReturnedQuantity(Integer productReturnedQuantity) {
        this.productReturnedQuantity = productReturnedQuantity;
    }

    @JsonProperty("refund_cost")
    public Long getRefundCost() {
        return refundCost;
    }

    public void setRefundCost(Long refundCost) {
        this.refundCost = refundCost;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

   /* @JsonProperty("is_modified")
    public Byte getIsModified() {
        return 0;
    }

    public void setIsModified(Byte isModified) {
        this.isModified = isModified;
    }*/
}
