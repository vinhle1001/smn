package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnAccountEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by VinhLe on 5/5/2017.
 */
@Component
public class AccountRepository extends SQLRepository<SmnAccountEntity, Integer> {

    /*----------------------------------- Method $AccountRepository ---------------------------------------------*/

    public SmnAccountEntity getByUsername(String username) throws Exception {
        return ExecuteScalar(SmnAccountEntity.class, "account_get_by_username",
                new Argument("_username", username, String.class));
    }

    /*----------------------------------- Method $SQLRepository ---------------------------------------------*/

    @Override
    public SmnAccountEntity save(SmnAccountEntity e) throws Exception {
        return ExecuteScalar(SmnAccountEntity.class, "account_save",
                new Argument("_id", e.getAccountId(), Integer.class),
                new Argument("_username", e.getUsername(), String.class),
                new Argument("_password", e.getPassword(), String.class),
                new Argument("_full_name", e.getFullName(), String.class),
                new Argument("_first_name", e.getFirstName(), String.class),
                new Argument("_last_name", e.getLastName(), String.class),
                new Argument("_phone_number", e.getPhoneNumber(), String.class),
                new Argument("_email", e.getEmail(), String.class),
                new Argument("_gender_id", e.getGenderId(), byte.class),
                new Argument("_account_type_id", e.getAccountTypeId(), byte.class),
                new Argument("_last_login_date", e.getLastLoginDate(), Timestamp.class),
                new Argument("_is_active", e.getIsActive(), byte.class));
    }

    @Override
    public SmnAccountEntity update(SmnAccountEntity e) throws Exception {
        return ExecuteScalar(SmnAccountEntity.class, "account_save",
                new Argument("_id", e.getAccountId(), Integer.class),
                new Argument("_username", e.getUsername(), String.class),
                new Argument("_password", e.getPassword(), String.class),
                new Argument("_full_name", e.getFullName(), String.class),
                new Argument("_first_name", e.getFirstName(), String.class),
                new Argument("_last_name", e.getLastName(), String.class),
                new Argument("_phone_number", e.getPhoneNumber(), String.class),
                new Argument("_email", e.getEmail(), String.class),
                new Argument("_gender_id", e.getGenderId(), byte.class),
                new Argument("_account_type_id", e.getAccountTypeId(), byte.class),
                new Argument("_last_login_date", e.getLastLoginDate(), Timestamp.class),
                new Argument("_is_active", e.getIsActive(), byte.class));
    }

    @Override
    public Iterable<SmnAccountEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnAccountEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnAccountEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnAccountEntity> findByText(String text) throws Exception {
        return null;
    }

}
