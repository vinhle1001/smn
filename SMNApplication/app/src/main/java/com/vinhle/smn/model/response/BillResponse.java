package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.model.shared.ResourceModel;

import java.util.List;

/**
 * Created by VinhLe on 5/15/2017.
 */
public class BillResponse extends BaseResponse {

    private Integer billId;
    private String billCode;
    private Integer customerId;
    private String customerName;
    private String customerPhone;
    private String address;
    private ResourceModel province;
    private ResourceModel district;
    private ResourceModel ward;
    private ResourceModel agency;
    private String description;
    private Integer billStepId;
    private Long billPrice;
    private Long billRefundCost;
    private Long billDebt;
    private List<BillDetailResponse> billDetails;
    private Byte isActive;

    @JsonProperty("bill_id")
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    @JsonProperty("customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("bill_code")
    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    @JsonProperty("customer_name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonProperty("customer_phone")
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("province")
    public ResourceModel getProvince() {
        return province;
    }

    public void setProvince(ResourceModel province) {
        this.province = province;
    }

    @JsonProperty("district")
    public ResourceModel getDistrict() {
        return district;
    }

    public void setDistrict(ResourceModel district) {
        this.district = district;
    }

    @JsonProperty("ward")
    public ResourceModel getWard() {
        return ward;
    }

    public void setWard(ResourceModel ward) {
        this.ward = ward;
    }

    @JsonProperty("agency")
    public ResourceModel getAgency() {
        return agency;
    }

    public void setAgency(ResourceModel agency) {
        this.agency = agency;
    }

    @JsonProperty("bill_step_id")
    public Integer getBillStepId() {
        return billStepId;
    }

    public void setBillStepId(Integer billStepId) {
        this.billStepId = billStepId;
    }

    @JsonProperty("bill_price")
    public Long getBillPrice() {
        if (billPrice == null) return 0L;
        return billPrice;
    }

    public void setBillPrice(Long billPrice) {
        this.billPrice = billPrice;
    }

    @JsonProperty("bill_refund_cost")
    public Long getBillRefundCost() {
        if (billRefundCost == null) return 0L;
        return billRefundCost;
    }

    public void setBillRefundCost(Long billRefundCost) {
        this.billRefundCost = billRefundCost;
    }

    @JsonProperty("bill_debt")
    public Long getBillDebt() {
        if (billDebt == null) return 0L;
        return billDebt;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("bill_details")
    public List<BillDetailResponse> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetailResponse> billDetails) {
        this.billDetails = billDetails;
    }

    @JsonProperty("is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }
}
