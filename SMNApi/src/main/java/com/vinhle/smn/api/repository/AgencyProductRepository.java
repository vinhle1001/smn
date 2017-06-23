package com.vinhle.smn.api.repository;

import com.vinhle.server.framework.internal.sql.Argument;
import com.vinhle.server.framework.internal.sql.SQLRepository;
import com.vinhle.smn.api.model.sql.SmnAgencyProductEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by VinhLe on 5/13/2017.
 */
@Component
public class AgencyProductRepository extends SQLRepository<SmnAgencyProductEntity, Integer> {

    public List<SmnAgencyProductEntity> getAgencyProductByAgencyIdProductIds(Integer agencyId, String productIds) {
        return ExecStoreProc(SmnAgencyProductEntity.class, "agency_product_by_agency_id_product_ids",
                new Argument("_agency_id", agencyId, Integer.class),
                new Argument("_product_ids", productIds, String.class));
    }

    public List<SmnAgencyProductEntity> getAgencyProductByProductId(Integer productId) {
        return ExecStoreProc(SmnAgencyProductEntity.class, "agency_product_by_product_id",
                new Argument("_product_id", productId, Integer.class));
    }

    public SmnAgencyProductEntity getProductOfAgency(Integer productId, Integer agencyId) {
        return ExecuteScalar(SmnAgencyProductEntity.class, "agency_product_by_product_id_and_agency_id",
                new Argument("_agency_id", agencyId, Integer.class),
                new Argument("_product_id", productId, Integer.class));
    }

    @Override
    public SmnAgencyProductEntity save(SmnAgencyProductEntity e) throws Exception {
        return ExecuteScalar(SmnAgencyProductEntity.class, "agency_product_save",
                new Argument("_id", e.getAgencyProductId(), Integer.class),
                new Argument("_agency_id", e.getAgencyId(), Integer.class),
                new Argument("_product_id", e.getProductId(), Integer.class),
                new Argument("_product_quantity", e.getProductQuantity(), Integer.class),
                new Argument("_product_beginning_quantity", e.getProductBeginningQuantity(), Integer.class),
                new Argument("_is_active", e.getIsActive(), byte.class));
    }

    @Override
    public SmnAgencyProductEntity update(SmnAgencyProductEntity e) throws Exception {
        return ExecuteScalar(SmnAgencyProductEntity.class, "agency_product_save",
                new Argument("_id", e.getAgencyProductId(), Integer.class),
                new Argument("_agency_id", e.getAgencyId(), Integer.class),
                new Argument("_product_id", e.getProductId(), Integer.class),
                new Argument("_product_quantity", e.getProductQuantity(), Integer.class),
                new Argument("_product_beginning_quantity", e.getProductBeginningQuantity(), Integer.class),
                new Argument("_is_active", e.getIsActive(), byte.class));
    }

    @Override
    public Iterable<SmnAgencyProductEntity> findAll() throws Exception {
        return null;
    }

    @Override
    public SmnAgencyProductEntity findById(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Iterable<SmnAgencyProductEntity> findByIds(Integer[] integers) throws Exception {
        return null;
    }

    @Override
    public boolean exists(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Iterable<SmnAgencyProductEntity> findByText(String text) throws Exception {
        return null;
    }
}
