package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.custom.SmnCustomerExtendFieldEntity;
import org.springframework.stereotype.Component;

/**
 * Created by VinhLe on 5/9/2017.
 */
@Component
public class CustomerExtendFieldRepository extends SQLRepository<SmnCustomerExtendFieldEntity, Integer> {

    @Override
    public SmnCustomerExtendFieldEntity save(SmnCustomerExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public SmnCustomerExtendFieldEntity update(SmnCustomerExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnCustomerExtendFieldEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnCustomerExtendFieldEntity findById(Integer integer) throws Exception {
        return ExecuteScalar(SmnCustomerExtendFieldEntity.class, "customer_get_by_id",
                new Argument("_id", integer, Integer.class));
    }

    @Override
    public Iterable<SmnCustomerExtendFieldEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnCustomerExtendFieldEntity> findByText(String text) throws Exception {
        return null;
    }
}
