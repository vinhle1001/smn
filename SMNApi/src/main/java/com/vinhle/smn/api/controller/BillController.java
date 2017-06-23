package com.vinhle.smn.api.controller;

import com.vinhle.smn.api.common.RelateDateTime;
import com.vinhle.smn.api.model.request.*;
import com.vinhle.smn.api.model.response.*;
import com.vinhle.smn.api.service.implservice.BillServiceImpl;
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
 * Created by VinhLe on 5/15/2017.
 */
@RestController
@RequestMapping(UrlEntity.E_BILL)
public class BillController {

    /*----------------------------------- Variable $BillController ---------------------------------------------*/

    @Autowired
    BillServiceImpl billService;

    @Autowired
    LogService logService;

    /*----------------------------------- Method $BillController ---------------------------------------------*/

    @RequestMapping(value = UrlEntity.A_SAVE_BILL, method = RequestMethod.POST)
    @ResponseBody
    UpdateBillResponse saveBill(@JsonBody BillRequest model) {
        long currentTime = System.currentTimeMillis();
        UpdateBillResponse response = billService.saveBill(UrlEntity.A_SAVE_BILL, model);

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_SAVE_BILL, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_BILL_BY_COMPOSITED_ID, method = RequestMethod.POST)
    @ResponseBody
    BillResponse getBillByCompositedId(@JsonBody CompositedIdCustomerBillRequest model) {
        long currentTime = System.currentTimeMillis();
        BillResponse response = billService.getBillByCompositedId(UrlEntity.A_GET_BILL_BY_COMPOSITED_ID, model.getCustomerId(), model.getBillId());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_BILL_BY_COMPOSITED_ID, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_BILL_BY_CUSTOMER_ID, method = RequestMethod.POST)
    @ResponseBody
    List<BillSearchResponse> getBillByCustomerId(@JsonBody CustomerIdRequest model) {
        long currentTime = System.currentTimeMillis();
        List<BillSearchResponse> response = billService.getByCustomerId(UrlEntity.A_GET_BILL_BY_CUSTOMER_ID, model.getCustomerId());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_BILL_BY_CUSTOMER_ID, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_SEARCH_BILL_TEXT, method = RequestMethod.POST)
    @ResponseBody
    List<BillSearchResponse> getBillByText(@JsonBody TextRequest model) {
        long currentTime = System.currentTimeMillis();
        List<BillSearchResponse> response = billService.getByText(UrlEntity.A_SEARCH_BILL_TEXT, model.getText());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_SEARCH_BILL_TEXT, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_SEARCH_BILL_RANGE_DATE, method = RequestMethod.POST)
    @ResponseBody
    List<BillSearchResponse> getBillByRangeDate(@JsonBody RangeDateRequest model) {
        long currentTime = System.currentTimeMillis();
        List<BillSearchResponse> response = billService.getByRangeDate(UrlEntity.A_SEARCH_BILL_RANGE_DATE, model.getTimeStart(), model.getTimeEnd());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_SEARCH_BILL_RANGE_DATE, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_SAVE_BILL_RETURNED, method = RequestMethod.POST)
    @ResponseBody
    UpdateBillReturnedResponse saveBillReturned(@JsonBody BillReturnedRequest model) {
        long currentTime = System.currentTimeMillis();
        UpdateBillReturnedResponse response = billService.saveBillReturned(UrlEntity.A_SAVE_BILL_RETURNED, model.getBillId(), model.getBillRefundCost(), model.getBillReturnedDetails());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_SAVE_BILL_RETURNED, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));
        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_BILL_RETURNED, method = RequestMethod.POST)
    @ResponseBody
    BillReturnedResponse getBillReturned(@JsonBody CompositedIdCustomerBillRequest model) {
        long currentTime = System.currentTimeMillis();
        BillReturnedResponse response = billService.getBillReturnedByBillId(UrlEntity.A_GET_BILL_RETURNED, model.getCustomerId(), model.getBillId());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_BILL_RETURNED, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));
        return response;
    }
}
