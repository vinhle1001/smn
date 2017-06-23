package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnBillStepEntity;
import org.springframework.stereotype.Component;

/**
 * Created by VinhLe on 5/18/2017.
 */
@Component
public class BillStepRepository extends SQLRepository<SmnBillStepEntity, Integer> {

    @Override
    public SmnBillStepEntity save(SmnBillStepEntity e) throws Exception {
        return null;
    }

    @Override
    public SmnBillStepEntity update(SmnBillStepEntity e) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnBillStepEntity> findAll() throws Exception {
        return ExecStoreProc(SmnBillStepEntity.class, "bill_step_get_all");
    }

    @Override
    public SmnBillStepEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnBillStepEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnBillStepEntity> findByText(String text) throws Exception {
        return null;
    }
}
