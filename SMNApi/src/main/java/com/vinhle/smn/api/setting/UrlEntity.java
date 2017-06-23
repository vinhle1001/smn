package com.vinhle.smn.api.setting;

/**
 * Created by VinhLe on 4/15/2017.
 */
public class UrlEntity {

    /*----------------------------------- URL $Account ---------------------------------------------*/
    public static final String E_ACCOUNT = "api/account";

    public static final String A_CHECK_LOGIN = "/check-login";
    public static final String A_GET_ACCOUNT_INFO_BY_SESSION = "/get-account-info-by-session";


    /*----------------------------------- URL $Supplier ---------------------------------------------*/
    public static final String E_SUPPLIER = "api/supplier";

    public static final String A_SAVE_SUPPLIER = "/save-supplier";
    public static final String A_SEARCH_SUPPLIER = "/search-supplier";
    public static final String A_GET_SUPPLIER_BY_ID = "/get-supplier-by-id";
    public static final String A_GET_IMPORT_BY_COMPOSITED_ID = "/get-import-by-composited-id";
    public static final String A_SAVE_IMPORT = "/save-import";
    public static final String A_GET_IMPORT_BY_SUPPLIER_ID = "/get-import-by-supplier-id";


    /*----------------------------------- URL $Customer ---------------------------------------------*/
    public static final String E_CUSTOMER = "api/customer";

    public static final String A_SAVE_CUSTOMER = "/save-customer";
    public static final String A_SEARCH_CUSTOMER = "/search-customer";
    public static final String A_FIND_ALL_CUSTOMER = "/find-all-customer";
    public static final String A_GET_CUSTOMER_BY_ID = "/get-customer-by-id";
//    public static final String A_GET_INFO_BY_SESSION = "/get-info-by-session";


    /*----------------------------------- URL $Product ---------------------------------------------*/
    public static final String E_PRODUCT = "api/product";

    public static final String A_SAVE_PRODUCT = "/save-product";
    public static final String A_SAVE_PRODUCT_OF_AGENCY = "/save-product-of-agency";
    public static final String A_GET_ALL_PRODUCT = "/get-all-product";
    public static final String A_GET_PRODUCT_BY_ID = "/get-product-by-id";
    public static final String A_SEARCH_PRODUCT = "/search-product";
    public static final String A_GET_PRODUCT_OF_AGENCY = "/get-product-of-agency";
    public static final String A_GET_ALL_PRODUCT_OF_AGENCY = "/get-all-product-of-agency";


    /*----------------------------------- URL $Bill ---------------------------------------------*/
    public static final String E_BILL = "api/bill";

    public static final String A_SAVE_BILL = "/save-bill";
    public static final String A_GET_BILL_BY_COMPOSITED_ID = "/get-bill-by-composited-id";
    public static final String A_GET_BILL_BY_CUSTOMER_ID = "/get-bill-by-customer-id";
    public static final String A_SEARCH_BILL_TEXT = "/search-bill-text";
    public static final String A_SEARCH_BILL_RANGE_DATE = "/search-bill-range-date";
    public static final String A_SAVE_BILL_RETURNED = "/save-bill-returned";
    public static final String A_GET_BILL_RETURNED = "/get-bill-returned";


    /*----------------------------------- URL $Resource ---------------------------------------------*/
    public static final String E_RESOURCE = "api/resource";

    public static final String A_GET_ALL_AGENCY = "/get-all-agency";
    public static final String A_GET_ALL_PROVINCE = "/get-all-province";
    public static final String A_GET_ALL_DISTRICT = "/get-all-district";
    public static final String A_GET_ALL_WARD = "/get-all-ward";
    public static final String A_GET_ALL_PRODUCT_TYPE = "/get-all-product-type";
    public static final String A_GET_ALL_BILL_STEP = "/get-all-bill-step";


}
