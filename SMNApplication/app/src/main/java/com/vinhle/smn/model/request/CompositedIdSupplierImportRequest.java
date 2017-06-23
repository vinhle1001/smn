package com.vinhle.smn.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VinhLe on 6/11/2017.
 */
public class CompositedIdSupplierImportRequest extends BaseRequest {

    private Integer importId;
    private Integer supplierId;

    @JsonProperty("import_id")
    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    @JsonProperty("supplier_id")
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
}
