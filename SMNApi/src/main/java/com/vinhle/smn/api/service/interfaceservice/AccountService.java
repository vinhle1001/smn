package com.vinhle.smn.api.service.interfaceservice;

import com.vinhle.smn.api.model.response.LoginResponse;

/**
 * Created by VinhLe on 5/6/2017.
 */
public interface AccountService {

    LoginResponse checkLogin(String email, String password);
    LoginResponse checkSessionInfo(String session);

}
