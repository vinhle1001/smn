package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.custom.SmnSupplierExtendFieldEntity;
import org.springframework.stereotype.Component;

/**
 * Created by VinhLe on 5/31/2017.
 */
@Component
public class SupplierExtendFieldRepository extends SQLRepository<SmnSupplierExtendFieldEntity, Integer> {

    @Override
    public SmnSupplierExtendFieldEntity save(SmnSupplierExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public SmnSupplierExtendFieldEntity update(SmnSupplierExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnSupplierExtendFieldEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnSupplierExtendFieldEntity findById(Integer integer) throws Exception {
        return ExecuteScalar(SmnSupplierExtendFieldEntity.class, "supplier_extend_filed_get_by_id",
                new Argument("_supplier_id", integer, Integer.class));
    }

    @Override
    public Iterable<SmnSupplierExtendFieldEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnSupplierExtendFieldEntity> findByText(String text) throws Exception {
        return null;
    }
}
