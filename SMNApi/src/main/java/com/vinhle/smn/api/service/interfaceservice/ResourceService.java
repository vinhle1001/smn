package com.vinhle.smn.api.service.interfaceservice;

import com.vinhle.smn.api.model.response.*;

import java.util.List;

/**
 * Created by VinhLe on 5/9/2017.
 */
public interface ResourceService {

    List<AgencyResponse> getAllAgency();
    List<ProvinceResponse> getAllProvince();
    List<DistrictResponse> getAllDistrict();
    List<WardResponse> getAllWard();
    List<ProductTypeResponse> getAllProductType();
    List<BillStepResponse> getAllBillStep();

}
