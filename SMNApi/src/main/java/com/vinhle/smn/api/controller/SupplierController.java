package com.vinhle.smn.api.controller;

import com.vinhle.smn.api.common.RelateDateTime;
import com.vinhle.smn.api.model.request.*;
import com.vinhle.smn.api.model.response.*;
import com.vinhle.smn.api.service.interfaceservice.LogService;
import com.vinhle.smn.api.service.interfaceservice.SupplierService;
import com.vinhle.smn.api.setting.LogSetting;
import com.vinhle.smn.api.setting.UrlEntity;
import com.vinhle.smn.api.springconfig.resolver.JsonBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by VinhLe on 5/29/2017.
 */
@RestController
@RequestMapping(UrlEntity.E_SUPPLIER)
public class SupplierController {

    /*----------------------------------- Variable $SupplierController ---------------------------------------------*/

    @Autowired
    SupplierService supplierService;

    @Autowired
    LogService logService;

    /*----------------------------------- Method $SupplierController ---------------------------------------------*/

    @RequestMapping(value = UrlEntity.A_SEARCH_SUPPLIER, method = RequestMethod.POST)
    @ResponseBody
    public List<SupplierSearchResponse> searchSupplier(@JsonBody TextRequest model) {
        long currentTime = System.currentTimeMillis();
        List<SupplierSearchResponse> response = supplierService.searchSupplier(UrlEntity.A_SEARCH_SUPPLIER, model.getText());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_SEARCH_SUPPLIER, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }


    @RequestMapping(value = UrlEntity.A_GET_SUPPLIER_BY_ID, method = RequestMethod.POST)
    @ResponseBody
    public SupplierResponse getSupplierById(@JsonBody SupplierIdRequest model) {
        long currentTime = System.currentTimeMillis();
        SupplierResponse response = supplierService.getSupplierById(UrlEntity.A_GET_SUPPLIER_BY_ID, model.getSupplierId());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_SUPPLIER_BY_ID, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }


    @RequestMapping(value = UrlEntity.A_SAVE_SUPPLIER, method = RequestMethod.POST)
    @ResponseBody
    public UpdateSupplierResponse save(@JsonBody SupplierRequest model) {
        long currentTime = System.currentTimeMillis();
        UpdateSupplierResponse response = supplierService.save(UrlEntity.A_SAVE_SUPPLIER, model);

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_SAVE_SUPPLIER, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }


    @RequestMapping(value = UrlEntity.A_GET_IMPORT_BY_COMPOSITED_ID, method = RequestMethod.POST)
    @ResponseBody
    public ImportResponse getImportByCompositedId(@JsonBody CompositedIdSupplierImportRequest model) {
        long currentTime = System.currentTimeMillis();
        ImportResponse response = supplierService.getImport(UrlEntity.A_GET_IMPORT_BY_COMPOSITED_ID, model.getSupplierId(), model.getImportId());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_IMPORT_BY_COMPOSITED_ID, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_IMPORT_BY_SUPPLIER_ID, method = RequestMethod.POST)
    @ResponseBody
    public List<ImportSearchResponse> getImportBySupplierId(@JsonBody SupplierIdRequest model) {
        long currentTime = System.currentTimeMillis();
        List<ImportSearchResponse> response = supplierService.getImportOfSupplier(UrlEntity.A_GET_IMPORT_BY_SUPPLIER_ID, model.getSupplierId());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_IMPORT_BY_SUPPLIER_ID, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_SAVE_IMPORT, method = RequestMethod.POST)
    @ResponseBody
    public UpdateImportResponse saveImport(@JsonBody ImportRequest model) {
        long currentTime = System.currentTimeMillis();
        UpdateImportResponse response = supplierService.saveImport(UrlEntity.A_SAVE_IMPORT, model);

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_SAVE_SUPPLIER, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }
}
