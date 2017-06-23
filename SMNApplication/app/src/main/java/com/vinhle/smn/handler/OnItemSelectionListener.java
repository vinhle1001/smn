package com.vinhle.smn.handler;

/**
 * Created by VinhLe on 5/8/2017.
 */

public interface OnItemSelectionListener {

    void OnEditItem(Object customerId);
    void OnAddBillForCustomer(Object customerId);
    void OnShowListBillOfCustomer(Object customerId);

}
