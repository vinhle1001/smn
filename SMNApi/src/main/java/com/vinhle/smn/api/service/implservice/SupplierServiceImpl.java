package com.vinhle.smn.api.service.implservice;

import com.vinhle.smn.api.common.StringHelper;
import com.vinhle.smn.api.model.request.ImportDetailRequest;
import com.vinhle.smn.api.model.request.ImportRequest;
import com.vinhle.smn.api.model.request.SupplierRequest;
import com.vinhle.smn.api.model.response.*;
import com.vinhle.smn.api.model.shared.ResourceModel;
import com.vinhle.smn.api.model.sql.*;
import com.vinhle.smn.api.model.sql.custom.SmnImportDetailExtendFieldEntity;
import com.vinhle.smn.api.model.sql.custom.SmnImportExtendFieldEntity;
import com.vinhle.smn.api.model.sql.custom.SmnSupplierExtendFieldEntity;
import com.vinhle.smn.api.repository.*;
import com.vinhle.smn.api.service.interfaceservice.AgencyProductService;
import com.vinhle.smn.api.service.interfaceservice.MqttService;
import com.vinhle.smn.api.service.interfaceservice.SupplierService;
import com.vinhle.smn.api.setting.AppSetting;
import com.vinhle.smn.api.setting.MqttSetting;
import com.vinhle.smn.api.setting.RedisKeyEntity;
import com.vinhle.smn.api.setting.UrlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by VinhLe on 5/29/2017.
 */
@Service
public class SupplierServiceImpl extends BaseService implements SupplierService {

    /*----------------------------------- Variable $SupplierService ---------------------------------------------*/

    private static final String DEFAULT_E_URL = "SMN_SUPPLIER_API";
    private static final String PRODUCT_E_URL = "SMN_PRODUCT_API";


    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ImportRepository importRepository;

    @Autowired
    ImportDetailRepository importDetailRepository;

    @Autowired
    ImportExtendFieldRepository importExtendFieldRepository;

    @Autowired
    ImportDetailExtendFieldRepository importDetailExtendFieldRepository;

    @Autowired
    AgencyProductRepository agencyProductRepository;

    @Autowired
    SupplierExtendFieldRepository supplierExtendFieldRepository;


    @Autowired
    AgencyProductService agencyProductService;

    @Autowired
    MqttService mqttService;

    /*----------------------------------- Method $SupplierService ---------------------------------------------*/

    @Override
    public List<SupplierSearchResponse> searchSupplier(String method, String text) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_SUPPLIER_SEARCH_TEXT, text);
            List<SupplierSearchResponse> result = (List<SupplierSearchResponse>) getCache(key);
            if (result == null) {
                List<SupplierSearchResponse> response = new ArrayList<>();
                List<SmnSupplierEntity> supplierEntities = (List<SmnSupplierEntity>) supplierRepository.findByText(text);
                supplierEntities.forEach(s -> response.add(new SupplierSearchResponse(s.getSupplierId(), s.getName(), s.getPhoneNumber())));
                result = response;
                writeCache(key, result);
            }
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public ImportResponse getImport(String method, Integer supplierId, Integer importId) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_SUPPLIER_ID, String.valueOf(supplierId), RedisKeyEntity.E_IMPORT_ID, String.valueOf(importId));
            ImportResponse result = (ImportResponse) getCache(key);
            if (result == null) {
                result = new ImportResponse(AppSetting.SUCCESS_CODE, AppSetting.SUCCESS_MESSAGE);
                SmnSupplierExtendFieldEntity supplierExtendFieldEntity = supplierExtendFieldRepository.findById(supplierId);

                SmnImportExtendFieldEntity importExtendFieldEntity = importExtendFieldRepository.getImportByImportId(importId);
                if (importExtendFieldEntity != null) {
                    result.setImportId(importExtendFieldEntity.getImportId());
                    result.setImportCost(importExtendFieldEntity.getImportCost());
                    result.setImportDebt(importExtendFieldEntity.getImportDebt());
                    result.setDescription(importExtendFieldEntity.getDescription());

                    List<ImportDetailResponse> importDetails = new ArrayList<>();
                    List<SmnImportDetailExtendFieldEntity> importDetailExtendFieldEntities = importDetailExtendFieldRepository.getByImportId(importId);
                    importDetailExtendFieldEntities.forEach(i -> {
                        ImportDetailResponse importDetail = new ImportDetailResponse();
                        importDetail.setImportDetailId(i.getImportDetailId());
                        importDetail.setProduct(new ResourceModel(i.getProductId(), i.getProductName()));
                        importDetail.setProductQuantity(i.getProductQuantity());
                        importDetail.setProductCost(i.getProductCost());
                        importDetail.setDescription(i.getDescription());
                        importDetail.setIsActive(i.getIsActive());
                        importDetails.add(importDetail);
                    });

                    result.setAgency(new ResourceModel(importExtendFieldEntity.getAgencyId(), importExtendFieldEntity.getAgencyName()));
                    result.setImportDetails(importDetails);
                }

                result.setSupplierId(supplierExtendFieldEntity.getSupplierId());
                result.setSupplierName(supplierExtendFieldEntity.getName());
                result.setSupplierPhone(supplierExtendFieldEntity.getPhoneNumber());

                writeCache(key, result);
            }
            return result;
        } catch (Exception e) {
            return new ImportResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }

    @Override
    public List<ImportSearchResponse> getImportOfSupplier(String method, Integer supplierId) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_SUPPLIER_ID, String.valueOf(supplierId));
            List<ImportSearchResponse> result = (List<ImportSearchResponse>) getCache(key);
            if (result == null) {
                List<ImportSearchResponse> response = new ArrayList<ImportSearchResponse>();
                List<SmnImportExtendFieldEntity> importExtendFieldEntities = importExtendFieldRepository.getImportBySupplierId(supplierId);
                importExtendFieldEntities.forEach(i -> {
                    ImportSearchResponse item = new ImportSearchResponse();
                    item.setImportId(i.getImportId());
                    item.setSupplierId(i.getSupplierId());
                    item.setImportCost(i.getImportCost());
                    item.setAgencyName(i.getAgencyName());
                    item.setSupplierName(i.getSupplierName());
                    item.setCreateDate(i.getCreatedDate());

                    response.add(item);
                });
                result = response;
                writeCache(key, result);
            }
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public UpdateImportResponse saveImport(String method, ImportRequest model) {
        try {
            System.out.println(model);
            SmnImportEntity importEntity = new SmnImportEntity();
            importEntity.setImportId(model.getImportId());
//            importEntity.setIm(StringHelper.generateSKU(model.getImportCode(), /*model.getBillType().getSign()*/"SKU"));
            importEntity.setSupplierId(model.getSupplierId());
            importEntity.setAgencyId(model.getAgencyId());
            importEntity.setImportCost(model.getImportCost());
            importEntity.setImportDebt(model.getImportDebt());
            importEntity.setDescription(model.getDescription());
            importEntity.setIsActive(model.getIsActive());

            if (importEntity.getImportId() == null)
                importRepository.save(importEntity);
            else importRepository.update(importEntity);

            // TODO: Save bill detail
            List<SmnImportDetailEntity> newEntries = new ArrayList<>();
            List<SmnImportDetailEntity> importDetailEntities = importDetailRepository.getImportDetailByBillId(model.getImportId());

            StringBuilder productIdStringBuilder = new StringBuilder();
            if (model.getImportDetails() != null && model.getImportDetails().size() > 0) {
                productIdStringBuilder.append((String) model.getImportDetails().stream().map(d -> String.valueOf(d.getProductId())).collect(Collectors.joining(",")));
            }
            if (importDetailEntities.size() > 0) {
                if (productIdStringBuilder.length() > 0) productIdStringBuilder.append(",");
                productIdStringBuilder.append((String) importDetailEntities.stream().map(d -> String.valueOf(d.getProductId())).collect(Collectors.joining(",")));
            }
            List<SmnAgencyProductEntity> agencyProductEntities = agencyProductRepository.getAgencyProductByAgencyIdProductIds(model.getAgencyId(), productIdStringBuilder.toString());

            // Add product of import detail
            if (model.getImportDetails() != null) {
                List<ImportDetailRequest> billDetails = model.getImportDetails().stream().sorted(new Comparator<ImportDetailRequest>() {
                    @Override
                    public int compare(ImportDetailRequest o1, ImportDetailRequest o2) {
                        return o1.getProductId().compareTo(o2.getProductId());
                    }
                }).collect(Collectors.toList());

                Integer indexOfAgencyProduct = 0;
                Integer indexOfDetailEntity = 0;
                for (ImportDetailRequest detail : billDetails) {
                    Integer currentProductId = detail.getProductId();
                    Integer quantityProductImport = detail.getProductQuantity();

                    for (int max = importDetailEntities.size(); indexOfDetailEntity < max; ) {
                        SmnImportDetailEntity currentDetail = importDetailEntities.get(indexOfDetailEntity);
                        if (currentDetail.getProductId().compareTo(currentProductId) > 1) break;
                        else if (currentDetail.getProductId().compareTo(currentProductId) == 0) {
                            quantityProductImport = quantityProductImport - currentDetail.getProductQuantity();
                            detail.setImportDetailId(currentDetail.getImportDetailId());

                            currentDetail.setProductCost(detail.getProductCost());
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
                            int productQuantity = currentAgencyProduct.getProductQuantity() + quantityProductImport;

                            if (productQuantity < 0)
                                return new UpdateImportResponse(AppSetting.IMPORT_PRODUCT_EXCEED_LIMIT_STOCK_CODE, AppSetting.INVALID_MESSAGE);
                            currentAgencyProduct.setProductQuantity(productQuantity);

                            break;
                        } else {
                            indexOfAgencyProduct++;
                        }
                    }

                    if (detail.getImportDetailId() == null) {
                        SmnImportDetailEntity importDetailEntity = new SmnImportDetailEntity();

                        importDetailEntity.setProductId(detail.getProductId());
                        importDetailEntity.setImportId(importEntity.getImportId());
                        importDetailEntity.setProductCost(detail.getProductCost());
                        importDetailEntity.setProductBeginningQuantity(detail.getProductQuantity());
                        importDetailEntity.setProductQuantity(detail.getProductQuantity());
                        importDetailEntity.setDescription(detail.getDescription());
                        importDetailEntity.setIsActive(detail.getIsActive());
                        importDetailEntity.setUid(detail.getUid());

                        newEntries.add(importDetailEntity);
                    }
                }
                importDetailRepository.save(newEntries);
                importDetailRepository.update(importDetailEntities);
                agencyProductRepository.save(agencyProductEntities);

                importDetailEntities.addAll(newEntries);
            }
            List<UpdateImportDetailResponse> updateImportDetails = new ArrayList<>();
            importDetailEntities.forEach(b -> updateImportDetails.add(new UpdateImportDetailResponse(b.getImportDetailId(), b.getUid())));

            // TODO: delete writeCache agency_product, bill of customer
            String keyProductOfAgency = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, PRODUCT_E_URL, UrlEntity.A_GET_PRODUCT_OF_AGENCY.toUpperCase(), RedisKeyEntity.E_AGENCY_ID, String.valueOf(model.getAgencyId()), RedisKeyEntity.E_STAR);
            String keyAllProductOfAgency = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, PRODUCT_E_URL, UrlEntity.A_GET_ALL_PRODUCT_OF_AGENCY.toUpperCase(), RedisKeyEntity.E_AGENCY_ID, String.valueOf(model.getAgencyId()));
            String keyImportOfSupplier = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, UrlEntity.A_GET_IMPORT_BY_COMPOSITED_ID.toUpperCase(), RedisKeyEntity.E_SUPPLIER_ID, String.valueOf(model.getSupplierId()), RedisKeyEntity.E_IMPORT_ID, String.valueOf(importEntity.getImportId()));
            String keyAllImportOfSupplier = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, UrlEntity.A_GET_IMPORT_BY_SUPPLIER_ID.toUpperCase(), RedisKeyEntity.E_SUPPLIER_ID, String.valueOf(model.getSupplierId()));

            deleteCache(keyProductOfAgency);
            deleteCache(keyAllProductOfAgency);
            deleteCache(keyImportOfSupplier);
            deleteCache(keyAllImportOfSupplier);

            // TODO: publish product of agency
            mqttService.publish(StringHelper.ConcatWithSplit(MqttSetting.SPLIT_CHAR,MqttSetting.TOPIC_PRODUCT_OF_AGENCY, String.valueOf(model.getAgencyId())), new Function() {
                @Override
                public Object apply(Object o) {
                    return agencyProductService.getProductOfAgency((Integer) o);
                }
            }, model.getAgencyId());

            return new UpdateImportResponse(importEntity.getImportId(), updateImportDetails);
        } catch (Exception e) {
            return new UpdateImportResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }

    @Override
    public SupplierResponse getSupplierById(String method, Integer id) {
        try {
            String key = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, method.toUpperCase(), RedisKeyEntity.E_SUPPLIER_ID, String.valueOf(id));
            SupplierResponse result = (SupplierResponse) getCache(key);
            if (result == null) {
                SmnSupplierExtendFieldEntity supplierExtendFieldEntity = supplierExtendFieldRepository.findById(id);

                result = new SupplierResponse(supplierExtendFieldEntity.getSupplierId(), supplierExtendFieldEntity.getName(), supplierExtendFieldEntity.getDescription(), supplierExtendFieldEntity.getPhoneNumber(), supplierExtendFieldEntity.getEmail(), supplierExtendFieldEntity.getAddress(),
                        new ResourceModel(supplierExtendFieldEntity.getProvinceId(), supplierExtendFieldEntity.getProvinceName()),
                        new ResourceModel(supplierExtendFieldEntity.getDistrictId(), supplierExtendFieldEntity.getDistrictName()),
                        new ResourceModel(supplierExtendFieldEntity.getWardId(), supplierExtendFieldEntity.getWardName()),
                        supplierExtendFieldEntity.getIsActive());

                writeCache(key, result);
            }
            return result;
        } catch (Exception e) {
            return new SupplierResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }

    @Override
    public UpdateSupplierResponse save(String method, SupplierRequest model) {
        try {
            SmnSupplierEntity supplierEntity = new SmnSupplierEntity();
            supplierEntity.setSupplierId(model.getSupplierId());
            supplierEntity.setName(model.getName());
            supplierEntity.setPhoneNumber(model.getPhoneNumber());
            supplierEntity.setEmail(model.getEmail());
            supplierEntity.setAddress(model.getAddress());
            supplierEntity.setProvinceId(model.getProvinceId());
            supplierEntity.setDistrictId(model.getDistrictId());
            supplierEntity.setWardId(model.getWardId());
            supplierEntity.setDescription(model.getDescription());
            supplierEntity.setIsActive(model.getIsActive());

            if (supplierEntity.getSupplierId() == null) {
                supplierRepository.save(supplierEntity);
            } else supplierRepository.update(supplierEntity);

            // TODO: delete writeCache
            String keySearchText = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, UrlEntity.A_SEARCH_SUPPLIER.toUpperCase(), RedisKeyEntity.E_STAR);
            String keyGetById = StringHelper.ConcatWithSplit(RedisKeyEntity.SPLIT_CHAR, DEFAULT_E_URL, UrlEntity.A_GET_SUPPLIER_BY_ID.toUpperCase(), String.valueOf(supplierEntity.getSupplierId()));
            deleteCache(keySearchText);
            deleteCache(keyGetById);

            return new UpdateSupplierResponse(supplierEntity.getSupplierId());
        } catch (Exception e) {
            return new UpdateSupplierResponse(AppSetting.INVALID_CODE, AppSetting.INVALID_MESSAGE);
        }
    }

}
