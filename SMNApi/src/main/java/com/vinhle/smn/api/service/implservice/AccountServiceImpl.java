package com.vinhle.smn.api.service.implservice;

import com.vinhle.smn.api.common.PasswordHelper;
import com.vinhle.smn.api.common.StringHelper;
import com.vinhle.smn.api.model.response.LoginResponse;
import com.vinhle.smn.api.model.sql.SmnAccountEntity;
import com.vinhle.smn.api.repository.AccountRepository;
import com.vinhle.smn.api.service.interfaceservice.AccountService;
import com.vinhle.smn.api.service.interfaceservice.LogService;
import com.vinhle.smn.api.setting.AppSetting;
import com.vinhle.smn.api.setting.RedisKeyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by VinhLe on 5/6/2017.
 */
@Service
public class AccountServiceImpl extends BaseService implements AccountService {

    /*----------------------------------- Variable $AccountService ---------------------------------------------*/

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LogService logService;

    /*----------------------------------- Method $AccountService ---------------------------------------------*/

    @Override
    public LoginResponse checkLogin(String email, String password) {
        try {
            String keyUserInfo = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, RedisKeyEntity.E_CUSTOMER_EMAIL, email, RedisKeyEntity.E_CUSTOMER_PASSWORD, password);

            LoginResponse result = (LoginResponse) getCache(keyUserInfo);
            if (result == null) {
                SmnAccountEntity accountEntity = accountRepository.getByUsername(email);
                if (accountEntity == null)
                    return new LoginResponse(AppSetting.WRONG_EMAIL_CODE, AppSetting.WRONG_EMAIL_MESSAGE);
                else {
                    if (!accountEntity.getPassword().equals(PasswordHelper.ParseToMD5(password))) {
                        return new LoginResponse(AppSetting.WRONG_PASSWORD_CODE, AppSetting.WRONG_PASSWORD_MESSAGE);
                    } else {
                        String session = StringHelper.encodeSession(accountEntity.getEmail());
                        String keySession = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, RedisKeyEntity.E_CUSTOMER_SESSION, session);
                        result = new LoginResponse(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE, accountEntity.getAccountId(), accountEntity.getEmail(), accountEntity.getFullName(), session);
                        writeCache(keyUserInfo, result, RedisKeyEntity.CACHE_SESSION_SECONDS);
                        writeCache(keySession, result, RedisKeyEntity.CACHE_SESSION_SECONDS);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            return new LoginResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }

    @Override
    public LoginResponse checkSessionInfo(String session) {
        try {
            String keySession = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, RedisKeyEntity.E_CUSTOMER_SESSION, session);
            LoginResponse result = (LoginResponse) getCache(keySession);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
