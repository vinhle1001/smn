package com.vinhle.smn.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by VinhLe on 5/7/2017.
 */
public class CustomerRequest extends BaseRequest {

    private Integer customerId;
    private String fullName;
    private String firstName;
    private String lastName;
    private String email;
    private String facebook;
    private Byte genderId;
    private Date birthday;
    private String phoneNumber;
    private String address;
    private Integer provinceId;
    private Integer districtId;
    private Integer wardId;
    private Byte customerTypeId;
    private String customerNote;
    private Byte isActive;


    @JsonProperty("customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @JsonProperty("facebook")
    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }


    @JsonProperty("gender_id")
    public Byte getGenderId() {
        return genderId;
    }

    public void setGenderId(Byte genderId) {
        this.genderId = genderId;
    }


    @JsonProperty("birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    @JsonProperty("phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @JsonProperty("province_id")
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }


    @JsonProperty("district_id")
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


    @JsonProperty("customer_type_id")
    public Byte getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Byte customerTypeId) {
        this.customerTypeId = customerTypeId;
    }


    @JsonProperty("customer_note")
    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }


    @JsonProperty("is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

}
