package com.vinhle.smn.api.service.interfaceservice;

import com.vinhle.smn.api.model.request.ImportRequest;
import com.vinhle.smn.api.model.request.SupplierRequest;
import com.vinhle.smn.api.model.response.*;

import java.util.List;

/**
 * Created by VinhLe on 5/29/2017.
 */
public interface SupplierService {

    List<SupplierSearchResponse> searchSupplier(String method, String text);
    SupplierResponse getSupplierById(String method, Integer id);
    ImportResponse getImport(String method, Integer supplierId, Integer importId);
    List<ImportSearchResponse> getImportOfSupplier(String method, Integer supplierId);

    UpdateImportResponse saveImport(String method, ImportRequest model);

    UpdateSupplierResponse save(String method, SupplierRequest model);
}
