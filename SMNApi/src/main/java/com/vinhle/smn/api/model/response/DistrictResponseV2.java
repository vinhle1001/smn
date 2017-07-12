package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by nguyenthuantan on 7/8/17.
 */
public class DistrictResponseV2 extends BaseResponse {
    private List<DistrictResponse> districts;

    public DistrictResponseV2(int code, String message, List<DistrictResponse> districts) {
        super(code, message);

        this.districts = districts;
    }

    @JsonProperty("districts")
    public List<DistrictResponse> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictResponse> provinces) {
        this.districts = districts;
    }
}
