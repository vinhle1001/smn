package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.model.shared.ResourceModel;

import java.io.Serializable;
import java.util.UUID;

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

    private String uid;
    private Boolean btnBillReturnedDetail = Boolean.FALSE;
//    private Byte isModified;

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
        if (productPrice == null) return 0L;
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
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

    @JsonIgnore
    public String getUid() {
        if (uid == null || uid.isEmpty())
            uid = UUID.randomUUID().toString();
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    /*@JsonProperty("is_modified")
    public Byte getIsModified() {
        return isModified;
    }

    public void setIsModified(Byte isModified) {
        this.isModified = isModified;
    }*/

    @JsonIgnore
    public Boolean getBtnBillReturnedDetail() {
        return btnBillReturnedDetail;
    }

    public void setBtnBillReturnedDetail(Boolean btnBillReturnedDetail) {
        this.btnBillReturnedDetail = btnBillReturnedDetail;
    }

    public boolean hasReturned() {
        return productReturnedQuantity != null && productReturnedQuantity > 0;
    }

    @Override
    public String toString() {
        return "BillDetailResponse{" +
                "billDetailId=" + billDetailId +
                ", product=" + product +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", uid='" + uid + '\'' +
                '}';
    }
}
