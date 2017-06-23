package com.vinhle.smn.api.controller;

import com.vinhle.smn.api.common.RelateDateTime;
import com.vinhle.smn.api.model.request.BaseRequest;
import com.vinhle.smn.api.model.response.*;
import com.vinhle.smn.api.service.implservice.ResourceServiceImpl;
import com.vinhle.smn.api.service.interfaceservice.LogService;
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
}
