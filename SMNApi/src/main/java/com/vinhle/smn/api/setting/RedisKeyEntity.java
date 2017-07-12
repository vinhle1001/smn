package com.vinhle.smn.api.setting;

/**
 * Created by VinhLe on 5/20/2017.
 */
public class RedisKeyEntity {

    /*----------------------------------- Setting $Redis ---------------------------------------------*/

    public static final int CACHE_NORMAL_SECONDS = 900;
    public static final int CACHE_LONG_TIME_SECONDS = 172800;
    public static final int CACHE_SESSION_SECONDS = 172800;

    /*----------------------------------- Setting $Field ---------------------------------------------*/

    public static final char SPLIT_CHAR = '_';
    public static final String E_STAR = "*";

    public static final String E_CUSTOMER_SESSION = "CUSTOMER_SESSION";
    public static final String E_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
    public static final String E_CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD";
    public static final String E_CUSTOMER_ID = "CUSTOMER_ID";
    public static final String E_BILL_ID = "BILL_ID";
    public static final String E_IMPORT_ID = "IMPORT_ID";
    public static final String E_AGENCY_ID = "AGENCY_ID";
    public static final String E_PRODUCT_ID = "PRODUCT_ID";
    public static final String E_SUPPLIER_ID = "SUPPLIER_ID";
    public static final String E_CUSTOMER_SEARCH_TEXT = "CUSTOMER_SEARCH_TEXT";
    public static final String E_PRODUCT_SEARCH_TEXT = "PRODUCT_SEARCH_TEXT";
    public static final String E_BILL_SEARCH_TEXT = "BILL_SEARCH_TEXT";
    public static final String E_SUPPLIER_SEARCH_TEXT = "SUPPLIER_SEARCH_TEXT";

    /*----------------------------------- Setting $Entity ---------------------------------------------*/
    public static final String K_AGENCY = "AGENCY";
    public static final String K_PROVINCE = "PROVINCE";
    public static final String K_DISTRICT = "DISTRICT";
    public static final String K_WARD = "WARD";
    public static final String K_PRODUCT_TYPE = "PRODUCT_TYPE";
    public static final String K_BILL_STEP = "BILL_STEP";

    public static final String K_DISTRICT_BY_PROVINCE_ID = "DISTRICT_BY_PROVINCE_ID";
    public static final String K_WARD_BY_DISTRICT_ID = "WARD_BY_DISTRICT_ID";
}
