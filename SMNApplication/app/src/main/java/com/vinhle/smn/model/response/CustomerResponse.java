package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.model.shared.ResourceModel;

import java.sql.Date;

/**
 * Created by VinhLe on 5/7/2017.
 */

public class CustomerResponse extends BaseResponse {

    @JsonProperty("customer_id")
    public Integer customerId;
    @JsonProperty("full_name")
    public String fullName;
    @JsonProperty("first_name")
    public String firstName;
    @JsonProperty("last_name")
    public String lastName;
    @JsonProperty("email")
    public String email;
    @JsonProperty("facebook")
    public String facebook;
    @JsonProperty("gender")
    public ResourceModel gender;
    @JsonProperty("birthday")
    public Date birthday;
    @JsonProperty("phone_number")
    public String phoneNumber;
    @JsonProperty("address")
    public String address;
    @JsonProperty("province")
    public ResourceModel province;
    @JsonProperty("district")
    public ResourceModel district;
    @JsonProperty("ward")
    public ResourceModel ward;
    @JsonProperty("customer_type")
    public ResourceModel customerType;
    @JsonProperty("customer_note")
    public String customerNote;
    @JsonProperty("is_active")
    public Byte isActive;

    @Override
    public String toString() {
        return "CustomerResponse{" +
                "customerId=" + customerId +
                ", fullName='" + fullName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", facebook='" + facebook + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", province=" + province +
                ", district=" + district +
                ", ward=" + ward +
                ", customerType=" + customerType +
                ", customerNote='" + customerNote + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
