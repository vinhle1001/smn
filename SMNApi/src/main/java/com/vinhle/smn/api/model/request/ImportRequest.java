package com.vinhle.smn.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by VinhLe on 6/10/2017.
 */
public class ImportRequest extends BaseRequest {

    private Integer importId;
//    private String importCode;
    private Integer supplierId;
    private Integer agencyId;
    private Long importCost;
    private Long importDebt;
    private String description;
    private List<ImportDetailRequest> importDetails;
    private Byte isActive;

    @JsonProperty("import_id")
    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    /*@JsonProperty("import_code")
    public String getImportCode() {
        return importCode;
    }

    public void setImportCode(String importCode) {
        this.importCode = importCode;
    }*/

    @JsonProperty("supplier_id")
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    @JsonProperty("agency_id")
    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
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

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("import_details")
    public List<ImportDetailRequest> getImportDetails() {
        return importDetails;
    }

    public void setImportDetails(List<ImportDetailRequest> importDetails) {
        this.importDetails = importDetails;
    }

    @JsonProperty("is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "ImportRequest{" +
                "importId=" + importId +
                ", supplierId=" + supplierId +
                ", agencyId=" + agencyId +
                ", importCost=" + importCost +
                ", importDebt=" + importDebt +
                ", description='" + description + '\'' +
                ", importDetails=" + importDetails +
                ", isActive=" + isActive +
                '}';
    }
}
