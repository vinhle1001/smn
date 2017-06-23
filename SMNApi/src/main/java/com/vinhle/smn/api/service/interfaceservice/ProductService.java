package com.vinhle.smn.api.service.interfaceservice;

import com.vinhle.smn.api.model.request.ProductOfAgencyRequest;
import com.vinhle.smn.api.model.request.ProductRequest;
import com.vinhle.smn.api.model.response.*;
import com.vinhle.smn.api.model.shared.ProductOfAgencyModel;

import java.util.List;

/**
 * Created by VinhLe on 5/11/2017.
 */
public interface ProductService {

    UpdateProductResponse saveProduct(String method, ProductRequest model);
    ApiResponse saveProductOfAgency(String method, ProductOfAgencyRequest model);

    ProductResponse getById(String method, Integer id);
    List<ProductSearchResponse> getByText(String method, String text);
    List<ProductSearchResponse> getAll(String method);
    List<ProductOfAgencyResponse> getByAgency(String method, Integer agencyId);
    ProductOfAgencyModel getProductOfAgency(String method, Integer productId, Integer agencyId);

}
