package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.api.model.shared.ResourceModel;

import java.util.List;

/**
 * Created by VinhLe on 6/11/2017.
 */
public class ImportResponse extends BaseResponse {

    private Integer importId;
    private Integer supplierId;
    private String supplierName;
    private String supplierPhone;
    private ResourceModel agency;
    private Long importCost;
    private Long importDebt;
    private List<ImportDetailResponse> importDetails;
    private String description;
    private Byte isActive;

    public ImportResponse(int code, String message) {
        super(code, message);
    }

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

    @JsonProperty("supplier_name")
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @JsonProperty("supplier_phone")
    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    @JsonProperty("agency")
    public ResourceModel getAgency() {
        return agency;
    }

    public void setAgency(ResourceModel agency) {
        this.agency = agency;
    }

    @JsonProperty("import_cost")
    public Long getImportCost() {
        return importCost;
    }

    public void setImportCost(Long importCost) {
        this.importCost = importCost;
    }

    @JsonProperty("import_debt")
    public Long getImportDebt() {
        return importDebt;
    }

    public void setImportDebt(Long importDebt) {
        this.importDebt = importDebt;
    }

    @JsonProperty("import_details")
    public List<ImportDetailResponse> getImportDetails() {
        return importDetails;
    }

    public void setImportDetails(List<ImportDetailResponse> importDetails) {
        this.importDetails = importDetails;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }
}
