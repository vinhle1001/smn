package com.vinhle.smn.setting;

/**
 * Created by VinhLe on 5/6/2017.
 */
public class AppSetting {

    /*----------------------------------- Global $Setting ---------------------------------------------*/
    public static final int SUCCESS_CODE = 1;
    public static final int INVALID_CODE = -1;

    public static final String BASE_URL = "http://192.168.0.106:11484/";
    public static final String MQTT_BASE_HOST = "ec2-54-179-149-134.ap-southeast-1.compute.amazonaws.com";
    public static final int MQTT_BASE_PORT = 1883;

    public static final String SPACE_CHAR = " ";

    public enum Fragment {
        NONE,
        SEARCH_CUSTOMER, CUSTOMER_INFORMATION,
        SEARCH_PRODUCT, PRODUCT_INFORMATION,
        BILL_INFORMATION, BILLS_OF_CUSTOMER, SEARCH_BILL, BILL_RETURNED_INFORMATION,
        SEARCH_IMPORT, IMPORT_INFORMATION,  IMPORTS_OF_SUPPLIER,
        SEARCH_SUPPLIER, SUPPLIER_INFORMATION,
        POP_BACK_STACK
    }

    /*----------------------------------- Active $Setting ---------------------------------------------*/
    public static final byte ACTIVE = 1;
    public static final byte DEACTIVATE = 0;


    /*----------------------------------- Login $Setting ---------------------------------------------*/

    public static final int WRONG_EMAIL_CODE = -2;
    public static final int WRONG_PASSWORD_CODE = -3;
    public static final String WRONG_EMAIL_MESSAGE = "Email không tồn tại!";
    public static final String WRONG_PASSWORD_MESSAGE = "Mật khẩu không đúng!";


    /*----------------------------------- Gender $Setting ---------------------------------------------*/
    public static final int GENDER_MALE_ID = 1;
    public static final int GENDER_FEMALE_ID = 2;


    /*----------------------------------- Store Bill $Setting ---------------------------------------------*/
    public static final int BILL_PRODUCT_EXCEED_LIMIT_STOCK_CODE = -3;
    public static final int BILL_PRODUCT_EXCEED_LIMIT_BEGGING_CODE = -4;
    public static final int BILL_PRODUCT_EXCEED_QUANTITY_USED_CODE = -5;


    /*----------------------------------- BillStep $Setting ---------------------------------------------*/
    public static final int BILL_STEP_PROPOSE_ID = 1;
    public static final int BILL_STEP_DELIVERY_ID = 2;
    public static final int BILL_STEP_RECEIVED_ID = 3;
}
