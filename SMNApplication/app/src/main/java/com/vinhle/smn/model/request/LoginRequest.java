package com.vinhle.smn.model.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.annotation.RequestProperty;

/**
 * Created by VinhLe on 5/7/2017.
 */
public class LoginRequest extends BaseRequest {

    private String email;
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @JsonProperty("email")
    @RequestProperty(minLength = 5, pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$", fieldRequired = "email", contentRequired = "")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("password")
    @RequestProperty(minLength = 5, fieldRequired = "password", contentRequired = "")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
