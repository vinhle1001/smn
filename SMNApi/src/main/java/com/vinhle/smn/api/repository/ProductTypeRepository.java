package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnProductTypeEntity;
import org.springframework.stereotype.Component;

/**
 * Created by VinhLe on 5/12/2017.
 */
@Component
public class ProductTypeRepository extends SQLRepository<SmnProductTypeEntity, Integer> {

    @Override
    public SmnProductTypeEntity save(SmnProductTypeEntity e) throws Exception {
        return null;
    }

    @Override
    public SmnProductTypeEntity update(SmnProductTypeEntity e) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnProductTypeEntity> findAll() throws Exception {
        return ExecStoreProc(SmnProductTypeEntity.class, "product_type_get_all");
    }

    @Override
    public SmnProductTypeEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnProductTypeEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnProductTypeEntity> findByText(String text) throws Exception {
        return null;
    }
}
