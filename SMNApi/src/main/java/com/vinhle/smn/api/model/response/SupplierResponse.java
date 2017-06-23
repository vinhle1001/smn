package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.api.model.shared.ResourceModel;
import com.vinhle.smn.api.setting.AppSetting;

/**
 * Created by VinhLe on 5/31/2017.
 */
public class SupplierResponse extends BaseResponse {

    private Integer supplierId;
    private String name;
    private String description;
    private String phoneNumber;
    private String email;
    private String address;
    private ResourceModel province;
    private ResourceModel district;
    private ResourceModel ward;
    private Byte isActive;

    public SupplierResponse(int code, String message) {
        super(code, message);
    }

    public SupplierResponse(Integer supplierId, String name, String description, String phoneNumber, String email, String address, ResourceModel province, ResourceModel district, ResourceModel ward, Byte isActive) {
        super(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE);

        this.supplierId = supplierId;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.isActive = isActive;
    }

    @JsonProperty("supplier_id")
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @JsonProperty("is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

}
