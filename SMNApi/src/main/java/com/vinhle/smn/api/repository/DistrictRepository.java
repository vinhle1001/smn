package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnDistrictEntity;
import org.springframework.stereotype.Component;

/**
 * Created by VinhLe on 5/9/2017.
 */
@Component
public class DistrictRepository extends SQLRepository<SmnDistrictEntity, Integer> {

    @Override
    public SmnDistrictEntity save(SmnDistrictEntity e) throws Exception {
        // TODO: not implement
        return null;
    }

    @Override
    public SmnDistrictEntity update(SmnDistrictEntity e) throws Exception {
        // TODO: not implement
        return null;
    }

    @Override
    public Iterable<SmnDistrictEntity> findAll() throws Exception {
        return ExecStoreProc(SmnDistrictEntity.class, "district_get_all");
    }

    @Override
    public SmnDistrictEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnDistrictEntity> findByIds(Integer[] integers) throws Exception {
        // TODO: not implement
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        // TODO: not implement
        return false;
    }

    @Override
    public Iterable<SmnDistrictEntity> findByText(String text) throws Exception {
        // TODO: not implement
        return null;
    }
}
