package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnProvinceEntity;
import org.springframework.stereotype.Component;

/**
 * Created by VinhLe on 5/9/2017.
 */
@Component
public class ProvinceRepository extends SQLRepository<SmnProvinceEntity, Integer> {

    @Override
    public SmnProvinceEntity save(SmnProvinceEntity e) throws Exception {
        // TODO: not implement
        return null;
    }

    @Override
    public SmnProvinceEntity update(SmnProvinceEntity e) throws Exception {
        // TODO: not implement
        return null;
    }

    @Override
    public Iterable<SmnProvinceEntity> findAll() throws Exception {
        return ExecStoreProc(SmnProvinceEntity.class, "province_get_all");
    }

    @Override
    public SmnProvinceEntity findById(Integer integer) throws Exception {

        return null;
    }

    @Override
    public Iterable<SmnProvinceEntity> findByIds(Integer[] integers) throws Exception {
        // TODO: not implement
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        // TODO: not implement
        return false;
    }

    @Override
    public Iterable<SmnProvinceEntity> findByText(String text) throws Exception {
        // TODO: not implement
        return null;
    }
}
