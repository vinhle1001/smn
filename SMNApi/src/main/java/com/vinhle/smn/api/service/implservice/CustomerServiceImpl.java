package com.vinhle.smn.api.service.implservice;

import com.vinhle.smn.api.common.StringHelper;
import com.vinhle.smn.api.model.request.CustomerRequest;
import com.vinhle.smn.api.model.response.CustomerResponse;
import com.vinhle.smn.api.model.response.CustomerSearchResponse;
import com.vinhle.smn.api.model.response.UpdateCustomerResponse;
import com.vinhle.smn.api.model.shared.ResourceModel;
import com.vinhle.smn.api.model.sql.SmnCustomerEntity;
import com.vinhle.smn.api.model.sql.custom.SmnCustomerExtendFieldEntity;
import com.vinhle.smn.api.repository.CustomerRepository;
import com.vinhle.smn.api.repository.CustomerExtendFieldRepository;
import com.vinhle.smn.api.service.interfaceservice.CustomerService;
import com.vinhle.smn.api.setting.AppSetting;
import com.vinhle.smn.api.setting.RedisKeyEntity;
import com.vinhle.smn.api.setting.UrlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhLe on 5/7/2017.
 */
@Service
public class CustomerServiceImpl extends BaseService implements CustomerService {

    /*----------------------------------- Variable $CustomerService ---------------------------------------------*/

    private static final String DEFAULT_E_URL = "SMN_CUSTOMER_API";

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerExtendFieldRepository customerExtendFieldRepository;

    /*----------------------------------- Method $CustomerService ---------------------------------------------*/

    @Override
    public CustomerResponse getById(String method, Integer id) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_CUSTOMER_ID, String.valueOf(id));
            CustomerResponse result = (CustomerResponse) getCache(key);

            if (result == null) {
                SmnCustomerExtendFieldEntity customerWithInformationEntity = customerExtendFieldRepository.findById(id);

                result = new CustomerResponse(customerWithInformationEntity.getCustomerId(), customerWithInformationEntity.getFullName(), customerWithInformationEntity.getFirstName(), customerWithInformationEntity.getLastName(), customerWithInformationEntity.getEmail(), customerWithInformationEntity.getFacebook(),
                        new ResourceModel(customerWithInformationEntity.getGenderId().intValue(), customerWithInformationEntity.getGenderName()),
                        customerWithInformationEntity.getBirthday(), customerWithInformationEntity.getPhoneNumber(), customerWithInformationEntity.getAddress(),
                        customerWithInformationEntity.getProvinceId() != null ? new ResourceModel(customerWithInformationEntity.getProvinceId(), customerWithInformationEntity.getProvinceName()) : null,
                        customerWithInformationEntity.getDistrictId() != null ? new ResourceModel(customerWithInformationEntity.getDistrictId(), customerWithInformationEntity.getDistrictName()) : null,
                        customerWithInformationEntity.getWardId() != null ? new ResourceModel(customerWithInformationEntity.getWardId(), customerWithInformationEntity.getWardName()) : null,
                        customerWithInformationEntity.getCustomerTypeId() != null ? new ResourceModel(customerWithInformationEntity.getCustomerTypeId().intValue(), customerWithInformationEntity.getCustomerTypeName()) : null,
                        customerWithInformationEntity.getCustomerNote(), customerWithInformationEntity.getIsActive());
                writeCache(key, result);
            }
            return result;
        } catch (Exception e) {
            return new CustomerResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }

    @Override
    public List<CustomerSearchResponse> getByText(String method, String text) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_CUSTOMER_SEARCH_TEXT, text);
            List<CustomerSearchResponse> result = (List<CustomerSearchResponse>) getCache(key);

            if (result == null) {
                final List<CustomerSearchResponse> customerSearchResponses = new ArrayList<CustomerSearchResponse>();
                List<SmnCustomerEntity> customerEntities = (List<SmnCustomerEntity>) customerRepository.findByText(text);

                customerEntities.forEach(c -> customerSearchResponses.add(new CustomerSearchResponse(c.getCustomerId(), c.getFullName(), c.getPhoneNumber())));

                result = customerSearchResponses;
                writeCache(key, result);
            }
            return result;
        } catch (Exception e) {
            return new ArrayList<CustomerSearchResponse>();
        }
    }

    @Override
    public List<CustomerSearchResponse> getAll() {
        try {
            List<CustomerSearchResponse> customerSearchResponses = new ArrayList<CustomerSearchResponse>();
            List<SmnCustomerEntity> customerEntities = (List<SmnCustomerEntity>) customerRepository.findAll();

            customerEntities.forEach(c -> customerSearchResponses.add(new CustomerSearchResponse(c.getCustomerId(), c.getFullName(), c.getPhoneNumber())));

            return customerSearchResponses;
        } catch (Exception e) {
            return new ArrayList<CustomerSearchResponse>();
        }
    }


    @Override
    public UpdateCustomerResponse saveCustomer(String method, CustomerRequest model) {
        try {
            SmnCustomerEntity customerEntity = new SmnCustomerEntity();
            customerEntity.setCustomerId(model.getCustomerId());
            customerEntity.setFullName(model.getFullName());
            customerEntity.setFirstName(model.getFirstName());
            customerEntity.setLastName(model.getLastName());
            customerEntity.setPhoneNumber(model.getPhoneNumber());
            customerEntity.setEmail(model.getEmail());
            customerEntity.setFacebook(model.getFacebook());
            customerEntity.setGenderId(model.getGenderId());
            customerEntity.setBirthday(model.getBirthday());
            customerEntity.setAddress(model.getAddress());
            customerEntity.setProvinceId(model.getProvinceId());
            customerEntity.setDistrictId(model.getDistrictId());
            customerEntity.setWardId(model.getWardId());
            customerEntity.setCustomerTypeId(model.getCustomerTypeId());
            customerEntity.setCustomerNote(model.getCustomerNote());
            customerEntity.setIsActive(model.getIsActive());

            customerEntity = customerRepository.save(customerEntity);

            // TODO: deleteCache writeCache search customer & customer by id
            String keySearch = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, UrlEntity.A_SEARCH_CUSTOMER.toUpperCase(), RedisKeyEntity.E_CUSTOMER_SEARCH_TEXT, RedisKeyEntity.E_STAR);
            String keyGetCustomerById = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, UrlEntity.A_GET_CUSTOMER_BY_ID.toUpperCase(), RedisKeyEntity.E_CUSTOMER_ID, String.valueOf(customerEntity.getCustomerId()));

            deleteCache(keySearch);
            deleteCache(keyGetCustomerById);

            return new UpdateCustomerResponse(customerEntity.getCustomerId());
        } catch (Exception e) {
            return new UpdateCustomerResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }
}
