package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.custom.SmnImportDetailExtendFieldEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by VinhLe on 6/12/2017.
 */
@Component
public class ImportDetailExtendFieldRepository extends SQLRepository<SmnImportDetailExtendFieldEntity, Integer> {

    public List<SmnImportDetailExtendFieldEntity> getByImportId(Integer importId) throws Exception {
        return ExecStoreProc(SmnImportDetailExtendFieldEntity.class, "import_detail_extend_field_by_bill_id",
                new Argument("_import_id", importId, Integer.class));
    }

    @Override
    public SmnImportDetailExtendFieldEntity save(SmnImportDetailExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public SmnImportDetailExtendFieldEntity update(SmnImportDetailExtendFieldEntity e) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnImportDetailExtendFieldEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnImportDetailExtendFieldEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnImportDetailExtendFieldEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnImportDetailExtendFieldEntity> findByText(String text) throws Exception {
        return null;
    }
}
