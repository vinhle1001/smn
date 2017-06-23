package com.vinhle.smn.api.model.sql;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by VinhLe on 6/3/2017.
 */
@Entity
@Table(name = "smn_bill_detail")
@DynamicInsert
public class SmnBillDetailEntity implements Serializable {
    private Integer billDetailId;
    private Integer billId;
    private Integer productId;
    private String description;
    private Integer productBeginningQuantity;
    private Long productCurrentPrice;
    private Long productPrice;
    private Integer productQuantity;
    private Timestamp createdDate;
    private Integer createdBy;
    private Timestamp modifiedDate;
    private Byte isActive;
    private String uid;

    @Id
    @Column(name = "bill_detail_id")
    @GeneratedValue
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmnBillDetailEntity that = (SmnBillDetailEntity) o;

        if (billDetailId != null ? !billDetailId.equals(that.billDetailId) : that.billDetailId != null) return false;
        if (billId != null ? !billId.equals(that.billId) : that.billId != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (productBeginningQuantity != null ? !productBeginningQuantity.equals(that.productBeginningQuantity) : that.productBeginningQuantity != null)
            return false;
        if (productCurrentPrice != null ? !productCurrentPrice.equals(that.productCurrentPrice) : that.productCurrentPrice != null)
            return false;
        if (productPrice != null ? !productPrice.equals(that.productPrice) : that.productPrice != null) return false;
        if (productQuantity != null ? !productQuantity.equals(that.productQuantity) : that.productQuantity != null)
            return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = billDetailId != null ? billDetailId.hashCode() : 0;
        result = 31 * result + (billId != null ? billId.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (productBeginningQuantity != null ? productBeginningQuantity.hashCode() : 0);
        result = 31 * result + (productCurrentPrice != null ? productCurrentPrice.hashCode() : 0);
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
        result = 31 * result + (productQuantity != null ? productQuantity.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }

    @Transient
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
