package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VinhLe on 5/6/2017.
 */
public class LoginResponse extends BaseResponse {

    private int accountId;
    private String accountEmail;
    private String accountFullName;
    private String accountSession;

    public LoginResponse(int code, String message) {
        super(code, message);
    }

    public LoginResponse(int code, String message, int accountId, String accountEmail, String accountFullName, String accountSession) {
        super(code, message);

        this.accountId = accountId;
        this.accountEmail = accountEmail;
        this.accountFullName = accountFullName;
        this.accountSession = accountSession;
    }

    @JsonProperty("account_id")
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("account_email")
    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    @JsonProperty("account_full_name")
    public String getAccountFullName() {
        return accountFullName;
    }

    public void setAccountFullName(String accountFullName) {
        this.accountFullName = accountFullName;
    }

    @JsonProperty("account_session")
    public String getAccountSession() {
        return accountSession;
    }

    public void setAccountSession(String accountSession) {
        this.accountSession = accountSession;
    }
}
