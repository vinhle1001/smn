package com.vinhle.smn.api.controller;

import com.vinhle.smn.api.common.RelateDateTime;
import com.vinhle.smn.api.model.request.LoginRequest;
import com.vinhle.smn.api.model.request.TextRequest;
import com.vinhle.smn.api.model.response.LoginResponse;
import com.vinhle.smn.api.service.implservice.AccountServiceImpl;
import com.vinhle.smn.api.service.interfaceservice.LogService;
import com.vinhle.smn.api.setting.LogSetting;
import com.vinhle.smn.api.setting.UrlEntity;
import com.vinhle.smn.api.springconfig.resolver.JsonBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by VinhLe on 5/6/2017.
 */
@RestController
@RequestMapping(UrlEntity.E_ACCOUNT)
public class AccountController {

    /*----------------------------------- Variable $AccountController ---------------------------------------------*/

    @Autowired
    AccountServiceImpl accountService;

    @Autowired
    LogService logService;

    /*----------------------------------- Method $AccountController ---------------------------------------------*/

    @RequestMapping(value = UrlEntity.A_CHECK_LOGIN, method = RequestMethod.POST)
    @ResponseBody
    LoginResponse checkLogin(@JsonBody LoginRequest model) {
        long currentTime = System.currentTimeMillis();
        LoginResponse response = accountService.checkLogin(model.getEmail(), model.getPassword());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_CHECK_LOGIN, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_ACCOUNT_INFO_BY_SESSION, method = RequestMethod.POST)
    @ResponseBody
    LoginResponse getInfoBySession(@JsonBody TextRequest model) {
        long currentTime = System.currentTimeMillis();
        LoginResponse response = accountService.checkSessionInfo(model.getText());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ACCOUNT_INFO_BY_SESSION, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }
}
