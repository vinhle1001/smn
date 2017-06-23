package com.vinhle.smn.api.service.interfaceservice;

import com.vinhle.smn.api.model.response.ProductOfAgencyResponse;

import java.util.List;

/**
 * Created by VinhLe on 6/21/2017.
 */
public interface AgencyProductService {

    List<ProductOfAgencyResponse> getProductOfAgency(Integer agencyId);

}
