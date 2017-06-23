package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by VinhLe on 6/12/2017.
 */
public class UpdateImportResponse extends BaseResponse {

    private Integer importId;
//    private String importCode;
    private List<UpdateImportDetailResponse> importDetails;

    @JsonProperty("import_id")
    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

//    @JsonProperty("import_code")
//    public String getImportCode() {
//        return importCode;
//    }
//
//    public void setImportCode(String importCode) {
//        this.importCode = importCode;
//    }

    @JsonProperty("import_details")
    public List<UpdateImportDetailResponse> getImportDetails() {
        return importDetails;
    }

    public void setImportDetails(List<UpdateImportDetailResponse> importDetails) {
        this.importDetails = importDetails;
    }
}
