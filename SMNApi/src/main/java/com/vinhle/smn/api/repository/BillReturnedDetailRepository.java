package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnBillReturnedDetailEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by VinhLe on 6/3/2017.
 */
@Component
public class BillReturnedDetailRepository extends SQLRepository<SmnBillReturnedDetailEntity, Integer> {

    public List<SmnBillReturnedDetailEntity> getBillReturnedDetailByBillId(Integer billId) throws Exception {
        return ExecStoreProc(SmnBillReturnedDetailEntity.class, "bill_returned_detail_get_by_bill_id",
                new Argument("_bill_id", billId, Integer.class));
    }

    @Override
    public SmnBillReturnedDetailEntity save(SmnBillReturnedDetailEntity e) throws Exception {
        Session currentSession = GetSessionFactory().openSession();
        Transaction transaction = currentSession.beginTransaction();

        currentSession.save(e);

        transaction.commit();
        currentSession.close();
        return e;
    }

    @Override
    public SmnBillReturnedDetailEntity update(SmnBillReturnedDetailEntity e) throws Exception {
        return ExecuteScalar(SmnBillReturnedDetailEntity.class, "bill_returned_detail_update",
                new Argument("_id", e.getBillReturnedDetailId(), Integer.class),
                new Argument("_bill_id", e.getBillId(), Integer.class),
                new Argument("_bill_detail_id", e.getBillDetailId(), Integer.class),
                new Argument("_product_returned_quantity", e.getProductReturnedQuantity(), Integer.class),
                new Argument("_refunded_cost", e.getRefundedCost(), Long.class),
                new Argument("_description", e.getDescription(), String.class));
    }

    @Override
    public Iterable<SmnBillReturnedDetailEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnBillReturnedDetailEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnBillReturnedDetailEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnBillReturnedDetailEntity> findByText(String text) throws Exception {
        return null;
    }
}
