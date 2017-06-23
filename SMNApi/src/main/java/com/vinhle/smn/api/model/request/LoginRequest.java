package com.vinhle.smn.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VinhLe on 5/6/2017.
 */
public class LoginRequest extends BaseRequest {

    private String email;
    private String password;

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
