package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnBillEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * Created by VinhLe on 5/15/2017.
 */
@Component
public class BillRepository extends SQLRepository<SmnBillEntity, Integer> {

    @Override
    public SmnBillEntity save(SmnBillEntity e) throws Exception {
        Session currentSession = GetSessionFactory().openSession();
        Transaction transaction = currentSession.beginTransaction();

        currentSession.saveOrUpdate(e);

        transaction.commit();
        currentSession.close();
        return e;
    }

    @Override
    public SmnBillEntity update(SmnBillEntity e) throws Exception {
        return ExecuteScalar(SmnBillEntity.class, "bill_save",
                new Argument("_bill_id", e.getBillId(), Integer.class),
                new Argument("_bill_code", e.getBillCode(), String.class),
                new Argument("_customer_id", e.getCustomerId(), Integer.class),
                new Argument("_agency_id", e.getAgencyId(), Integer.class),
                new Argument("_bill_price", e.getBillPrice(), Long.class),
                new Argument("_bill_refund_cost", e.getBillRefundCost(), Long.class),
                new Argument("_bill_debt", e.getBillDebt(), Long.class),
                new Argument("_address", e.getAddress(), String.class),
                new Argument("_bill_step_id", e.getBillStepId(), Integer.class),
                new Argument("_bill_type_id", e.getBillTypeId(), Integer.class),
                new Argument("_description", e.getDescription(), String.class),
                new Argument("_province_id", e.getProvinceId(), Integer.class),
                new Argument("_district_id", e.getDistrictId(), Integer.class),
                new Argument("_ward_id", e.getWardId(), Integer.class),
                new Argument("_is_active", e.getIsActive(), byte.class));
    }

    @Override
    public Iterable<SmnBillEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnBillEntity findById(Integer integer) throws Exception {
        return ExecuteScalar(SmnBillEntity.class, "bill_get_by_bill_id",
                new Argument("_bill_id", integer, Integer.class));
    }

    @Override
    public Iterable<SmnBillEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnBillEntity> findByText(String text) throws Exception {
        return null;
    }
}
