package com.vinhle.smn.api.controller;

import com.vinhle.smn.api.common.RelateDateTime;
import com.vinhle.smn.api.model.request.*;
import com.vinhle.smn.api.model.response.*;
import com.vinhle.smn.api.model.shared.ProductOfAgencyModel;
import com.vinhle.smn.api.service.implservice.ProductServiceImpl;
import com.vinhle.smn.api.service.interfaceservice.LogService;
import com.vinhle.smn.api.setting.LogSetting;
import com.vinhle.smn.api.setting.UrlEntity;
import com.vinhle.smn.api.springconfig.resolver.JsonBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by VinhLe on 5/11/2017.
 */
@RestController
@RequestMapping(UrlEntity.E_PRODUCT)
public class ProductController {

    /*----------------------------------- Variable $ProductController ---------------------------------------------*/

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    LogService logService;

    /*----------------------------------- Method $ProductController ---------------------------------------------*/

    @RequestMapping(value = UrlEntity.A_SAVE_PRODUCT, method = RequestMethod.POST)
    @ResponseBody
    UpdateProductResponse saveProduct(@JsonBody ProductRequest model) {
        long currentTime = System.currentTimeMillis();
        UpdateProductResponse response = productService.saveProduct(UrlEntity.A_SAVE_PRODUCT, model);

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_SAVE_PRODUCT, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_SAVE_PRODUCT_OF_AGENCY, method = RequestMethod.POST)
    @ResponseBody
    ApiResponse saveProductOfAgency(@JsonBody ProductOfAgencyRequest model) {
        long currentTime = System.currentTimeMillis();
        ApiResponse response = productService.saveProductOfAgency(UrlEntity.A_SAVE_PRODUCT_OF_AGENCY, model);

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_SAVE_PRODUCT_OF_AGENCY, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_PRODUCT_BY_ID, method = RequestMethod.POST)
    @ResponseBody
    ProductResponse getProductById(@JsonBody ProductIdRequest model) {
        long currentTime = System.currentTimeMillis();
        ProductResponse response = productService.getById(UrlEntity.A_GET_PRODUCT_BY_ID, model.getProductId());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_PRODUCT_BY_ID, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_SEARCH_PRODUCT, method = RequestMethod.POST)
    @ResponseBody
    List<ProductSearchResponse> searchProduct(@JsonBody TextRequest model) {
        long currentTime = System.currentTimeMillis();
        List<ProductSearchResponse> response = productService.getByText(UrlEntity.A_SEARCH_PRODUCT, model.getText());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_SEARCH_PRODUCT, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_ALL_PRODUCT, method = RequestMethod.POST)
    @ResponseBody
    List<ProductSearchResponse> getAllProduct(BaseRequest model) {
        long currentTime = System.currentTimeMillis();
        List<ProductSearchResponse> response =  productService.getAll(UrlEntity.A_GET_ALL_PRODUCT);

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ALL_PRODUCT, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_PRODUCT_OF_AGENCY, method = RequestMethod.POST)
    @ResponseBody
    ProductOfAgencyModel getProductOfAgency(@JsonBody CompositedIdAgencyProductRequest model) {
        long currentTime = System.currentTimeMillis();
        ProductOfAgencyModel response = productService.getProductOfAgency(UrlEntity.A_GET_PRODUCT_OF_AGENCY, model.getProductId(), model.getAgencyId());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_PRODUCT_OF_AGENCY, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }

    @RequestMapping(value = UrlEntity.A_GET_ALL_PRODUCT_OF_AGENCY, method = RequestMethod.POST)
    @ResponseBody
    List<ProductOfAgencyResponse> getAllProductOfAgency(@JsonBody AgencyIdRequest model) {
        long currentTime = System.currentTimeMillis();
        List<ProductOfAgencyResponse> response = productService.getByAgency(UrlEntity.A_GET_ALL_PRODUCT_OF_AGENCY, model.getAgencyId());

        logService.save(LogSetting.LOG_TYPE_NORMAL, model.getSource(), UrlEntity.A_GET_ALL_PRODUCT_OF_AGENCY, model.getDeviceId(), model, response, RelateDateTime.SubLongTime(currentTime, System.currentTimeMillis()));

        return response;
    }
}
