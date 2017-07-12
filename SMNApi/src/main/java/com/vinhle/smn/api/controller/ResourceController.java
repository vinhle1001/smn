package com.vinhle.smn.api.controller;

import com.vinhle.smn.api.common.RelateDateTime;
import com.vinhle.smn.api.model.request.BaseRequest;
import com.vinhle.smn.api.model.request.IntegerIdRequest;
import com.vinhle.smn.api.model.response.*;
import com.vinhle.smn.api.service.implservice.ResourceServiceImpl;
import com.vinhle.smn.api.service.interfaceservice.LogService;
import com.vinhle.smn.api.setting.AppSetting;
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
 * Created by VinhLe on 5/9/2017.
 */
@RestController
@RequestMapping(value = UrlEntity.E_RESOURCE)
public class ResourceController {

    /*----------------------------------- Variable $ResourceController ---------------------------------------------*/

    @Autowired
    ResourceServiceImpl resourceService;

    @Autowired
    LogService logService;

    /*----------------------------------- Method $ResourceController ---------------------------------------------*/

    @RequestMapping(value = UrlEntity.A_GET_ALL_AGENCY, method = RequestMethod.POST)
    @ResponseBody
    List<AgencyResponse> getAllAgency(@JsonBody BaseRequest model) {
        long currentTime = System.currentTimeMillis();
        List<AgencyResponse> response = resourceService.getAllAgency();

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ALL_AGENCY, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_ALL_PROVINCE, method = RequestMethod.POST)
    @ResponseBody
    List<ProvinceResponse> getAllProvince(@JsonBody BaseRequest model) {
        long currentTime = System.currentTimeMillis();
        List<ProvinceResponse> response = resourceService.getAllProvince();

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ALL_PROVINCE, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_ALL_DISTRICT, method = RequestMethod.POST)
    @ResponseBody
    List<DistrictResponse> getAllDistrict(@JsonBody BaseRequest model) {
        long currentTime = System.currentTimeMillis();
        List<DistrictResponse> response = resourceService.getAllDistrict();

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ALL_DISTRICT, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_ALL_WARD, method = RequestMethod.POST)
    @ResponseBody
    List<WardResponse> getAllWard(@JsonBody BaseRequest model) {
        long currentTime = System.currentTimeMillis();
        List<WardResponse> response = resourceService.getAllWard();

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ALL_WARD, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_ALL_PRODUCT_TYPE, method = RequestMethod.POST)
    @ResponseBody
    List<ProductTypeResponse> getAllProductType(@JsonBody BaseRequest model) {
        long currentTime = System.currentTimeMillis();
        List<ProductTypeResponse> response = resourceService.getAllProductType();

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ALL_PRODUCT_TYPE, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_ALL_BILL_STEP, method = RequestMethod.POST)
    @ResponseBody
    List<BillStepResponse> getAllBillStep(@JsonBody BaseRequest model) {
        long currentTime = System.currentTimeMillis();
        List<BillStepResponse> response = resourceService.getAllBillStep();

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ALL_BILL_STEP, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    // Version 2
    @RequestMapping(value = UrlEntity.A_GET_ALL_AGENCY_V2, method = RequestMethod.POST)
    @ResponseBody
    AgencyResponseV2 getAllAgencyV2(@JsonBody BaseRequest model) {
        long currentTime = System.currentTimeMillis();
        List<AgencyResponse> response = resourceService.getAllAgency();

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ALL_AGENCY_V2, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return new AgencyResponseV2(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE, response);
    }

    @RequestMapping(value = UrlEntity.A_GET_ALL_PROVINCE_V2, method = RequestMethod.POST)
    @ResponseBody
    ProvinceResponseV2 getAllProvinceV2(@JsonBody BaseRequest model) {
        long currentTime = System.currentTimeMillis();
        List<ProvinceResponse> response = resourceService.getAllProvince();

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ALL_PROVINCE_V2, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return new ProvinceResponseV2(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE, response);
    }

    @RequestMapping(value = UrlEntity.A_GET_ALL_DISTRICT_V2, method = RequestMethod.POST)
    @ResponseBody
    DistrictResponseV2 getAllDistrictV2(@JsonBody BaseRequest model) {
        long currentTime = System.currentTimeMillis();
        List<DistrictResponse> response = resourceService.getAllDistrict();

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ALL_DISTRICT_V2, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return new DistrictResponseV2(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE, response);
    }

    @RequestMapping(value = UrlEntity.A_GET_ALL_WARD_V2, method = RequestMethod.POST)
    @ResponseBody
    WardResponseV2 getAllWardV2(@JsonBody BaseRequest model) {
        long currentTime = System.currentTimeMillis();
        List<WardResponse> response = resourceService.getAllWard();

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ALL_WARD_V2, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return new WardResponseV2(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE, response);
    }

    @RequestMapping(value = UrlEntity.A_GET_ALL_PRODUCT_TYPE_V2, method = RequestMethod.POST)
    @ResponseBody
    ProductTypeResponseV2 getAllProductTypeV2(@JsonBody BaseRequest model) {
        long currentTime = System.currentTimeMillis();
        List<ProductTypeResponse> response = resourceService.getAllProductType();

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ALL_PRODUCT_TYPE_V2, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return new ProductTypeResponseV2(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE, response);
    }

    @RequestMapping(value = UrlEntity.A_GET_DISTRICT_BY_PROVINCE_ID, method = RequestMethod.POST)
    @ResponseBody
    DistrictResponseV2 getDistrictByProvinceId(@JsonBody IntegerIdRequest model) {
        long currentTime = System.currentTimeMillis();
        List<DistrictResponse> response = resourceService.getDistrictByProvinceId(model.getId());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_DISTRICT_BY_PROVINCE_ID, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return new DistrictResponseV2(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE, response);
    }

    @RequestMapping(value = UrlEntity.A_GET_WARD_BY_DISTRICT_ID, method = RequestMethod.POST)
    @ResponseBody
    WardResponseV2 getWardByDistrictId(@JsonBody IntegerIdRequest model) {
        long currentTime = System.currentTimeMillis();
        List<WardResponse> response = resourceService.getWardByDistrictId(model.getId());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_WARD_BY_DISTRICT_ID, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return new WardResponseV2(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE, response);
    }
}
