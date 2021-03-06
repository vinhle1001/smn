package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 6/7/2017.
 */
public class BillReturnedDetailResponse implements Serializable {

    private Integer billReturnedDetailId;
    private Integer billDetailId;
    private Integer productId;
    private String productName;
    private Integer productQuantity;
    private Integer productReturnedQuantity;
    private Long refundCost;
    private String description;
    private Byte isActive;
    //    private Byte isModified;

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

    @JsonProperty("product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("product_quantity")
    public Integer getProductQuantity() {
        if (productQuantity == null) return 0;
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @JsonProperty("product_returned_quantity")
    public Integer getProductReturnedQuantity() {
        if (productReturnedQuantity == null) return 0;
        return productReturnedQuantity;
    }

    public void setProductReturnedQuantity(Integer productReturnedQuantity) {
        this.productReturnedQuantity = productReturnedQuantity;
    }

    @JsonProperty("refund_cost")
    public Long getRefundCost() {
        if (refundCost == null) return 0L;
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
}
