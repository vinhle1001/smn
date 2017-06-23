package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnBillDetailEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by VinhLe on 5/15/2017.
 */
@Component
public class BillDetailRepository extends SQLRepository<SmnBillDetailEntity, Integer> {

    public List<SmnBillDetailEntity> getBillDetailByBillId(Integer billId) {
        return ExecStoreProc(SmnBillDetailEntity.class, "bill_detail_by_bill_id",
                new Argument("_bill_id", billId, Integer.class));
    }

    @Override
    public SmnBillDetailEntity save(SmnBillDetailEntity e) throws Exception {
        Session currentSession = GetSessionFactory().openSession();
        Transaction transaction = currentSession.beginTransaction();

        e.setProductBeginningQuantity(e.getProductQuantity());
        currentSession.save(e);

        transaction.commit();
        currentSession.close();
        return e;
    }

    @Override
    public SmnBillDetailEntity update(SmnBillDetailEntity e) throws Exception {
        Session currentSession = GetSessionFactory().openSession();
        Transaction transaction = currentSession.beginTransaction();

        currentSession.update(e);

        transaction.commit();
        currentSession.close();
        return e;
    }

    @Override
    public Iterable<SmnBillDetailEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnBillDetailEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnBillDetailEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnBillDetailEntity> findByText(String text) throws Exception {
        return null;
    }
}
