package com.vinhle.smn.api.service.implservice;

import com.vinhle.smn.api.common.StringHelper;
import com.vinhle.smn.api.model.request.ProductOfAgencyRequest;
import com.vinhle.smn.api.model.request.ProductRequest;
import com.vinhle.smn.api.model.response.*;
import com.vinhle.smn.api.model.shared.ProductOfAgencyModel;
import com.vinhle.smn.api.model.shared.ResourceModel;
import com.vinhle.smn.api.model.sql.SmnAgencyProductEntity;
import com.vinhle.smn.api.model.sql.SmnProductEntity;
import com.vinhle.smn.api.model.sql.custom.SmnProductExtendFieldEntity;
import com.vinhle.smn.api.repository.AgencyProductRepository;
import com.vinhle.smn.api.repository.ProductRepository;
import com.vinhle.smn.api.repository.ProductExtendFieldRepository;
import com.vinhle.smn.api.service.interfaceservice.AgencyProductService;
import com.vinhle.smn.api.service.interfaceservice.MqttService;
import com.vinhle.smn.api.service.interfaceservice.ProductService;
import com.vinhle.smn.api.setting.AppSetting;
import com.vinhle.smn.api.setting.MqttSetting;
import com.vinhle.smn.api.setting.RedisKeyEntity;
import com.vinhle.smn.api.setting.UrlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by VinhLe on 5/11/2017.
 */
@Service
public class ProductServiceImpl extends BaseService implements ProductService {

    private static final String DEFAULT_E_URL = "SMN_PRODUCT_API";

    /*----------------------------------- Variable $ProductService ---------------------------------------------*/

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AgencyProductRepository agencyProductRepository;

    @Autowired
    ProductExtendFieldRepository productExtendFieldRepository;


    @Autowired
    AgencyProductService agencyProductService;

    @Autowired
    MqttService mqttService;

    /*----------------------------------- Method $ProductService ---------------------------------------------*/

    @Override
    public UpdateProductResponse saveProduct(String method, ProductRequest model) {
        try {
            SmnProductEntity productEntity = new SmnProductEntity();
            productEntity.setProductId(model.getProductId());
            productEntity.setProductCode(StringHelper.generateSKU(model.getProductCode(), model.getProductType().getSign()));
            productEntity.setProductName(model.getProductName());
            productEntity.setProductPrice(model.getProductPrice());
            productEntity.setCostOfImport(model.getCostOfImport());
            productEntity.setCostOfOrder(model.getCostOfOrder());
            productEntity.setProductSize(model.getProductSize());
            productEntity.setProductTypeId(model.getProductType().getId());
            productEntity.setDescription(model.getDescription());
            productEntity.setIsActive(model.getIsActive());

            if (productEntity.getProductId() == null)
                productRepository.save(productEntity);
            else {
                productEntity = productRepository.update(productEntity);
            }

            // TODO: deleted writeCache of product
            String keySearchByText = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, UrlEntity.A_SEARCH_PRODUCT.toUpperCase(), RedisKeyEntity.E_PRODUCT_SEARCH_TEXT, RedisKeyEntity.E_STAR);
            String keyGetProductById = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, UrlEntity.A_GET_PRODUCT_BY_ID.toUpperCase(), RedisKeyEntity.E_PRODUCT_ID, String.valueOf(productEntity.getProductId()));

            deleteCache(keySearchByText);
            deleteCache(keyGetProductById);

            return new UpdateProductResponse(productEntity.getProductId(), productEntity.getProductCode());
        } catch (Exception e) {
            return new UpdateProductResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }

    @Override
    public ApiResponse saveProductOfAgency(String method, ProductOfAgencyRequest model) {
        try {
            SmnAgencyProductEntity agencyProductEntity = new SmnAgencyProductEntity();
            agencyProductEntity.setAgencyProductId(model.getAgencyProductId());
            agencyProductEntity.setProductId(model.getProductId());
            agencyProductEntity.setAgencyId(model.getAgencyId());
            agencyProductEntity.setProductQuantity(model.getProductQuantity());
            agencyProductEntity.setProductBeginningQuantity(model.getProductBeginningQuantity());
            agencyProductEntity.setIsActive(model.getIsActive());

            agencyProductRepository.save(agencyProductEntity);

            // TODO: deleted writeCache of agency_product
            String keyProductOfAgency = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, UrlEntity.A_GET_PRODUCT_OF_AGENCY.toUpperCase(), RedisKeyEntity.E_AGENCY_ID, String.valueOf(agencyProductEntity.getAgencyId()), RedisKeyEntity.E_PRODUCT_ID, String.valueOf(agencyProductEntity.getProductId()));
            String keyAllProductOfAgency = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, UrlEntity.A_GET_ALL_PRODUCT_OF_AGENCY.toUpperCase(), RedisKeyEntity.E_AGENCY_ID, String.valueOf(agencyProductEntity.getAgencyId()), RedisKeyEntity.E_STAR);

            deleteCache(keyProductOfAgency);
            deleteCache(keyAllProductOfAgency);

            // TODO: publish product of agency
            mqttService.publish(StringHelper.ConcatWithSplit(MqttSetting.SPLIT_CHAR,MqttSetting.TOPIC_PRODUCT_OF_AGENCY, String.valueOf(model.getAgencyId())), new Function() {
                @Override
                public Object apply(Object o) {
                    return agencyProductService.getProductOfAgency((Integer) o);
                }
            }, model.getAgencyId());

            return new ApiResponse(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE);
        } catch (Exception e) {
            return new ApiResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }


    @Override
    public ProductResponse getById(String method, Integer id) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_PRODUCT_ID, String.valueOf(id));
            ProductResponse result = (ProductResponse) getCache(key);
            if (result == null) {
                SmnProductExtendFieldEntity productWithInformationEntity = productExtendFieldRepository.findById(id);
                List<SmnAgencyProductEntity> agencyProductEntities = agencyProductRepository.getAgencyProductByProductId(id);
                List<Integer> agencyIds = new ArrayList<>();
                if (agencyProductEntities != null && agencyProductEntities.size() > 0)
                    agencyIds = agencyProductEntities.stream().map(a -> a.getAgencyId()).collect(Collectors.toList());

                result = new ProductResponse(productWithInformationEntity.getProductId(), productWithInformationEntity.getProductCode(), productWithInformationEntity.getProductName(), productWithInformationEntity.getProductPrice(), productWithInformationEntity.getCostOfImport(), productWithInformationEntity.getCostOfOrder(), productWithInformationEntity.getProductSize(),
                        new ResourceModel(productWithInformationEntity.getProductTypeId(), productWithInformationEntity.getProductName(), productWithInformationEntity.getProductTypeIcon(), productWithInformationEntity.getProductTypeNotation()),
                        productWithInformationEntity.getDescription(), productWithInformationEntity.getIsActive(),
                        agencyIds);
                writeCache(key, result);
            }
            return result;
        } catch (Exception e) {
            return new ProductResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }

    @Override
    public List<ProductSearchResponse> getByText(String method, String text) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_PRODUCT_SEARCH_TEXT, text);
            List<ProductSearchResponse> result = (List<ProductSearchResponse>) getCache(key);
            if (result == null) {
                List<ProductSearchResponse> responses = new ArrayList<>();
                List<SmnProductExtendFieldEntity> productWithInformationEntities = (List<SmnProductExtendFieldEntity>) productExtendFieldRepository.findByText(text);
                productWithInformationEntities.forEach(p -> responses.add(new ProductSearchResponse(p.getProductId(), p.getProductName(), p.getProductPrice(), p.getProductTypeName(), p.getProductTypeIcon(), p.getIsActive())));
                result = responses;
                writeCache(key, result);
            }
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<ProductSearchResponse> getAll(String method) {
        List<ProductSearchResponse> result = new ArrayList<>();
        try {
            List<SmnProductExtendFieldEntity> productWithInformationEntities = (List<SmnProductExtendFieldEntity>) productExtendFieldRepository.findAll();
            productWithInformationEntities.forEach(p -> result.add(new ProductSearchResponse(p.getProductId(), p.getProductName(), p.getProductPrice(), p.getProductTypeName(), p.getProductTypeIcon(), p.getIsActive())));
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public List<ProductOfAgencyResponse> getByAgency(String method, Integer agencyId) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_AGENCY_ID, String.valueOf(agencyId));
            List<ProductOfAgencyResponse> result = (List<ProductOfAgencyResponse>) getCache(key);
            if (result == null) {
                List<ProductOfAgencyResponse> responses = new ArrayList<>();
                List<SmnProductExtendFieldEntity> productWithInformationEntities = (List<SmnProductExtendFieldEntity>) productExtendFieldRepository.getByAgencyId(agencyId);
                productWithInformationEntities.forEach(p -> responses.add(new ProductOfAgencyResponse(p.getProductId(), StringHelper.Concat(p.getProductName(), " ", p.getProductSize()), p.getProductPrice(), p.getCostOfImport(), p.getProductQuantity(), p.getIsActive())));
                result = responses;
                writeCache(key, result);
            }

            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public ProductOfAgencyModel getProductOfAgency(String method, Integer productId, Integer agencyId) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_AGENCY_ID, String.valueOf(agencyId), RedisKeyEntity.E_PRODUCT_ID, String.valueOf(productId));
            ProductOfAgencyModel result = (ProductOfAgencyModel) getCache(key);
            if (result == null) {
                SmnAgencyProductEntity agencyProductEntity = agencyProductRepository.getProductOfAgency(productId, agencyId);
                result = new ProductOfAgencyModel(agencyProductEntity.getAgencyProductId(), agencyProductEntity.getAgencyId(), agencyProductEntity.getProductId(), agencyProductEntity.getProductQuantity(), agencyProductEntity.getProductBeginningQuantity(), agencyProductEntity.getIsActive());
                writeCache(key, result);
            }
            return result;
        } catch (Exception e) {
            return new ProductOfAgencyModel();
        }
    }

}
