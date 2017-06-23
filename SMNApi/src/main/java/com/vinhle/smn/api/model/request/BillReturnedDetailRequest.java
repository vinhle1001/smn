package com.vinhle.smn.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 6/3/2017.
 */
public class BillReturnedDetailRequest implements Serializable {

    private Integer billReturnedDetailId;
    private Integer billDetailId;
    private Integer productId;
    private Integer productReturnedQuantity;
    private String description;
    private Long refundedCost;
    private Byte isActive;

    @JsonProperty("bill_returned_detail_id")
    public Integer getBillReturnedDetailId() {
        return billReturnedDetailId;
    }

    public void setBillReturnedDetailId(Integer billReturnedDetailId) {
        this.billReturnedDetailId = billReturnedDetailId;
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

    @JsonProperty("product_returned_quantity")
    public Integer getProductReturnedQuantity() {
        return productReturnedQuantity;
    }

    public void setProductReturnedQuantity(Integer productReturnedQuantity) {
        this.productReturnedQuantity = productReturnedQuantity;
    }

    @JsonProperty("refunded_cost")
    public Long getRefundedCost() {
        return refundedCost;
    }

    public void setRefundedCost(Long refundedCost) {
        this.refundedCost = refundedCost;
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

}
