package com.vinhle.smn.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.annotation.RequestProperty;

/**
 * Created by VinhLe on 5/31/2017.
 */
public class SupplierRequest extends BaseRequest {

    private Integer supplierId;
    private String name;
    private String description;
    private String phoneNumber;
    private String email;
    private String address;
    private Integer provinceId;
    private Integer districtId;
    private Integer wardId;
    private Byte isActive;

    @JsonProperty("supplier_id")
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    @JsonProperty("name")
    @RequestProperty(checkNullAndEmpty = true, fieldRequired = "name", contentRequired = "Name of supplier is not empty!")
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
    @RequestProperty(checkNullAndEmpty = true, pattern = "^(\\+84|0)([0-9]{9,10})(\\-.{1,20})?$", fieldRequired = "phone_number", contentRequired = "Phone number is wrong format!")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("email")
    @RequestProperty(pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$", fieldRequired = "email", contentRequired = "Email is wrong format!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("address")
    @RequestProperty(checkNullAndEmpty = true, fieldRequired = "address", contentRequired = "Address is not empty!")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("province_id")
    @RequestProperty(checkNullAndEmpty = true, fieldRequired = "province", contentRequired = "Province is not empty!")
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @JsonProperty("district_id")
    @RequestProperty(checkNullAndEmpty = true, fieldRequired = "district", contentRequired = "District is not empty!")
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

    @JsonProperty("is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }
}
