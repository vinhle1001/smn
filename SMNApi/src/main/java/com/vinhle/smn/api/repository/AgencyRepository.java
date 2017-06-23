package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnAgencyEntity;
import org.springframework.stereotype.Component;

/**
 * Created by VinhLe on 5/13/2017.
 */
@Component
public class AgencyRepository extends SQLRepository<SmnAgencyEntity, Integer> {

    @Override
    public SmnAgencyEntity save(SmnAgencyEntity e) throws Exception {
        return null;
    }

    @Override
    public SmnAgencyEntity update(SmnAgencyEntity e) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnAgencyEntity> findAll() throws Exception {
        return ExecStoreProc(SmnAgencyEntity.class, "agency_get_all");
    }

    @Override
    public SmnAgencyEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnAgencyEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnAgencyEntity> findByText(String text) throws Exception {
        return null;
    }
}
