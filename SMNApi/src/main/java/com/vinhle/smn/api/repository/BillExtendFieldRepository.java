package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.custom.SmnBillDetailExtendFieldEntity;
import com.vinhle.smn.api.model.sql.custom.SmnBillExtendFieldEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by VinhLe on 5/15/2017.
 */
@Component
public class BillExtendFieldRepository extends SQLRepository<SmnBillExtendFieldEntity, Integer> {

    public Iterable<SmnBillExtendFieldEntity> getByRangeDate(Timestamp timeStart, Timestamp timeEnd) throws Exception {
        return ExecStoreProc(SmnBillExtendFieldEntity.class, "bill_extend_field_get_by_range_date",
                new Argument("_timeStart", timeStart, Timestamp.class),
                new Argument("_timeEnd", timeEnd, Timestamp.class));
    }

    public Iterable<SmnBillExtendFieldEntity> getByCustomerId(Integer customerId) throws Exception {
        return ExecStoreProc(SmnBillExtendFieldEntity.class, "bill_extend_field_by_customer_id",
                new Argument("_customer_id", customerId, Integer.class));
    }

    @Override
    public SmnBillExtendFieldEntity save(SmnBillExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public SmnBillExtendFieldEntity update(SmnBillExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnBillExtendFieldEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnBillExtendFieldEntity findById(Integer integer) throws Exception {
        return ExecuteScalar(SmnBillExtendFieldEntity.class, "bill_extend_field_by_id",
                new Argument("_bill_id", integer, Integer.class));
    }

    @Override
    public Iterable<SmnBillExtendFieldEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnBillExtendFieldEntity> findByText(String text) throws Exception {
        return ExecStoreProc(SmnBillExtendFieldEntity.class, "bill_extend_field_get_by_text",
                new Argument("_text", text, String.class));
    }
}
