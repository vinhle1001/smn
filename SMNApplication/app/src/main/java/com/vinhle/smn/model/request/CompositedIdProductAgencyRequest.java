package com.vinhle.smn.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 5/13/2017.
 */
public class CompositedIdProductAgencyRequest extends BaseRequest {

    private Integer productId;
    private Integer agencyId;

    public CompositedIdProductAgencyRequest(Integer productId, Integer agencyId) {
        this.productId = productId;
        this.agencyId = agencyId;
    }

    @JsonProperty("product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("agency_id")
    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }
}
