package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnImportDetailEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by VinhLe on 6/9/2017.
 */
@Component
public class ImportDetailRepository extends SQLRepository<SmnImportDetailEntity, Integer> {

    public List<SmnImportDetailEntity> getImportDetailByBillId(Integer importId) throws Exception {
        return ExecStoreProc(SmnImportDetailEntity.class, "import_detail_get_by_import_id",
                new Argument("_import_id", importId, Integer.class));
    }

    @Override
    public SmnImportDetailEntity save(SmnImportDetailEntity e) throws Exception {
        Session currentSession = GetSessionFactory().openSession();
        Transaction transaction = currentSession.beginTransaction();

        currentSession.save(e);

        transaction.commit();
        currentSession.close();
        return e;
    }

    @Override
    public SmnImportDetailEntity update(SmnImportDetailEntity e) throws Exception {
        return ExecuteScalar(SmnImportDetailEntity.class, "import_detail_update",
                new Argument("_import_detail_id", e.getImportDetailId(), Integer.class),
                new Argument("_import_id", e.getImportId(), Integer.class),
                new Argument("_product_id", e.getProductId(), Integer.class),
                new Argument("_product_quantity", e.getProductQuantity(), Integer.class),
                new Argument("_product_cost", e.getProductCost(), Long.class),
                new Argument("_description", e.getDescription(), String.class),
                new Argument("_is_active", e.getIsActive(), Byte.class));
    }

    @Override
    public Iterable<SmnImportDetailEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnImportDetailEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnImportDetailEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnImportDetailEntity> findByText(String text) throws Exception {
        return null;
    }
}
