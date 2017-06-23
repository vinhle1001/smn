package com.vinhle.smn.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.annotation.RequestProperty;
import com.vinhle.smn.model.response.CustomerResponse;
import com.vinhle.smn.model.shared.ResourceModel;

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
    @RequestProperty(checkNullAndEmpty = true, fieldRequired = "full_name", contentRequired = "Full name is not empty!")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("first_name")
    @RequestProperty(checkNullAndEmpty = true, fieldRequired = "first_name", contentRequired = "First name is not empty!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @JsonProperty("last_name")
    @RequestProperty(checkNullAndEmpty = true, fieldRequired = "last_name", contentRequired = "Last name is not empty!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @JsonProperty("email")
    @RequestProperty(pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$", fieldRequired = "email", contentRequired = "Email is wrong format!")
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
    @RequestProperty(checkNullAndEmpty = true, pattern = "^(\\+84|0)([0-9]{9,10})(\\-.{1,20})?$", fieldRequired = "phone_number", contentRequired = "Phone number is wrong format!")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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


    @JsonProperty("customer_type_id")
//    @RequestProperty(checkNullAndEmpty = true, fieldRequired = "customer_type", contentRequired = "Customer type is not empty!")
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
