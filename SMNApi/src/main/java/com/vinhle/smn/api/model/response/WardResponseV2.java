package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by nguyenthuantan on 7/8/17.
 */
public class WardResponseV2 extends BaseResponse {
    private List<WardResponse> wards;

    public WardResponseV2(int code, String message, List<WardResponse> wards) {
        super(code, message);

        this.wards = wards;
    }

    @JsonProperty("wards")
    public List<WardResponse> getWards() {
        return wards;
    }

    public void setWards(List<WardResponse> wards) {
        this.wards = wards;
    }
}
