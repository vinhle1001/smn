package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnCustomerEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by VinhLe on 5/7/2017.
 */
@Component
public class CustomerRepository extends SQLRepository<SmnCustomerEntity, Integer> {

    /*----------------------------------- Method $CustomerRepository ---------------------------------------------*/



    /*----------------------------------- Method $SQLRepository ---------------------------------------------*/

    @Override
    public SmnCustomerEntity save(SmnCustomerEntity e) throws Exception {
        return ExecuteScalar(SmnCustomerEntity.class, "customer_save",
                new Argument("_id", e.getCustomerId(), Integer.class),
                new Argument("_full_name", e.getFullName(), String.class),
                new Argument("_first_name", e.getFirstName(), String.class),
                new Argument("_last_name", e.getLastName(), String.class),
                new Argument("_email", e.getEmail(), String.class),
                new Argument("_facebook", e.getFacebook(), String.class),
                new Argument("_phone_number", e.getPhoneNumber(), String.class),
                new Argument("_gender_id", e.getGenderId(), byte.class),
                new Argument("_birthday", e.getBirthday(), Timestamp.class),
                new Argument("_address", e.getAddress(), String.class),
                new Argument("_province_id", e.getProvinceId(), Integer.class),
                new Argument("_district_id", e.getDistrictId(), Integer.class),
                new Argument("_ward_id", e.getWardId(), Integer.class),
                new Argument("_customer_type_id", e.getCustomerTypeId(), byte.class),
                new Argument("_customer_note", e.getCustomerNote(), String.class),
                new Argument("_is_active", e.getIsActive(), byte.class));
    }

    @Override
    public SmnCustomerEntity update(SmnCustomerEntity e) throws Exception {
        return ExecuteScalar(SmnCustomerEntity.class, "customer_save",
                new Argument("_id", e.getCustomerId(), Integer.class),
                new Argument("_full_name", e.getFullName(), String.class),
                new Argument("_first_name", e.getFirstName(), String.class),
                new Argument("_last_name", e.getLastName(), String.class),
                new Argument("_email", e.getEmail(), String.class),
                new Argument("_facebook", e.getFacebook(), String.class),
                new Argument("_phone_number", e.getPhoneNumber(), String.class),
                new Argument("_gender", e.getGenderId(), byte.class),
                new Argument("_birthday", e.getBirthday(), Timestamp.class),
                new Argument("_address", e.getAddress(), String.class),
                new Argument("_province_id", e.getProvinceId(), Integer.class),
                new Argument("_district_id", e.getDistrictId(), Integer.class),
                new Argument("_ward_id", e.getWardId(), Integer.class),
                new Argument("_customer_type_id", e.getCustomerTypeId(), byte.class),
                new Argument("_customer_note", e.getCustomerNote(), String.class),
                new Argument("_is_active", e.getIsActive(), byte.class));
    }

    @Override
    public Iterable<SmnCustomerEntity> findAll() throws Exception {
        return ExecStoreProc(SmnCustomerEntity.class, "customer_get_all");
    }

    @Override
    public SmnCustomerEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnCustomerEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnCustomerEntity> findByText(String text) throws Exception {
        return ExecStoreProc(SmnCustomerEntity.class, "customer_get_by_text",
                new Argument("_text", text, String.class));
    }
}
