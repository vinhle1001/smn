package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by nguyenthuantan on 7/8/17.
 */
public class AgencyResponseV2 extends BaseResponse {
    private List<AgencyResponse> agencies;

    public AgencyResponseV2(int code, String message, List<AgencyResponse> agencies) {
        super(code, message);

        this.agencies = agencies;
    }

    @JsonProperty("agencies")
    public List<AgencyResponse> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<AgencyResponse> agencies) {
        this.agencies = agencies;
    }
}
