package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.custom.SmnBillDetailExtendFieldEntity;
import org.springframework.stereotype.Component;


/**
 * Created by VinhLe on 5/17/2017.
 */
@Component
public class BillDetailExtendFieldRepository extends SQLRepository<SmnBillDetailExtendFieldEntity, Integer> {

    public Iterable<SmnBillDetailExtendFieldEntity> getByBillId(Integer billId) {
        return ExecStoreProc(SmnBillDetailExtendFieldEntity.class, "bill_detail_extend_field_by_bill_id",
                new Argument("_bill_id", billId, Integer.class));
    }

    @Override
    public SmnBillDetailExtendFieldEntity save(SmnBillDetailExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public SmnBillDetailExtendFieldEntity update(SmnBillDetailExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnBillDetailExtendFieldEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnBillDetailExtendFieldEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnBillDetailExtendFieldEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnBillDetailExtendFieldEntity> findByText(String text) throws Exception {
        return null;
    }
}
