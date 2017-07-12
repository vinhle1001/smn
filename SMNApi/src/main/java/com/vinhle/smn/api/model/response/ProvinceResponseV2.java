package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by nguyenthuantan on 7/8/17.
 */
public class ProvinceResponseV2 extends BaseResponse {
    private List<ProvinceResponse> provinces;

    public ProvinceResponseV2(int code, String message, List<ProvinceResponse> provinces) {
        super(code, message);

        this.provinces = provinces;
    }

    @JsonProperty("provinces")
    public List<ProvinceResponse> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvinceResponse> provinces) {
        this.provinces = provinces;
    }
}
