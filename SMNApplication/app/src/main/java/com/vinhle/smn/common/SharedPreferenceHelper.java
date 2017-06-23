package com.vinhle.smn.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vinhle.smn.model.response.AgencyResponse;
import com.vinhle.smn.model.response.BillStepResponse;
import com.vinhle.smn.model.response.DistrictResponse;
import com.vinhle.smn.model.response.LoginResponse;
import com.vinhle.smn.model.response.ProductTypeResponse;
import com.vinhle.smn.model.response.ProvinceResponse;
import com.vinhle.smn.model.response.WardResponse;

import java.io.IOException;

/**
 * Created by VinhLe on 5/6/2017.
 */
public class SharedPreferenceHelper {

    private static final String KEY_ACCOUNT_DATA = "smn_account";
    private static final String KEY_ACCOUNT_ID = "smn_account_id";
    private static final String KEY_ACCOUNT_EMAIL = "smn_account_email";
    private static final String KEY_ACCOUNT_FULLNAME = "smn_account_fullname";
    private static final String KEY_ACCOUNT_SESSION = "smn_account_session";


    private static final String KEY_RESOURCE_DATA = "smn_resource";
    private static final String KEY_RESOURCE_AGENCY = "smn_resource_agency";
    private static final String KEY_RESOURCE_PROVINCE = "smn_resource_province";
    private static final String KEY_RESOURCE_DISTRICT = "smn_resource_district";
    private static final String KEY_RESOURCE_WARD = "smn_resource_ward";
    private static final String KEY_RESOURCE_PRODUCT_TYPE = "smn_resource_product_type";
    private static final String KEY_RESOURCE_BILL_STEP = "smn_resource_bill_step";


    /*-----------------------------------Method about account ----------------------------------------------------*/
    public static void saveAccount(Context context, LoginResponse response) {
        if (response == null)
            return;

        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_ACCOUNT_DATA, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(KEY_ACCOUNT_ID, response.accountId).commit();
        sharedPreferences.edit().putString(KEY_ACCOUNT_EMAIL, response.accountEmail).commit();
        sharedPreferences.edit().putString(KEY_ACCOUNT_FULLNAME, response.accountFullName).commit();
        sharedPreferences.edit().putString(KEY_ACCOUNT_SESSION, response.accountSession).commit();
    }

    public static LoginResponse getAccount(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_ACCOUNT_DATA, Context.MODE_PRIVATE);

        LoginResponse response = new LoginResponse();

        response.accountId = sharedPreferences.getInt(KEY_ACCOUNT_ID, 0);
        if (response.accountId < 1) return null;
        response.accountEmail = sharedPreferences.getString(KEY_ACCOUNT_EMAIL, "");
        response.accountFullName = sharedPreferences.getString(KEY_ACCOUNT_FULLNAME, "");
        response.accountSession = sharedPreferences.getString(KEY_ACCOUNT_SESSION, "");
        return response;
    }

    public static void removeAccount(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_ACCOUNT_DATA, Context.MODE_PRIVATE);

        sharedPreferences.edit().remove(KEY_ACCOUNT_ID).commit();
        sharedPreferences.edit().remove(KEY_ACCOUNT_EMAIL).commit();
        sharedPreferences.edit().remove(KEY_ACCOUNT_FULLNAME).commit();
        sharedPreferences.edit().remove(KEY_ACCOUNT_SESSION).commit();
    }

    /*-----------------------------------Method about resource ----------------------------------------------------*/
    public static void saveAgencies(Context context, AgencyResponse[] response) throws Exception {
        if (response == null)
            return;

        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(KEY_RESOURCE_AGENCY, JacksonHelper.getInstance().getObjectMapper().writeValueAsString(response)).commit();
    }

    public static AgencyResponse[] getAgencies(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        String data = sharedPreferences.getString(KEY_RESOURCE_AGENCY, "");
        return JacksonHelper.getInstance().getObjectMapper().readValue(data, AgencyResponse[].class);
    }

    public static void removeAgencies(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(KEY_RESOURCE_AGENCY).commit();
    }

    public static void saveProvinces(Context context, ProvinceResponse[] response) throws Exception {
        if (response == null)
            return;

        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(KEY_RESOURCE_PROVINCE, JacksonHelper.getInstance().getObjectMapper().writeValueAsString(response)).commit();
    }

    public static ProvinceResponse[] getProvinces(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        String data = sharedPreferences.getString(KEY_RESOURCE_PROVINCE, "");
        return JacksonHelper.getInstance().getObjectMapper().readValue(data, ProvinceResponse[].class);
    }

    public static void removeProvinces(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(KEY_RESOURCE_PROVINCE).commit();
    }

    public static void saveDistricts(Context context, DistrictResponse[] response) throws Exception {
        if (response == null)
            return;

        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(KEY_RESOURCE_DISTRICT, JacksonHelper.getInstance().getObjectMapper().writeValueAsString(response)).commit();
    }

    public static DistrictResponse[] getDistricts(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        String data = sharedPreferences.getString(KEY_RESOURCE_DISTRICT, "");
        return JacksonHelper.getInstance().getObjectMapper().readValue(data, DistrictResponse[].class);
    }

    public static void removeDistricts(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(KEY_RESOURCE_DISTRICT).commit();
    }

    public static void saveWards(Context context, WardResponse[] response) throws Exception {
        if (response == null)
            return;

        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(KEY_RESOURCE_WARD, JacksonHelper.getInstance().getObjectMapper().writeValueAsString(response)).commit();
    }

    public static WardResponse[] getWards(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        String data = sharedPreferences.getString(KEY_RESOURCE_WARD, "");
        return JacksonHelper.getInstance().getObjectMapper().readValue(data, WardResponse[].class);
    }

    public static void removeWards(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(KEY_RESOURCE_WARD).commit();
    }

    public static void saveProductTypes(Context context, ProductTypeResponse[] response) throws Exception {
        if (response == null)
            return;

        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(KEY_RESOURCE_PRODUCT_TYPE, JacksonHelper.getInstance().getObjectMapper().writeValueAsString(response)).commit();
    }

    public static ProductTypeResponse[] getProductTypes(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        String data = sharedPreferences.getString(KEY_RESOURCE_PRODUCT_TYPE, "");
        return JacksonHelper.getInstance().getObjectMapper().readValue(data, ProductTypeResponse[].class);
    }

    public static void removeProductTypes(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(KEY_RESOURCE_PRODUCT_TYPE).commit();
    }

    public static void saveBillSteps(Context context, BillStepResponse[] response) throws Exception {
        if (response == null)
            return;

        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(KEY_RESOURCE_BILL_STEP, JacksonHelper.getInstance().getObjectMapper().writeValueAsString(response)).commit();
    }

    public static BillStepResponse[] getBillSteps(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        String data = sharedPreferences.getString(KEY_RESOURCE_BILL_STEP, "");
        return JacksonHelper.getInstance().getObjectMapper().readValue(data, BillStepResponse[].class);
    }

    public static void removeBillSteps(Context context) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_RESOURCE_DATA, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(KEY_RESOURCE_BILL_STEP).commit();
    }
}
