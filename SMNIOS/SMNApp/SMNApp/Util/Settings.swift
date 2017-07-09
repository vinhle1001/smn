//
//  Settings.swift
//  SMNApp
//
//  Created by Nguyen Thuan Tan on 6/24/17.
//  Copyright © 2017 Nguyen Thuan Tan. All rights reserved.
//

import Foundation

public class Settings {
    public static var API_BASE_URL = "http://127.0.0.1:11484/api/"
    
    public static var MQTT_BASE_HOST = "ec2-54-179-149-134.ap-southeast-1.compute.amazonaws.com"
    public static var MQTT_BASE_PORT = 1883
    
    /*----------------------------------- Active $Setting ---------------------------------------------*/
    
    public static var ACTIVE = 1;
    public static var DEACTIVATE = 0;
    
    /*----------------------------------- Login $Setting ---------------------------------------------*/
    
    public static var WRONG_EMAIL_CODE = -2;
    public static var WRONG_PASSWORD_CODE = -3;
    public static var WRONG_EMAIL_MESSAGE = "Email không tồn tại!";
    public static var WRONG_PASSWORD_MESSAGE = "Mật khẩu không đúng!";
    
    
    /*----------------------------------- Gender $Setting ---------------------------------------------*/
    public static var GENDER_MALE_ID = 1;
    public static var GENDER_FEMALE_ID = 2;
    
    
    /*----------------------------------- Store Bill $Setting ---------------------------------------------*/
    public static var BILL_PRODUCT_EXCEED_LIMIT_STOCK_CODE = -3;
    public static var BILL_PRODUCT_EXCEED_LIMIT_BEGGING_CODE = -4;
    public static var BILL_PRODUCT_EXCEED_QUANTITY_USED_CODE = -5;
    
    
    /*----------------------------------- BillStep $Setting ---------------------------------------------*/
    public static var BILL_STEP_PROPOSE_ID = 1;
    public static var BILL_STEP_DELIVERY_ID = 2;
    public static var BILL_STEP_RECEIVED_ID = 3;
}
