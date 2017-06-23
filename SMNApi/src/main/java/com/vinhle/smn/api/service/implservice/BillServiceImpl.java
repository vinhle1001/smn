package com.vinhle.smn.api.service.implservice;

import com.vinhle.smn.api.common.LambdaHelper;
import com.vinhle.smn.api.common.StringHelper;
import com.vinhle.smn.api.model.request.BillDetailRequest;
import com.vinhle.smn.api.model.request.BillRequest;
import com.vinhle.smn.api.model.request.BillReturnedDetailRequest;
import com.vinhle.smn.api.model.response.*;
import com.vinhle.smn.api.model.shared.ResourceModel;
import com.vinhle.smn.api.model.sql.*;
import com.vinhle.smn.api.model.sql.custom.SmnBillDetailExtendFieldEntity;
import com.vinhle.smn.api.model.sql.custom.SmnBillExtendFieldEntity;
import com.vinhle.smn.api.model.sql.custom.SmnBillReturnedExtendFieldEntity;
import com.vinhle.smn.api.model.sql.custom.SmnCustomerExtendFieldEntity;
import com.vinhle.smn.api.repository.*;
import com.vinhle.smn.api.service.interfaceservice.AgencyProductService;
import com.vinhle.smn.api.service.interfaceservice.BillService;
import com.vinhle.smn.api.service.interfaceservice.MqttService;
import com.vinhle.smn.api.setting.AppSetting;
import com.vinhle.smn.api.setting.MqttSetting;
import com.vinhle.smn.api.setting.RedisKeyEntity;
import com.vinhle.smn.api.setting.UrlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by VinhLe on 5/15/2017.
 */
@Service
public class BillServiceImpl extends BaseService implements BillService {

    /*----------------------------------- Variable $BillService ---------------------------------------------*/

    private static final String DEFAULT_E_URL = "SMN_BILL_API";
    private static final String PRODUCT_E_URL = "SMN_PRODUCT_API";

    @Autowired
    CustomerExtendFieldRepository customerExtendFieldRepository;

    @Autowired
    AgencyProductRepository agencyProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    BillDetailRepository billDetailRepository;

    @Autowired
    BillExtendFieldRepository billExtendFieldRepository;

    @Autowired
    BillReturnedDetailRepository billReturnedDetailRepository;

    @Autowired
    BillReturnedDetailExtendFieldRepository billReturnedDetailExtendFieldRepository;

    @Autowired
    BillDetailExtendFieldRepository billDetailExtendFieldRepository;


    @Autowired
    AgencyProductService agencyProductService;

    @Autowired
    MqttService mqttService;

    /*----------------------------------- Method $BillService ---------------------------------------------*/

    @Override
    public BillResponse getBillByCompositedId(String method, Integer customerId, Integer billId) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_CUSTOMER_ID, String.valueOf(customerId), RedisKeyEntity.E_BILL_ID, String.valueOf(billId));
            BillResponse bill = (BillResponse) getCache(key);
            if (bill == null) {
                SmnCustomerExtendFieldEntity customerExtendFieldEntity = customerExtendFieldRepository.findById(customerId);
                SmnBillExtendFieldEntity billExtendFieldEntity = billExtendFieldRepository.findById(billId);

                bill = new BillResponse();
                bill.setBillId(billExtendFieldEntity != null ? billExtendFieldEntity.getBillId() : null);
                bill.setCustomerId(customerExtendFieldEntity.getCustomerId());
                bill.setCustomerName(customerExtendFieldEntity.getFullName());
                bill.setCustomerPhone(customerExtendFieldEntity.getPhoneNumber());

                if (billExtendFieldEntity == null) {
                    bill.setAddress(customerExtendFieldEntity.getAddress());
                    bill.setProvince(new ResourceModel(customerExtendFieldEntity.getProvinceId(), customerExtendFieldEntity.getProvinceName()));
                    bill.setDistrict(new ResourceModel(customerExtendFieldEntity.getDistrictId(), customerExtendFieldEntity.getDistrictName()));
                    bill.setWard(customerExtendFieldEntity.getWardId() != null ? new ResourceModel(customerExtendFieldEntity.getWardId(), customerExtendFieldEntity.getWardName()) : null);
                } else {
                    bill.setBillCode(billExtendFieldEntity.getBillCode());
                    bill.setBillPrice(billExtendFieldEntity.getBillPrice());
                    bill.setBillRefundCost(billExtendFieldEntity.getBillRefundCost());
                    bill.setBillDebt(billExtendFieldEntity.getBillDebt());
                    bill.setBillStepId(billExtendFieldEntity.getBillStepId());
                    bill.setAddress(billExtendFieldEntity.getAddress());
                    bill.setProvince(new ResourceModel(billExtendFieldEntity.getProvinceId(), billExtendFieldEntity.getProvinceName()));
                    bill.setDistrict(new ResourceModel(billExtendFieldEntity.getDistrictId(), billExtendFieldEntity.getDistrictName()));
                    bill.setWard(billExtendFieldEntity.getWardId() != null ? new ResourceModel(billExtendFieldEntity.getWardId(), billExtendFieldEntity.getWardName()) : null);
                    bill.setAgency(billExtendFieldEntity.getAgencyId() != null ? new ResourceModel(billExtendFieldEntity.getAgencyId(), billExtendFieldEntity.getAgencyName()) : null);
                    List<BillDetailResponse> billDetails = new ArrayList<>();
                    List<SmnBillDetailExtendFieldEntity> billDetailExtendFieldEntities = (List<SmnBillDetailExtendFieldEntity>) billDetailExtendFieldRepository.getByBillId(billId);
                    billDetailExtendFieldEntities.forEach(d -> {
                        billDetails.add(new BillDetailResponse(d.getBillDetailId(), new ResourceModel(d.getProductId(), StringHelper.Concat(d.getProductName(), " ", d.getProductSize())), d.getProductPrice(), d.getProductQuantity(), d.getProductReturnedQuantity(), d.getRefundCost(), d.getDescription(), d.getIsActive()));
                    });

                    bill.setBillDetails(billDetails);
                    bill.setDescription(billExtendFieldEntity.getDescription());
                    bill.setIsActive(billExtendFieldEntity.getIsActive());
                }

                writeCache(key, bill);
            }

            return bill;
        } catch (Exception e) {
            return new BillResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }

    @Override
    public BillReturnedResponse getBillReturnedByBillId(String method, Integer customerId, Integer billId) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_BILL_ID, String.valueOf(billId));
            BillReturnedResponse result = (BillReturnedResponse) getCache(key);
            if (result == null) {
                result = new BillReturnedResponse();
                SmnCustomerExtendFieldEntity customerExtendFieldEntity = customerExtendFieldRepository.findById(customerId);
                SmnBillExtendFieldEntity billExtendFieldEntity = billExtendFieldRepository.findById(billId);
                List<SmnBillReturnedExtendFieldEntity> billReturnedExtendFieldEntities = billReturnedDetailExtendFieldRepository.getBillReturnedByBillId(billId);

                // TODO: set value
                result.setBillId(billExtendFieldEntity.getBillId());
                result.setBillCode(billExtendFieldEntity.getBillCode());
                result.setCustomerName(customerExtendFieldEntity.getFullName());
                result.setCustomerPhone(customerExtendFieldEntity.getPhoneNumber());
                result.setAddress(billExtendFieldEntity.getAddress());
                result.setProvince(new ResourceModel(billExtendFieldEntity.getProvinceId(), billExtendFieldEntity.getProvinceName()));
                result.setDistrict(new ResourceModel(billExtendFieldEntity.getDistrictId(), billExtendFieldEntity.getDistrictName()));
                result.setWard(billExtendFieldEntity.getWardId() != null ? new ResourceModel(billExtendFieldEntity.getWardId(), billExtendFieldEntity.getWardName()) : null);
                result.setAgency(billExtendFieldEntity.getAgencyId() != null ? new ResourceModel(billExtendFieldEntity.getAgencyId(), billExtendFieldEntity.getAgencyName()) : null);
                List<BillReturnedDetailResponse> billReturnedDetailResponses = new ArrayList<>();
                billReturnedExtendFieldEntities.forEach(d -> {
                    billReturnedDetailResponses.add(new BillReturnedDetailResponse(d.getBillReturnedDetailId(), d.getBillDetailId(), d.getProductId(), d.getProductName(), d.getProductQuantity(), d.getProductReturnedQuantity(), d.getRefundedCost(), d.getDescription(), d.getIsActive()));
                });
                result.setBillReturnedDetails(billReturnedDetailResponses);
                writeCache(key, result);
            }

            return result;
        } catch (Exception e) {
            return new BillReturnedResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }

    @Override
    public List<BillSearchResponse> getByCustomerId(String method, Integer customerId) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_CUSTOMER_ID, String.valueOf(customerId));
            List<BillSearchResponse> result = (List<BillSearchResponse>) getCache(key);
            if (result == null) {
                List<BillSearchResponse> responses = new ArrayList<>();
                List<SmnBillExtendFieldEntity> billDetailExtendFieldEntities = (List<SmnBillExtendFieldEntity>) billExtendFieldRepository.getByCustomerId(customerId);
                if (billDetailExtendFieldEntities != null && billDetailExtendFieldEntities.size() > 0) {
                    billDetailExtendFieldEntities.forEach(b -> {
                        Long amount = b.getBillPrice() - b.getBillRefundCost();
                        responses.add(new BillSearchResponse(b.getCustomerId(), b.getCustomerName(), b.getBillId(), amount, b.getCreatedDate(), b.getBillStepId()));
                    });
                }
                result = responses;
                writeCache(key, result);
            }

            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<BillSearchResponse> getByText(String method, String text) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_BILL_SEARCH_TEXT, text);
            List<BillSearchResponse> result = (List<BillSearchResponse>) getCache(key);
            if (result == null) {
                List<BillSearchResponse> responses = new ArrayList<>();
                List<SmnBillExtendFieldEntity> billDetailExtendFieldEntities = (List<SmnBillExtendFieldEntity>) billExtendFieldRepository.findByText(text);
                if (billDetailExtendFieldEntities != null && billDetailExtendFieldEntities.size() > 0) {
                    billDetailExtendFieldEntities.forEach(b -> responses.add(new BillSearchResponse(b.getCustomerId(), b.getCustomerName(), b.getBillId(), b.getBillPrice(), b.getCreatedDate(), b.getBillStepId())));
                }
                result = responses;
                writeCache(key, result);
            }

            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<BillSearchResponse> getByRangeDate(String method, Timestamp timeStart, Timestamp timeEnd) {
        try {
            List<BillSearchResponse> result = new ArrayList<>();
            List<SmnBillExtendFieldEntity> billDetailExtendFieldEntities = (List<SmnBillExtendFieldEntity>) billExtendFieldRepository.getByRangeDate(timeStart, timeEnd);
            if (billDetailExtendFieldEntities != null && billDetailExtendFieldEntities.size() > 0) {
                billDetailExtendFieldEntities.forEach(b -> result.add(new BillSearchResponse(b.getCustomerId(), b.getCustomerName(), b.getBillId(), b.getBillPrice(), b.getCreatedDate(), b.getBillStepId())));
            }

            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    @Override
    public UpdateBillResponse saveBill(String method, BillRequest model) {
        try {
            // TODO: Save bill
            SmnBillEntity billEntity = new SmnBillEntity();
            billEntity.setBillId(model.getBillId());
            billEntity.setBillCode(StringHelper.generateSKU(model.getBillCode(), /*model.getBillType().getSign()*/"SKU"));
            billEntity.setCustomerId(model.getCustomerId());
            billEntity.setAgencyId(model.getAgencyId());
            billEntity.setBillPrice(model.getBillPrice());
            billEntity.setBillRefundCost(model.getBillRefundCost());
            billEntity.setBillDebt(model.getBillDebt());
            billEntity.setAddress(model.getAddress());
            billEntity.setProvinceId(model.getProvinceId());
            billEntity.setDistrictId(model.getDistrictId());
            billEntity.setWardId(model.getWardId());
            billEntity.setBillStepId(model.getBillStepId());
            billEntity.setBillTypeId(/*model.getBillType().getId()*/1);
            billEntity.setDescription(model.getDescription());
            billEntity.setIsActive(model.getIsActive());
            if (billEntity.getBillId() == null)
                billRepository.save(billEntity);
            else billRepository.update(billEntity);

            // TODO: Save bill detail
            // Concat billId of billDetail
            // Load data
            List<SmnBillDetailEntity> newEntries = new ArrayList<>();
            List<SmnBillDetailEntity> billDetailEntities = billDetailRepository.getBillDetailByBillId(model.getBillId());
            List<SmnBillReturnedDetailEntity> billReturnedDetailEntities = billReturnedDetailRepository.getBillReturnedDetailByBillId(model.getBillId());
            List<SmnProductEntity> productEntities = productRepository.getByIds(model.getBillDetails().stream().filter(d -> d.getBillDetailId() == null).map(d -> String.valueOf(d.getProductId())).collect(Collectors.joining(",")));

            StringBuilder productIdStringBuilder = new StringBuilder();
            if (model.getBillDetails() != null && model.getBillDetails().size() > 0) {
                productIdStringBuilder.append((String) model.getBillDetails().stream().map(d -> String.valueOf(d.getProductId())).collect(Collectors.joining(",")));
            }
            if (billDetailEntities.size() > 0) {
                if (productIdStringBuilder.length() > 0) productIdStringBuilder.append(",");
                productIdStringBuilder.append((String) billDetailEntities.stream().map(d -> String.valueOf(d.getProductId())).collect(Collectors.joining(",")));
            }
            List<SmnAgencyProductEntity> agencyProductEntities = agencyProductRepository.getAgencyProductByAgencyIdProductIds(model.getAgencyId(), productIdStringBuilder.toString());

            // Remove old product of bill
            /*if (billDetailEntities.size() > 0) {
                List<Integer> productOfBillId = model.getBillDetails().stream().map(d -> d.getProductId()).collect(Collectors.toList());
                billDetailEntities.stream().filter(d -> productOfBillId == null || !productOfBillId.contains(d.getProductId())).forEach(d -> {
                    agencyProductEntities.stream().filter(p -> p.getProductId().equals(d.getProductId())).forEach(p -> {
                        int productQuantity = d.getProductQuantity();
                        d.setProductQuantity(p.getProductQuantity() + productQuantity);
                    });
                    d.setProductQuantity(0);
                    d.setIsActive((byte) 0);
                });
            }*/

            // Add product of bill detail
            if (model.getBillDetails() != null) {
                List<BillDetailRequest> billDetails = model.getBillDetails().stream().sorted(new Comparator<BillDetailRequest>() {
                    @Override
                    public int compare(BillDetailRequest o1, BillDetailRequest o2) {
                        return o1.getProductId().compareTo(o2.getProductId());
                    }
                }).collect(Collectors.toList());

                Integer indexOfAgencyProduct = 0;
                Integer indexOfDetailEntity = 0;
                for (BillDetailRequest detail : billDetails) {
                    Integer currentProductId = detail.getProductId();
                    Integer quantityProductUsed = detail.getProductQuantity();

                    for (int max = billDetailEntities.size(); indexOfDetailEntity < max; ) {
                        SmnBillDetailEntity currentDetail = billDetailEntities.get(indexOfDetailEntity);
                        if (currentDetail.getProductId().compareTo(currentProductId) > 1) break;
                        else if (currentDetail.getProductId().compareTo(currentProductId) == 0) {
                            quantityProductUsed = quantityProductUsed - currentDetail.getProductQuantity();
                            detail.setBillDetailId(currentDetail.getBillDetailId());

                            currentDetail.setProductPrice(detail.getProductPrice());
                            currentDetail.setModifiedDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
                            currentDetail.setProductQuantity(detail.getProductQuantity());
                            currentDetail.setDescription(detail.getDescription());
                            currentDetail.setIsActive(detail.getIsActive());
                            currentDetail.setUid(detail.getUid());

                            indexOfDetailEntity++;
                            break;
                        } else {
                            indexOfDetailEntity++;
                        }
                    }
                    for (int max = agencyProductEntities.size(); indexOfAgencyProduct < max; ) {
                        SmnAgencyProductEntity currentAgencyProduct = agencyProductEntities.get(indexOfAgencyProduct);
                        if (currentAgencyProduct.getProductId().compareTo(currentProductId) > 1) break;
                        else if (currentAgencyProduct.getProductId().compareTo(currentProductId) == 0) {
                            if (currentAgencyProduct.getProductQuantity().compareTo(quantityProductUsed) < 0)
                                return new UpdateBillResponse(AppSetting.BILL_PRODUCT_EXCEED_LIMIT_STOCK_CODE, AppSetting.INVALID_MESSAGE);
                            int productQuantity = currentAgencyProduct.getProductQuantity() - quantityProductUsed;
                            currentAgencyProduct.setProductQuantity(productQuantity);
                            break;
                        } else {
                            indexOfAgencyProduct++;
                        }
                    }

                    if (detail.getBillDetailId() == null) {
                        SmnProductEntity productEntity = productEntities.stream().filter(p -> p.getProductId().equals(detail.getProductId())).findFirst().get();
                        SmnBillDetailEntity billDetailEntity = new SmnBillDetailEntity();

                        billDetailEntity.setProductId(detail.getProductId());
                        billDetailEntity.setBillId(billEntity.getBillId());
                        billDetailEntity.setProductCurrentPrice(productEntity.getProductPrice());
                        billDetailEntity.setProductPrice(detail.getProductPrice());
                        billDetailEntity.setProductQuantity(detail.getProductQuantity());
                        billDetailEntity.setDescription(detail.getDescription());
                        billDetailEntity.setIsActive(detail.getIsActive());
                        billDetailEntity.setUid(detail.getUid());

                        newEntries.add(billDetailEntity);
                    }
                }
                billDetailRepository.save(newEntries);
                billDetailRepository.update(billDetailEntities);
                agencyProductRepository.save(agencyProductEntities);

                billDetailEntities.addAll(newEntries);
            }
            List<UpdateBillDetailResponse> updateBillResponses = new ArrayList<>();
            billDetailEntities.forEach(b -> updateBillResponses.add(new UpdateBillDetailResponse(b.getBillDetailId(), b.getUid())));

            // TODO: delete writeCache agency_product, bill of customer
            String keyProductOfAgency = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, PRODUCT_E_URL, UrlEntity.A_GET_PRODUCT_OF_AGENCY.toUpperCase(), RedisKeyEntity.E_AGENCY_ID, String.valueOf(model.getAgencyId()), RedisKeyEntity.E_STAR);
            String keyAllProductOfAgency = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, PRODUCT_E_URL, UrlEntity.A_GET_ALL_PRODUCT_OF_AGENCY.toUpperCase(), RedisKeyEntity.E_AGENCY_ID, String.valueOf(model.getAgencyId()));
            String keyBillOfCustomer = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, UrlEntity.A_GET_BILL_BY_COMPOSITED_ID.toUpperCase(), RedisKeyEntity.E_CUSTOMER_ID, String.valueOf(model.getCustomerId()), RedisKeyEntity.E_BILL_ID, String.valueOf(model.getBillId()));
            String keyAllBillOfCustomer = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, UrlEntity.A_GET_BILL_BY_CUSTOMER_ID.toUpperCase(), RedisKeyEntity.E_CUSTOMER_ID, String.valueOf(model.getCustomerId()));

            deleteCache(keyProductOfAgency);
            deleteCache(keyAllProductOfAgency);
            deleteCache(keyBillOfCustomer);
            deleteCache(keyAllBillOfCustomer);

            // TODO: publish product of agency
            mqttService.publish(StringHelper.ConcatWithSplit(MqttSetting.SPLIT_CHAR,MqttSetting.TOPIC_PRODUCT_OF_AGENCY, String.valueOf(model.getAgencyId())), new Function() {
                @Override
                public Object apply(Object o) {
                    return agencyProductService.getProductOfAgency((Integer) o);
                }
            }, model.getAgencyId());

            return new UpdateBillResponse(billEntity.getBillId(), billEntity.getBillCode(), updateBillResponses);
        } catch (Exception e) {
            return new UpdateBillResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }

    @Override
    public UpdateBillReturnedResponse saveBillReturned(String method, Integer billId, Long billRefundCost, List<BillReturnedDetailRequest> billReturnedDetails) {
        try {
            // TODO: Load data
            /** Get all product of warehouses_old_goods */
            String productIds = billReturnedDetails.stream().map(d -> String.valueOf(d.getProductId())).collect(Collectors.joining(","));
            SmnBillEntity billEntity = billRepository.findById(billId);
            List<SmnBillDetailEntity> billDetailEntities = billDetailRepository.getBillDetailByBillId(billId);
            List<SmnBillReturnedDetailEntity> billReturnedDetailEntities = billReturnedDetailRepository.getBillReturnedDetailByBillId(billId);
            List<SmnAgencyProductEntity> agencyProductEntities = agencyProductRepository.getAgencyProductByAgencyIdProductIds(AppSetting.WAREHOUSES_RETURNED_GOODS_ID, productIds);
            List<SmnBillReturnedDetailEntity> newEntries = new ArrayList<>();

            // TODO: Calculate quantity's product of warehouses_old_goods
            billReturnedDetails.sort(new Comparator<BillReturnedDetailRequest>() {
                @Override
                public int compare(BillReturnedDetailRequest o1, BillReturnedDetailRequest o2) {
                    return o1.getBillDetailId().compareTo(o2.getBillDetailId());
                }
            });
            billReturnedDetailEntities.sort(new Comparator<SmnBillReturnedDetailEntity>() {
                @Override
                public int compare(SmnBillReturnedDetailEntity o1, SmnBillReturnedDetailEntity o2) {
                    return o1.getBillDetailId().compareTo(o2.getBillDetailId());
                }
            });

            Integer indexOfBillDetailEntity = 0;
            Integer indexOfBillReturnedDetailEntity = 0;

            for (BillReturnedDetailRequest detail : billReturnedDetails) {
                Integer currentBillDetailId = detail.getBillDetailId();
                Integer quantityProductReturned = detail.getProductReturnedQuantity();

                for (int max = billDetailEntities.size(); indexOfBillDetailEntity < max; ) {
                    SmnBillDetailEntity currentDetail = billDetailEntities.get(indexOfBillDetailEntity);
                    if (currentDetail.getBillDetailId().compareTo(currentBillDetailId) > 1) break;
                    else if (currentDetail.getBillDetailId().compareTo(currentBillDetailId) == 0) {
                        // TODO: alert if currentDetail.getProductQuantity < detail.getProductReturnedQuantity
                        if (currentDetail.getProductQuantity() < detail.getProductReturnedQuantity())
                            return new UpdateBillReturnedResponse(AppSetting.BILL_PRODUCT_EXCEED_QUANTITY_USED_CODE, AppSetting.INVALID_MESSAGE);

                        indexOfBillDetailEntity++;
                        break;
                    } else {
                        indexOfBillDetailEntity++;
                    }
                }
                for (int max = billReturnedDetailEntities.size(); indexOfBillReturnedDetailEntity < max; ) {
                    SmnBillReturnedDetailEntity currentReturnedDetail = billReturnedDetailEntities.get(indexOfBillReturnedDetailEntity);
                    if (currentReturnedDetail.getBillDetailId().compareTo(currentBillDetailId) > 1) break;
                    else if (currentReturnedDetail.getBillDetailId().compareTo(currentBillDetailId) == 0) {
                        detail.setBillReturnedDetailId(currentReturnedDetail.getBillReturnedDetailId());
                        quantityProductReturned = quantityProductReturned - currentReturnedDetail.getProductReturnedQuantity();

                        currentReturnedDetail.setRefundedCost(detail.getRefundedCost());
                        currentReturnedDetail.setModifiedDate(new Timestamp(Calendar.getInstance().getTime().getTime()));
                        currentReturnedDetail.setProductReturnedQuantity(detail.getProductReturnedQuantity());
                        currentReturnedDetail.setDescription(detail.getDescription());
                        currentReturnedDetail.setIsActive(detail.getIsActive());

                        indexOfBillDetailEntity++;
                        break;
                    } else {
                        indexOfBillDetailEntity++;
                    }
                }
                SmnAgencyProductEntity agencyProductEntity = LambdaHelper.GetFirstOrDefault(agencyProductEntities, a -> a.getProductId().equals(detail.getProductId()));
                if (agencyProductEntity != null) {
                    int productQuantity = agencyProductEntity.getProductQuantity() + quantityProductReturned;
                    agencyProductEntity.setProductQuantity(productQuantity);
                }

                if (detail.getBillReturnedDetailId() == null) {
                    SmnBillReturnedDetailEntity billReturnedDetailEntity = new SmnBillReturnedDetailEntity();

                    billReturnedDetailEntity.setBillId(billId);
                    billReturnedDetailEntity.setBillDetailId(detail.getBillDetailId());
                    billReturnedDetailEntity.setProductReturnedQuantity(detail.getProductReturnedQuantity());
                    billReturnedDetailEntity.setRefundedCost(detail.getRefundedCost());
                    billReturnedDetailEntity.setDescription(detail.getDescription());
                    billReturnedDetailEntity.setIsActive(detail.getIsActive());

                    newEntries.add(billReturnedDetailEntity);
                }
            }
            billEntity.setBillRefundCost(billRefundCost);
            billEntity.setModifiedDate(new Timestamp(Calendar.getInstance().getTime().getTime()));

            // TODO: Save data
            billRepository.update(billEntity);
            billReturnedDetailRepository.save(newEntries);
            billReturnedDetailRepository.update(billReturnedDetailEntities);
            agencyProductRepository.save(agencyProductEntities);

            billReturnedDetailEntities.addAll(newEntries);
            // TODO: Clear writeCache

            List<UpdateBillReturnedDetailResponse> responses = new ArrayList<>();
            billReturnedDetailEntities.forEach(brd -> responses.add(new UpdateBillReturnedDetailResponse(brd.getBillReturnedDetailId(), brd.getBillDetailId())));

            // TODO: publish product of agency
            mqttService.publish(StringHelper.ConcatWithSplit(MqttSetting.SPLIT_CHAR,MqttSetting.TOPIC_PRODUCT_OF_AGENCY, String.valueOf(AppSetting.WAREHOUSES_RETURNED_GOODS_ID)), new Function() {
                @Override
                public Object apply(Object o) {
                    return agencyProductService.getProductOfAgency((Integer) o);
                }
            }, AppSetting.WAREHOUSES_RETURNED_GOODS_ID);


            return new UpdateBillReturnedResponse(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE, responses);
        } catch (Exception e) {
            return new UpdateBillReturnedResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }
}
