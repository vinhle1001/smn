package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.api.setting.AppSetting;

import java.util.List;

/**
 * Created by VinhLe on 6/12/2017.
 */
public class UpdateImportResponse extends BaseResponse {

    private Integer importId;
//    private String importCode;
    private List<UpdateImportDetailResponse> importDetails;

    public UpdateImportResponse(Integer importId, /*String importCode, */List<UpdateImportDetailResponse> importDetails) {
        super(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE);

        this.importId = importId;
//        this.importCode = importCode;
        this.importDetails = importDetails;
    }

    public UpdateImportResponse(int code, String message) {
        super(code, message);
    }

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

    @JsonProperty("import_details")
    public List<UpdateImportDetailResponse> getImportDetails() {
        return importDetails;
    }

    public void setImportDetails(List<UpdateImportDetailResponse> importDetails) {
        this.importDetails = importDetails;
    }
}
