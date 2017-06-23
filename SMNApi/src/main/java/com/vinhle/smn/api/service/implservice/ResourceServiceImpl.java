package com.vinhle.smn.api.service.implservice;

import com.vinhle.smn.api.common.StringHelper;
import com.vinhle.smn.api.model.response.*;
import com.vinhle.smn.api.model.sql.*;
import com.vinhle.smn.api.repository.*;
import com.vinhle.smn.api.service.interfaceservice.ResourceService;
import com.vinhle.smn.api.setting.RedisKeyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhLe on 5/9/2017.
 */
@Service
public class ResourceServiceImpl extends BaseService implements ResourceService {

    /*----------------------------------- Variable $ResourceService ---------------------------------------------*/

    private static final String DEFAULT_E_URL = "SMN_RESOURCE_API";

    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    BillStepRepository billStepRepository;

    /*----------------------------------- Method $ResourceService ---------------------------------------------*/

    @Override
    public List<AgencyResponse> getAllAgency() {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, RedisKeyEntity.K_AGENCY);
            List<AgencyResponse> result = (List<AgencyResponse>) getCache(key);
            if (result == null) {
                List<AgencyResponse> response = new ArrayList<>();
                List<SmnAgencyEntity> agencyEntities = (List<SmnAgencyEntity>) agencyRepository.findAll();
                agencyEntities.forEach(a -> response.add(new AgencyResponse(a.getAgencyId(), a.getAgencyCode(), a.getAgencyName())));
                result = response;
                writeCache(key, result, RedisKeyEntity.CACHE_LONG_TIME_SECONDS);
            }
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<ProvinceResponse> getAllProvince() {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, RedisKeyEntity.K_PROVINCE);
            List<ProvinceResponse> result = (List<ProvinceResponse>) getCache(key);
            if (result == null) {
                List<ProvinceResponse> response = new ArrayList<>();
                List<SmnProvinceEntity> provinceEntities = (List<SmnProvinceEntity>) provinceRepository.findAll();
                provinceEntities.forEach(p -> response.add(new ProvinceResponse(p.getProvinceId(), p.getName(), p.getType())));
                result = response;
                writeCache(key, result, RedisKeyEntity.CACHE_LONG_TIME_SECONDS);
            }
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<DistrictResponse> getAllDistrict() {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, RedisKeyEntity.K_DISTRICT);
            List<DistrictResponse> result = (List<DistrictResponse>) getCache(key);
            if (result == null) {
                List<DistrictResponse> response = new ArrayList<>();
                List<SmnDistrictEntity> districtEntities = (List<SmnDistrictEntity>) districtRepository.findAll();
                districtEntities.forEach(d -> response.add(new DistrictResponse(d.getDistrictId(), d.getName(), d.getType(), d.getProvinceId())));
                result = response;
                writeCache(key, result, RedisKeyEntity.CACHE_LONG_TIME_SECONDS);
            }
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<WardResponse> getAllWard() {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, RedisKeyEntity.K_WARD);
            List<WardResponse> result = (List<WardResponse>) getCache(key);
            if (result == null) {
                List<WardResponse> response = new ArrayList<>();
                List<SmnWardEntity> wardEntities = (List<SmnWardEntity>) wardRepository.findAll();
                wardEntities.forEach(w -> response.add(new WardResponse(w.getWardId(), w.getName(), w.getType(), w.getDistrictId())));
                result = response;
                writeCache(key, result, RedisKeyEntity.CACHE_LONG_TIME_SECONDS);
            }
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<ProductTypeResponse> getAllProductType() {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, RedisKeyEntity.K_PRODUCT_TYPE);
            List<ProductTypeResponse> result = (List<ProductTypeResponse>) getCache(key);
            if (result == null) {
                List<ProductTypeResponse> response = new ArrayList<>();
                List<SmnProductTypeEntity> wardEntities = (List<SmnProductTypeEntity>) productTypeRepository.findAll();
                wardEntities.forEach(t -> response.add(new ProductTypeResponse(t.getProductTypeId(), t.getName(), t.getNotation())));
                result = response;
                writeCache(key, result, RedisKeyEntity.CACHE_LONG_TIME_SECONDS);
            }
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<BillStepResponse> getAllBillStep() {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, RedisKeyEntity.K_BILL_STEP);
            List<BillStepResponse> result = (List<BillStepResponse>) getCache(key);
            if (result == null) {
                List<BillStepResponse> response = new ArrayList<>();
                List<SmnBillStepEntity> billStepEntities = (List<SmnBillStepEntity>) billStepRepository.findAll();
                billStepEntities.forEach(s -> response.add(new BillStepResponse(s.getBillStepId(), s.getName(), s.getColor(), s.getSortOrder())));
                result = response;
                writeCache(key, result, RedisKeyEntity.CACHE_LONG_TIME_SECONDS);
            }
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
