package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.custom.SmnProductExtendFieldEntity;
import org.springframework.stereotype.Component;

/**
 * Created by VinhLe on 5/11/2017.
 */
@Component
public class ProductExtendFieldRepository extends SQLRepository<SmnProductExtendFieldEntity, Integer> {

    public Iterable<SmnProductExtendFieldEntity> getByAgencyId(Integer agencyId) throws Exception {
        return ExecStoreProc(SmnProductExtendFieldEntity.class, "product_get_by_agency_id",
                new Argument("_agency_id", agencyId, Integer.class));
    }

    @Override
    public SmnProductExtendFieldEntity save(SmnProductExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public SmnProductExtendFieldEntity update(SmnProductExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnProductExtendFieldEntity> findAll() throws Exception {
        return ExecStoreProc(SmnProductExtendFieldEntity.class, "product_get_all");
    }

    @Override
    public SmnProductExtendFieldEntity findById(Integer integer) throws Exception {
        return ExecuteScalar(SmnProductExtendFieldEntity.class, "product_get_by_id",
                new Argument("_id", integer, Integer.class));
    }

    @Override
    public Iterable<SmnProductExtendFieldEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnProductExtendFieldEntity> findByText(String text) throws Exception {
        return ExecStoreProc(SmnProductExtendFieldEntity.class, "product_get_by_text",
                new Argument("_text", text, String.class));
    }
}
