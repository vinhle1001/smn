package com.vinhle.smn.api.setting;

/**
 * Created by VinhLe on 5/6/2017.
 */
public class AppSetting {

    /*----------------------------------- Global $Setting ---------------------------------------------*/

    public static final int SUCCESS_CODE = 1;
    public static final int INVALID_CODE = -1;

    public static final String SUCCESS_MESSAGE = "Thao tác thành công";
    public static final String INVALID_MESSAGE = "Đã có lỗi trong quá trình truy cập!";

    /*----------------------------------- Login $Setting ---------------------------------------------*/

    public static final int WRONG_EMAIL_CODE = -2;
    public static final int WRONG_PASSWORD_CODE = -3;
    public static final String WRONG_EMAIL_MESSAGE = "Email không tồn tại!";
    public static final String WRONG_PASSWORD_MESSAGE = "Mật khẩu không đúng!";

    /*----------------------------------- Store Bill $Setting ---------------------------------------------*/
    public static final int BILL_PRODUCT_EXCEED_LIMIT_STOCK_CODE = -3;
    public static final int BILL_PRODUCT_EXCEED_LIMIT_BEGGING_CODE = -4;
    public static final int BILL_PRODUCT_EXCEED_QUANTITY_USED_CODE = -5;

    /*----------------------------------- Store Import $Setting ---------------------------------------------*/
    public static final int IMPORT_PRODUCT_EXCEED_LIMIT_STOCK_CODE = -3;

    /*----------------------------------- Agency $Setting ---------------------------------------------*/
    public static final int WAREHOUSES_RETURNED_GOODS_ID = 1;

}
