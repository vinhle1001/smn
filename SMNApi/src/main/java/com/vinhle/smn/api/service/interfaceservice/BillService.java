package com.vinhle.smn.api.service.interfaceservice;

import com.vinhle.smn.api.model.request.*;
import com.vinhle.smn.api.model.response.*;
import com.vinhle.smn.api.model.response.BillSearchResponse;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by VinhLe on 5/15/2017.
 */
public interface BillService {

    BillResponse getBillByCompositedId(String method, Integer customerId, Integer billId);
    BillReturnedResponse getBillReturnedByBillId(String method, Integer customerId, Integer billId);
    UpdateBillResponse saveBill(String method, BillRequest model);
    List<BillSearchResponse> getByCustomerId(String method, Integer customerId);
    List<BillSearchResponse> getByText(String method, String text);
    List<BillSearchResponse> getByRangeDate(String method, Timestamp timeStart, Timestamp timeEnd);

    //Bill returned
    UpdateBillReturnedResponse saveBillReturned(String method, Integer billId, Long billRefundCost, List<BillReturnedDetailRequest> billReturnedDetails);

}
