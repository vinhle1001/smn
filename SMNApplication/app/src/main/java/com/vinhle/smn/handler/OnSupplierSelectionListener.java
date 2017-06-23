package com.vinhle.smn.handler;

import com.vinhle.smn.model.response.SupplierSearchResponse;

/**
 * Created by VinhLe on 5/29/2017.
 */

public interface OnSupplierSelectionListener {

    void OnEditItem(SupplierSearchResponse item);
    void OnCreateImport(SupplierSearchResponse item);
    void OnShowInvoices(SupplierSearchResponse item);

}
