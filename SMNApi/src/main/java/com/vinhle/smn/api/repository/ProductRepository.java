package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnProductEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by VinhLe on 5/11/2017.
 */
@Component
public class ProductRepository extends SQLRepository<SmnProductEntity, Integer> {

    public List<SmnProductEntity> getByIds(String ids) {
        return ExecStoreProc(SmnProductEntity.class, "product_get_by_ids",
                new Argument("_product_ids", ids, String.class));
    }

    @Override
    public SmnProductEntity save(SmnProductEntity e) throws Exception {
//        return ExecuteScalar(SmnProductEntity.class, "product_save",
//                new Argument("_id", e.getProductId(), Integer.class),
//                new Argument("_product_code", e.getProductCode(), String.class),
//                new Argument("_product_name", e.getProductName(), String.class),
//                new Argument("_product_price", e.getProductPrice(), Long.class),
//                new Argument("_cost_of_import", e.getCostOfImport(), Long.class),
//                new Argument("_cost_of_order", e.getCostOfOrder(), Long.class),
//                new Argument("_product_size", e.getProductSize(), String.class),
//                new Argument("_product_type_id", e.getProductTypeId(), Integer.class),
//                new Argument("_description", e.getDescription(), String.class),
//                new Argument("_is_active", e.getIsActive(), byte.class));
        Session currentSession = GetSessionFactory().openSession();
        Transaction transaction = currentSession.beginTransaction();

        currentSession.save(e);

        transaction.commit();
        currentSession.close();
        return e;
    }

    @Override
    public SmnProductEntity update(SmnProductEntity e) throws Exception {
        return ExecuteScalar(SmnProductEntity.class, "product_save",
                new Argument("_id", e.getProductId(), Integer.class),
                new Argument("_product_code", e.getProductCode(), String.class),
                new Argument("_product_name", e.getProductName(), String.class),
                new Argument("_product_price", e.getProductPrice(), Long.class),
                new Argument("_cost_of_import", e.getCostOfImport(), Long.class),
                new Argument("_cost_of_order", e.getCostOfOrder(), Long.class),
                new Argument("_product_size", e.getProductSize(), String.class),
                new Argument("_product_type_id", e.getProductTypeId(), Integer.class),
                new Argument("_description", e.getDescription(), String.class),
                new Argument("_is_active", e.getIsActive(), byte.class));
    }

    @Override
    public Iterable<SmnProductEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnProductEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnProductEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnProductEntity> findByText(String text) throws Exception {
        return null;
    }
}
