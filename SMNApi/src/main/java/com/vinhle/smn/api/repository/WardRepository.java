package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnProvinceEntity;
import com.vinhle.smn.api.model.sql.SmnWardEntity;
import org.springframework.stereotype.Component;

/**
 * Created by VinhLe on 5/9/2017.
 */
@Component
public class WardRepository extends SQLRepository<SmnWardEntity, Integer> {

    @Override
    public SmnWardEntity save(SmnWardEntity e) throws Exception {
        // TODO: not implement
        return null;
    }

    @Override
    public SmnWardEntity update(SmnWardEntity e) throws Exception {
        // TODO: not implement
        return null;
    }

    @Override
    public Iterable<SmnWardEntity> findAll() throws Exception {
        return ExecStoreProc(SmnWardEntity.class, "ward_get_all");
    }

    @Override
    public SmnWardEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnWardEntity> findByIds(Integer[] integers) throws Exception {
        // TODO: not implement
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        // TODO: not implement
        return false;
    }

    @Override
    public Iterable<SmnWardEntity> findByText(String text) throws Exception {
        // TODO: not implement
        return null;
    }


}
