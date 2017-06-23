package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/13/2017.
 */
public class AgencyResponse implements Serializable {

    private Integer agencyId;
    private String agencyCode;
    private String agencyName;

    public AgencyResponse(Integer agencyId, String agencyCode, String agencyName) {
        this.agencyId = agencyId;
        this.agencyCode = agencyCode;
        this.agencyName = agencyName;
    }

    @JsonProperty("agency_id")
    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    @JsonProperty("agency_code")
    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    @JsonProperty("agency_name")
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
}
