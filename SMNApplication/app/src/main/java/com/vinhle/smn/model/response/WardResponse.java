package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.searchablespinner.BaseSearchableModel;
import com.vinhle.smn.setting.AppSetting;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/9/2017.
 */
public class WardResponse extends BaseSearchableModel {

    private Integer id;
    private String name;
    private String type;
    private Integer districtId;


    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("district_id")
    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    @Override
    @JsonIgnore
    public String getDisplayText() {
        return type + AppSetting.SPACE_CHAR + name;
    }

    @Override
    @JsonIgnore
    public String getSubTitle() {
        return null;
    }
}
