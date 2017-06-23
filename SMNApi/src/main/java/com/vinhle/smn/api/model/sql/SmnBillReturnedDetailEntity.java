package com.vinhle.smn.api.model.sql;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by VinhLe on 6/5/2017.
 */
@Entity
@Table(name = "smn_bill_returned_detail")
@DynamicInsert
public class SmnBillReturnedDetailEntity implements Serializable {
    private Integer billReturnedDetailId;
    private Integer billId;
    private Integer billDetailId;
    private Integer productReturnedQuantity;
    private Long refundedCost;
    private String description;
    private Timestamp createdDate;
    private Integer createdBy;
    private Timestamp modifiedDate;
    private Byte isActive;

    @Id
    @Column(name = "bill_returned_detail_id")
    @GeneratedValue
    public Integer getBillReturnedDetailId() {
        return billReturnedDetailId;
    }

    public void setBillReturnedDetailId(Integer billReturnedDetailId) {
        this.billReturnedDetailId = billReturnedDetailId;
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
    @Column(name = "bill_detail_id")
    public Integer getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Integer billDetailId) {
        this.billDetailId = billDetailId;
    }

    @Basic
    @Column(name = "product_returned_quantity")
    public Integer getProductReturnedQuantity() {
        return productReturnedQuantity;
    }

    public void setProductReturnedQuantity(Integer productReturnedQuantity) {
        this.productReturnedQuantity = productReturnedQuantity;
    }

    @Basic
    @Column(name = "refunded_cost")
    public Long getRefundedCost() {
        return refundedCost;
    }

    public void setRefundedCost(Long refundedCost) {
        this.refundedCost = refundedCost;
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

        SmnBillReturnedDetailEntity that = (SmnBillReturnedDetailEntity) o;

        if (billReturnedDetailId != null ? !billReturnedDetailId.equals(that.billReturnedDetailId) : that.billReturnedDetailId != null)
            return false;
        if (billId != null ? !billId.equals(that.billId) : that.billId != null) return false;
        if (billDetailId != null ? !billDetailId.equals(that.billDetailId) : that.billDetailId != null) return false;
        if (productReturnedQuantity != null ? !productReturnedQuantity.equals(that.productReturnedQuantity) : that.productReturnedQuantity != null)
            return false;
        if (refundedCost != null ? !refundedCost.equals(that.refundedCost) : that.refundedCost != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = billReturnedDetailId != null ? billReturnedDetailId.hashCode() : 0;
        result = 31 * result + (billId != null ? billId.hashCode() : 0);
        result = 31 * result + (billDetailId != null ? billDetailId.hashCode() : 0);
        result = 31 * result + (productReturnedQuantity != null ? productReturnedQuantity.hashCode() : 0);
        result = 31 * result + (refundedCost != null ? refundedCost.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }
}
