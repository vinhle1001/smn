package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.api.model.shared.ResourceModel;

import java.io.Serializable;

/**
 * Created by VinhLe on 6/11/2017.
 */
public class ImportDetailResponse implements Serializable {

    private Integer importDetailId;
    private ResourceModel product;
    private Integer productQuantity;
    private Long productCost;
    private String description;
    private Byte isActive;

    @JsonProperty("import_detail_id")
    public Integer getImportDetailId() {
        return importDetailId;
    }

    public void setImportDetailId(Integer importDetailId) {
        this.importDetailId = importDetailId;
    }

    @JsonProperty("product")
    public ResourceModel getProduct() {
        return product;
    }

    public void setProduct(ResourceModel product) {
        this.product = product;
    }

    @JsonProperty("product_quantity")
    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @JsonProperty("product_cost")
    public Long getProductCost() {
        return productCost;
    }

    public void setProductCost(Long productCost) {
        this.productCost = productCost;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }
}
