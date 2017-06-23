package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VinhLe on 5/6/2017.
 */

public class LoginResponse extends BaseResponse {

    @JsonProperty("account_id")
    public int accountId;
    @JsonProperty("account_email")
    public String accountEmail;
    @JsonProperty("account_full_name")
    public String accountFullName;
    @JsonProperty("account_session")
    public String accountSession;


    @Override
    public String toString() {
        return "LoginResponse{" +
                "accountId=" + accountId +
                ", accountEmail='" + accountEmail + '\'' +
                ", accountFullName='" + accountFullName + '\'' +
                ", accountSession='" + accountSession + '\'' +
                '}';
    }
}
