package com.vinhle.smn.api.service.interfaceservice;

import com.vinhle.smn.api.model.request.CustomerRequest;
import com.vinhle.smn.api.model.response.CustomerResponse;
import com.vinhle.smn.api.model.response.CustomerSearchResponse;
import com.vinhle.smn.api.model.response.UpdateCustomerResponse;

import java.util.List;

/**
 * Created by VinhLe on 5/6/2017.
 */
public interface CustomerService {

    CustomerResponse getById(String method, Integer id);
    List<CustomerSearchResponse> getByText(String method, String text);
    List<CustomerSearchResponse> getAll();


    UpdateCustomerResponse saveCustomer(String method, CustomerRequest request);

}
