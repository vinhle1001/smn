package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.custom.SmnImportExtendFieldEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by VinhLe on 6/13/2017.
 */
@Component
public class ImportExtendFieldRepository extends SQLRepository<SmnImportExtendFieldEntity, Integer> {

    public SmnImportExtendFieldEntity getImportByImportId(Integer importId) throws Exception {
        return ExecuteScalar(SmnImportExtendFieldEntity.class, "import_extend_field_by_import_id",
                new Argument("_supplier_id", importId, Integer.class));
    }

    public List<SmnImportExtendFieldEntity> getImportBySupplierId(Integer supplierId) throws Exception {
        return ExecStoreProc(SmnImportExtendFieldEntity.class, "import_extend_field_by_supplier_id",
                new Argument("_supplier_id", supplierId, Integer.class));
    }

    @Override
    public SmnImportExtendFieldEntity save(SmnImportExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public SmnImportExtendFieldEntity update(SmnImportExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnImportExtendFieldEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnImportExtendFieldEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnImportExtendFieldEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnImportExtendFieldEntity> findByText(String text) throws Exception {
        return null;
    }
}
