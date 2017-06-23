package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.api.model.shared.ResourceModel;
import com.vinhle.smn.api.setting.AppSetting;

import java.sql.Date;

/**
 * Created by VinhLe on 5/7/2017.
 */
public class CustomerResponse extends BaseResponse {

    private Integer customerId;
    private String fullName;
    private String firstName;
    private String lastName;
    private String email;
    private String facebook;
    private ResourceModel gender;
    private Date birthday;
    private String phoneNumber;
    private String address;
    private ResourceModel province;
    private ResourceModel district;
    private ResourceModel ward;
    private ResourceModel customerType;
    private String customerNote;
    private Byte isActive;

    public CustomerResponse(int code, String message) {
        super(code, message);
    }

    public CustomerResponse(Integer customerId, String fullName, String firstName, String lastName, String email, String facebook, ResourceModel gender, Date birthday, String phoneNumber, String address, ResourceModel province, ResourceModel district, ResourceModel ward, ResourceModel customerType, String customerNote, Byte isActive) {
        super(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE);

        this.customerId = customerId;
        this.fullName = fullName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.facebook = facebook;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.customerType = customerType;
        this.customerNote = customerNote;
        this.isActive = isActive;
    }

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


    @JsonProperty("gender")
    public ResourceModel getGender() {
        return gender;
    }

    public void setGender(ResourceModel gender) {
        this.gender = gender;
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


    @JsonProperty("customer_type")
    public ResourceModel getCustomerType() {
        return customerType;
    }

    public void setCustomerTypeId(ResourceModel customerType) {
        this.customerType = customerType;
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
