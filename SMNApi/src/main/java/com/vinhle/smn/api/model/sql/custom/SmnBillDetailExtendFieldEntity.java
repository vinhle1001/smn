package com.vinhle.smn.api.model.sql.custom;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by VinhLe on 5/15/2017.
 */
@Entity
public class SmnBillDetailExtendFieldEntity implements Serializable {

    private Integer billDetailId;
    private Integer billId;
    private Integer productId;
    private String description;
    private Integer productBeginningQuantity;
    private Integer productReturnedQuantity;
    private Long refundCost;
    private Long productCurrentPrice;
    private Long productPrice;
    private Integer productQuantity;
    private Timestamp createdDate;
    private Integer createdBy;
    private Timestamp modifiedDate;
    private Byte isActive;

    @Id
    @Column(name = "bill_detail_id")
    public Integer getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Integer billDetailId) {
        this.billDetailId = billDetailId;
    }

    @Basic
    @Column(name = "bill_id")
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    @Basic
    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "product_beginning_quantity")
    public Integer getProductBeginningQuantity() {
        return productBeginningQuantity;
    }

    public void setProductBeginningQuantity(Integer productBeginningQuantity) {
        this.productBeginningQuantity = productBeginningQuantity;
    }

    @Basic
    @Column(name = "product_current_price")
    public Long getProductCurrentPrice() {
        return productCurrentPrice;
    }

    public void setProductCurrentPrice(Long productCurrentPrice) {
        this.productCurrentPrice = productCurrentPrice;
    }

    @Basic
    @Column(name = "product_price")
    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    @Basic
    @Column(name = "product_quantity")
    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Basic
    @Column(name = "product_quantity_returned")
    public Integer getProductReturnedQuantity() {
        return productReturnedQuantity;
    }

    public void setProductReturnedQuantity(Integer productReturnedQuantity) {
        this.productReturnedQuantity = productReturnedQuantity;
    }

    @Basic
    @Column(name = "refunded_cost")
    public Long getRefundCost() {
        return refundCost;
    }

    public void setRefundCost(Long refundCost) {
        this.refundCost = refundCost;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "created_by")
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "modified_date")
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Basic
    @Column(name = "is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    /******************************************** Extends field bill detail **********************************/

    private String productName;

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    private String productSize;

    @Basic
    @Column(name = "product_size")
    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
}
