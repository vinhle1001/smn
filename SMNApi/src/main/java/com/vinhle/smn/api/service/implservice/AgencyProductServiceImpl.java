package com.vinhle.smn.api.service.implservice;

import com.vinhle.smn.api.common.StringHelper;
import com.vinhle.smn.api.model.response.ProductOfAgencyResponse;
import com.vinhle.smn.api.model.sql.custom.SmnProductExtendFieldEntity;
import com.vinhle.smn.api.repository.ProductExtendFieldRepository;
import com.vinhle.smn.api.service.interfaceservice.AgencyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhLe on 6/21/2017.
 */
@Service
public class AgencyProductServiceImpl implements AgencyProductService {

    /*----------------------------------- Variable $AgencyProductService ---------------------------------------------*/

    @Autowired
    ProductExtendFieldRepository productExtendFieldRepository;

    /*----------------------------------- Method $AgencyProductService ---------------------------------------------*/


    @Override
    public List<ProductOfAgencyResponse> getProductOfAgency(Integer agencyId) {
        try {
            List<ProductOfAgencyResponse> responses = new ArrayList<>();
            List<SmnProductExtendFieldEntity> productExtendFieldEntities = (List<SmnProductExtendFieldEntity>) productExtendFieldRepository.getByAgencyId(agencyId);
            productExtendFieldEntities.forEach(p -> responses.add(new ProductOfAgencyResponse(p.getProductId(), StringHelper.Concat(p.getProductName(), " ", p.getProductSize()), p.getProductPrice(), p.getCostOfImport(), p.getProductQuantity(), p.getIsActive())));
            return responses;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
