package com.vinhle.smn.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by VinhLe on 5/13/2017.
 */
public class ProductOfAgencyRequest extends BaseRequest {

    private Integer agencyProductId;
    private Integer agencyId;
    private Integer productId;
    private Integer productQuantity;
    private Integer productBeginningQuantity;
    private byte isActive;

    public ProductOfAgencyRequest() {
    }

    public ProductOfAgencyRequest(Integer agencyProductId, Integer agencyId, Integer productId, Integer productQuantity, Integer productBeginningQuantity, byte isActive) {
        this.agencyProductId = agencyProductId;
        this.agencyId = agencyId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.productBeginningQuantity = productBeginningQuantity;
        this.isActive = isActive;
    }

    @JsonProperty("agency_product_id")
    public Integer getAgencyProductId() {
        return agencyProductId;
    }

    public void setAgencyProductId(Integer agencyProductId) {
        this.agencyProductId = agencyProductId;
    }

    @JsonProperty("agency_id")
    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    @JsonProperty("product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("product_quantity")
    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @JsonProperty("product_beginning_quantity")
    public Integer getProductBeginningQuantity() {
        return productBeginningQuantity;
    }

    public void setProductBeginningQuantity(Integer productBeginningQuantity) {
        this.productBeginningQuantity = productBeginningQuantity;
    }

    @JsonProperty("is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }
}
