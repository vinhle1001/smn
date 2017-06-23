package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.searchablespinner.BaseSearchableModel;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/13/2017.
 */
public class AgencyResponse extends BaseSearchableModel {

    private Integer agencyId;
    private String agencyCode;
    private String agencyName;

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

    @Override
    @JsonIgnore
    public Integer getId() {
        return agencyId;
    }

    @Override
    @JsonIgnore
    public String getDisplayText() {
        return agencyName;
    }

    @Override
    @JsonIgnore
    public String getSubTitle() {
        return null;
    }
}
