package com.vinhle.smn.api.controller;

import com.vinhle.smn.api.common.RelateDateTime;
import com.vinhle.smn.api.model.request.BaseRequest;
import com.vinhle.smn.api.model.request.CustomerIdRequest;
import com.vinhle.smn.api.model.request.CustomerRequest;
import com.vinhle.smn.api.model.request.TextRequest;
import com.vinhle.smn.api.model.response.CustomerResponse;
import com.vinhle.smn.api.model.response.CustomerSearchResponse;
import com.vinhle.smn.api.model.response.CustomerSearchResponseV2;
import com.vinhle.smn.api.model.response.UpdateCustomerResponse;
import com.vinhle.smn.api.service.implservice.CustomerServiceImpl;
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
 * Created by VinhLe on 5/7/2017.
 */
@RestController
@RequestMapping(UrlEntity.E_CUSTOMER)
public class CustomerController {

    /*----------------------------------- Variable $CustomerController ---------------------------------------------*/

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    LogService logService;

    /*----------------------------------- Method $CustomerController ---------------------------------------------*/

    @RequestMapping(value = UrlEntity.A_SEARCH_CUSTOMER, method = RequestMethod.POST)
    @ResponseBody
    List<CustomerSearchResponse> searchCustomer(@JsonBody TextRequest model) {
        long currentTime = System.currentTimeMillis();
        List<CustomerSearchResponse> response = customerService.getByText(UrlEntity.A_SEARCH_CUSTOMER, model.getText());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_SEARCH_CUSTOMER, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_SEARCH_CUSTOMER_V2, method = RequestMethod.POST)
    @ResponseBody
    CustomerSearchResponseV2 searchCustomerV2(@JsonBody TextRequest model) {
        long currentTime = System.currentTimeMillis();

        List<CustomerSearchResponse> customers = customerService.getByText(UrlEntity.A_SEARCH_CUSTOMER, model.getText());
        CustomerSearchResponseV2 response = new CustomerSearchResponseV2(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE, customers);

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_SEARCH_CUSTOMER, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }


    @RequestMapping(value = UrlEntity.A_FIND_ALL_CUSTOMER, method = RequestMethod.POST)
    @ResponseBody
    List<CustomerSearchResponse> findAllCustomer(BaseRequest model) {
        return customerService.getAll();
    }


    @RequestMapping(value = UrlEntity.A_SAVE_CUSTOMER, method = RequestMethod.POST)
    @ResponseBody
    UpdateCustomerResponse saveCustomer(@JsonBody CustomerRequest model) {
        long currentTime = System.currentTimeMillis();
        UpdateCustomerResponse response = customerService.saveCustomer(UrlEntity.A_SAVE_CUSTOMER, model);

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_SAVE_CUSTOMER, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }


    @RequestMapping(value = UrlEntity.A_GET_CUSTOMER_BY_ID, method = RequestMethod.POST)
    @ResponseBody
    CustomerResponse getCustomerById(@JsonBody CustomerIdRequest model) {
        long currentTime = System.currentTimeMillis();
        CustomerResponse response = customerService.getById(UrlEntity.A_GET_CUSTOMER_BY_ID, model.getCustomerId());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_CUSTOMER_BY_ID, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }
}
