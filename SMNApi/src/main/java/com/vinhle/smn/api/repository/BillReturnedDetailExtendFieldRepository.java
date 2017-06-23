package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.custom.SmnBillReturnedExtendFieldEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by VinhLe on 6/8/2017.
 */
@Component
public class BillReturnedDetailExtendFieldRepository extends SQLRepository<SmnBillReturnedExtendFieldEntity, Integer> {

    public List<SmnBillReturnedExtendFieldEntity> getBillReturnedByBillId(Integer billId) throws Exception {
        return ExecStoreProc(SmnBillReturnedExtendFieldEntity.class, "bill_returned_detail_extend_field_by_bill_id",
                new Argument("_bill_id", billId, Integer.class));
    }


    @Override
    public SmnBillReturnedExtendFieldEntity save(SmnBillReturnedExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public SmnBillReturnedExtendFieldEntity update(SmnBillReturnedExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnBillReturnedExtendFieldEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnBillReturnedExtendFieldEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnBillReturnedExtendFieldEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnBillReturnedExtendFieldEntity> findByText(String text) throws Exception {
        return null;
    }
}
