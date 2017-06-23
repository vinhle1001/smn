package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnImportEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * Created by VinhLe on 6/9/2017.
 */
@Component
public class ImportRepository extends SQLRepository<SmnImportEntity, Integer> {

    @Override
    public SmnImportEntity save(SmnImportEntity e) throws Exception {
        Session currentSession = GetSessionFactory().openSession();
        Transaction transaction = currentSession.beginTransaction();

        currentSession.save(e);

        transaction.commit();
        currentSession.close();
        return e;
    }

    @Override
    public SmnImportEntity update(SmnImportEntity e) throws Exception {
        return ExecuteScalar(SmnImportEntity.class, "import_update",
                new Argument("_import_id", e.getImportId(), Integer.class),
                new Argument("_supplier_id", e.getSupplierId(), Integer.class),
                new Argument("_agency_id", e.getAgencyId(), Integer.class),
                new Argument("_import_cost", e.getImportCost(), Long.class),
                new Argument("_import_debt", e.getImportDebt(), Long.class),
                new Argument("_description", e.getDescription(), String.class),
                new Argument("_is_active", e.getIsActive(), Byte.class));
    }

    @Override
    public Iterable<SmnImportEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnImportEntity findById(Integer integer) throws Exception {
        return ExecuteScalar(SmnImportEntity.class, "import_get_by_id",
                new Argument("_import_id", integer, Integer.class));
    }

    @Override
    public Iterable<SmnImportEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnImportEntity> findByText(String text) throws Exception {
        return null;
    }
}
