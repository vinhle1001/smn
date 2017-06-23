package com.vinhle.smn.api.model.sql;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by VinhLe on 6/6/2017.
 */
@Entity
@Table(name = "smn_bill")
@DynamicInsert
public class SmnBillEntity implements Serializable {
    private Integer billId;
    private String billCode;
    private Integer customerId;
    private Integer agencyId;
    private Long billPrice;
    private Long billRefundCost;
    private Long billDebt;
    private String address;
    private Integer billStepId;
    private Integer billTypeId;
    private String description;
    private Integer provinceId;
    private Integer districtId;
    private Integer wardId;
    private Timestamp createdDate;
    private Integer createdBy;
    private Timestamp modifiedDate;
    private Byte isActive;

    @Id
    @Column(name = "bill_id")
    @GeneratedValue
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    @Basic
    @Column(name = "bill_code")
    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    @Basic
    @Column(name = "customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "agency_id")
    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    @Basic
    @Column(name = "bill_price")
    public Long getBillPrice() {
        return billPrice;
    }

    public void setBillPrice(Long billPrice) {
        this.billPrice = billPrice;
    }

    @Basic
    @Column(name = "bill_refund_cost")
    public Long getBillRefundCost() {
        return billRefundCost;
    }

    public void setBillRefundCost(Long billRefundCost) {
        this.billRefundCost = billRefundCost;
    }

    @Basic
    @Column(name = "bill_debt")
    public Long getBillDebt() {
        return billDebt;
    }

    public void setBillDebt(Long billDebt) {
        this.billDebt = billDebt;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "bill_step_id")
    public Integer getBillStepId() {
        return billStepId;
    }

    public void setBillStepId(Integer billStepId) {
        this.billStepId = billStepId;
    }

    @Basic
    @Column(name = "bill_type_id")
    public Integer getBillTypeId() {
        return billTypeId;
    }

    public void setBillTypeId(Integer billTypeId) {
        this.billTypeId = billTypeId;
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
    @Column(name = "province_id")
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "district_id")
    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    @Basic
    @Column(name = "ward_id")
    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
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

        SmnBillEntity that = (SmnBillEntity) o;

        if (billId != null ? !billId.equals(that.billId) : that.billId != null) return false;
        if (billCode != null ? !billCode.equals(that.billCode) : that.billCode != null) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (agencyId != null ? !agencyId.equals(that.agencyId) : that.agencyId != null) return false;
        if (billPrice != null ? !billPrice.equals(that.billPrice) : that.billPrice != null) return false;
        if (billRefundCost != null ? !billRefundCost.equals(that.billRefundCost) : that.billRefundCost != null)
            return false;
        if (billDebt != null ? !billDebt.equals(that.billDebt) : that.billDebt != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (billStepId != null ? !billStepId.equals(that.billStepId) : that.billStepId != null) return false;
        if (billTypeId != null ? !billTypeId.equals(that.billTypeId) : that.billTypeId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (provinceId != null ? !provinceId.equals(that.provinceId) : that.provinceId != null) return false;
        if (districtId != null ? !districtId.equals(that.districtId) : that.districtId != null) return false;
        if (wardId != null ? !wardId.equals(that.wardId) : that.wardId != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = billId != null ? billId.hashCode() : 0;
        result = 31 * result + (billCode != null ? billCode.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (agencyId != null ? agencyId.hashCode() : 0);
        result = 31 * result + (billPrice != null ? billPrice.hashCode() : 0);
        result = 31 * result + (billRefundCost != null ? billRefundCost.hashCode() : 0);
        result = 31 * result + (billDebt != null ? billDebt.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (billStepId != null ? billStepId.hashCode() : 0);
        result = 31 * result + (billTypeId != null ? billTypeId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (provinceId != null ? provinceId.hashCode() : 0);
        result = 31 * result + (districtId != null ? districtId.hashCode() : 0);
        result = 31 * result + (wardId != null ? wardId.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }
}
