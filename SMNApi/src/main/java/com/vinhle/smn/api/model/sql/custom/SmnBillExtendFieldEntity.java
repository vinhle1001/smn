package com.vinhle.smn.api.model.sql.custom;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by VinhLe on 5/15/2017.
 */
@Entity
public class SmnBillExtendFieldEntity implements Serializable {

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

    /******************************************** Extends field bill **********************************/

    private String provinceName;

    @Basic
    @javax.persistence.Column(name = "province_name")
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    private String districtName;

    @Basic
    @javax.persistence.Column(name = "district_name")
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    private String wardName;

    @Basic
    @javax.persistence.Column(name = "ward_name")
    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    private String agencyName;

    @Basic
    @javax.persistence.Column(name = "agency_name")
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    private String customerName;

    @Basic
    @javax.persistence.Column(name = "customer_name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
