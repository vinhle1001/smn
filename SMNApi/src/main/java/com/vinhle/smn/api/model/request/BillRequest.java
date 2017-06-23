package com.vinhle.smn.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.api.model.shared.ResourceModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by VinhLe on 5/15/2017.
 */
public class BillRequest extends BaseRequest {

    private Integer billId;
    private String billCode;
    private Integer customerId;
    private Integer agencyId;
    private Long billPrice;
    private Long billRefundCost;
    private Long billDebt;
    private Integer billStepId;
    private ResourceModel billType;
    private String description;
    private String address;
    private Integer provinceId;
    private Integer districtId;
    private Integer wardId;
    private List<BillDetailRequest> billDetails;
    private byte isActive;

    @JsonProperty("bill_id")
    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    @JsonProperty("bill_code")
    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    @JsonProperty(value = "customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @JsonProperty(value = "agency_id")
    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    @JsonProperty("bill_price")
    public Long getBillPrice() {
        return billPrice;
    }

    public void setBillPrice(Long billPrice) {
        this.billPrice = billPrice;
    }

    @JsonProperty("bill_refund_cost")
    public Long getBillRefundCost() {
        return billRefundCost;
    }

    public void setBillRefundCost(Long billRefundCost) {
        this.billRefundCost = billRefundCost;
    }

    @JsonProperty("bill_debt")
    public Long getBillDebt() {
        return billDebt;
    }

    public void setBillDebt(Long billDebt) {
        this.billDebt = billDebt;
    }

    @JsonProperty(value = "bill_step_id")
    public Integer getBillStepId() {
        return billStepId;
    }

    public void setBillStepId(Integer billStepId) {
        this.billStepId = billStepId;
    }

    @JsonProperty("bill_type")
    public ResourceModel getBillType() {
        return billType;
    }

    public void setBillType(ResourceModel billType) {
        this.billType = billType;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty(value = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty(value = "province_id")
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @JsonProperty(value = "district_id")
    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    @JsonProperty("ward_id")
    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }

    @JsonProperty("bill_details")
    public List<BillDetailRequest> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetailRequest> billDetails) {
        this.billDetails = billDetails;
    }

    @JsonProperty("is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "BillRequest{" +
                "billId=" + billId +
                ", billCode='" + billCode + '\'' +
                ", customerId=" + customerId +
                ", agencyId=" + agencyId +
                ", billPrice=" + billPrice +
                ", billStepId=" + billStepId +
                ", billType=" + billType +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", provinceId=" + provinceId +
                ", districtId=" + districtId +
                ", wardId=" + wardId +
                ", billDetails=" + billDetails +
                ", isActive=" + isActive +
                '}';
    }
}
