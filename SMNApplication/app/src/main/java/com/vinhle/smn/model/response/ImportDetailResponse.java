package com.vinhle.smn.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.model.shared.ResourceModel;

import java.io.Serializable;
import java.util.UUID;

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
    private String uid;

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
        if (productQuantity == null) return 0;
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @JsonProperty("product_cost")
    public Long getProductCost() {
        if (productCost == null) return 0L;
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

    @JsonIgnore
    public String getUid() {
        if (uid == null || uid.isEmpty())
            uid = UUID.randomUUID().toString();
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
