package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 6/12/2017.
 */
public class UpdateImportDetailResponse implements Serializable {

    private Integer importDetailId;
    private String uid;

    public UpdateImportDetailResponse(Integer importDetailId, String uid) {
        this.importDetailId = importDetailId;
        this.uid = uid;
    }

    @JsonProperty("import_detail_id")
    public Integer getImportDetailId() {
        return importDetailId;
    }

    public void setImportDetailId(Integer importDetailId) {
        this.importDetailId = importDetailId;
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
