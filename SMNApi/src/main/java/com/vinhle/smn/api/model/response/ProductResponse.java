package com.vinhle.smn.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.smn.api.model.shared.ResourceModel;
import com.vinhle.smn.api.setting.AppSetting;

import java.util.List;

/**
 * Created by VinhLe on 5/11/2017.
 */
public class ProductResponse extends BaseResponse {

    private Integer productId;
    private String productCode;
    private String productName;
    private Long productPrice;
    private Long costOfImport;
    private Long costOfOrder;
    private String productSize;
    private ResourceModel productType;
    private String description;
    private Byte isActive;
    private List<Integer> agencyIds;

    public ProductResponse(int code, String message) {
        super(code, message);
    }

    public ProductResponse(Integer productId, String productCode, String productName, Long productPrice, Long costOfImport, Long costOfOrder, String productSize, ResourceModel productType, String description, Byte isActive, List<Integer> agencyIds) {
        super(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE);

        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.costOfImport = costOfImport;
        this.costOfOrder = costOfOrder;
        this.productSize = productSize;
        this.productType = productType;
        this.description = description;
        this.isActive = isActive;
        this.agencyIds = agencyIds;
    }

    @JsonProperty("product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("product_code")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @JsonProperty("product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("product_price")
    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    @JsonProperty("cost_of_import")
    public Long getCostOfImport() {
        return costOfImport;
    }

    public void setCostOfImport(Long costOfImport) {
        this.costOfImport = costOfImport;
    }

    @JsonProperty("cost_of_order")
    public Long getCostOfOrder() {
        return costOfOrder;
    }

    public void setCostOfOrder(Long costOfOrder) {
        this.costOfOrder = costOfOrder;
    }

    @JsonProperty("product_size")
    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    @JsonProperty("product_type")
    public ResourceModel getProductType() {
        return productType;
    }

    public void setProductType(ResourceModel productType) {
        this.productType = productType;
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

    @JsonProperty("agency_ids")
    public List<Integer> getAgencyIds() {
        return agencyIds;
    }

    public void setAgencyIds(List<Integer> agencyIds) {
        this.agencyIds = agencyIds;
    }
}
