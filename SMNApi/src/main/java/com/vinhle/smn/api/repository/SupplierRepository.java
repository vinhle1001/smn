package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnSupplierEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

/**
 * Created by VinhLe on 5/29/2017.
 */
@Component
public class SupplierRepository extends SQLRepository<SmnSupplierEntity, Integer> {

    @Override
    public SmnSupplierEntity save(SmnSupplierEntity e) throws Exception {
        Session currentSession = GetSessionFactory().openSession();
        Transaction transaction = currentSession.beginTransaction();

        currentSession.save(e);

        transaction.commit();
        currentSession.close();
        return null;
    }

    @Override
    public SmnSupplierEntity update(SmnSupplierEntity e) throws Exception {
        return ExecuteScalar(SmnSupplierEntity.class, "supplier_save",
                new Argument("_supplier_id", e.getSupplierId(), Integer.class),
                new Argument("_name", e.getName(), String.class),
                new Argument("_description", e.getDescription(), String.class),
                new Argument("_phone_number", e.getPhoneNumber(), String.class),
                new Argument("_email", e.getEmail(), String.class),
                new Argument("_address", e.getAddress(), String.class),
                new Argument("_province_id", e.getProvinceId(), Integer.class),
                new Argument("_district_id", e.getDistrictId(), Integer.class),
                new Argument("_ward_id", e.getWardId(), Integer.class),
                new Argument("_is_active", e.getIsActive(), Byte.class));
    }

    @Override
    public Iterable<SmnSupplierEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnSupplierEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnSupplierEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnSupplierEntity> findByText(String text) throws Exception {
        return ExecStoreProc(SmnSupplierEntity.class, "supplier_get_by_text",
                new Argument("_text", text, String.class));
    }
}
