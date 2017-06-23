package com.vinhle.smn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.vinhle.smn.common.DirectionUtils;
import com.vinhle.smn.common.SharedPreferenceHelper;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.io.OkHttpHandler;
import com.vinhle.smn.model.request.BaseRequest;
import com.vinhle.smn.model.request.TextRequest;
import com.vinhle.smn.model.response.AgencyResponse;
import com.vinhle.smn.model.response.BillStepResponse;
import com.vinhle.smn.model.response.DistrictResponse;
import com.vinhle.smn.model.response.LoginResponse;
import com.vinhle.smn.model.response.ProductTypeResponse;
import com.vinhle.smn.model.response.ProvinceResponse;
import com.vinhle.smn.model.response.WardResponse;
import com.vinhle.smn.setting.UrlEntity;

/**
 * Created by VinhLe on 5/9/2017.
 */

public class SplashActivity extends AppCompatActivity {

    private static int TOTAL_STEP = 6;

    private TextView mTVVersion;
    private CircularProgressBar mCPBLoading;
    private int step = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_splash);
        mTVVersion = (TextView) findViewById(R.id.version);
        mCPBLoading = (CircularProgressBar) findViewById(R.id.cpb_loading);

        mTVVersion.append(BuildConfig.VERSION_NAME);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final BaseRequest request = new BaseRequest();
        request.setDeviceId(StringHelper.GetDeviceId(getApplicationContext()));
        // TODO: Load Province
        OkHttpHandler handlerProvince = new OkHttpHandler(new OkHttpHandler.CallBackRequest<ProvinceResponse[]>() {
            @Override
            public void OnResult(boolean success, ProvinceResponse[] result) {
                try {
                    if (success)
                        SharedPreferenceHelper.saveProvinces(SplashActivity.this, result);
                    afterLoadResource();
                    Log.i("SplashActivity", "Load provinces is_success: " + success);
                } catch (Exception e) {
                    Log.e("SplashActivity", e.getMessage(), e);
                }
            }
        });
        handlerProvince.execute(UrlEntity.E_RESOURCE + UrlEntity.A_GET_ALL_PROVINCE, ProvinceResponse[].class, request);
        // TODO: Load District
        OkHttpHandler handlerDistrict = new OkHttpHandler(new OkHttpHandler.CallBackRequest<DistrictResponse[]>() {
            @Override
            public void OnResult(boolean success, DistrictResponse[] result) {
                try {
                    if (success)
                        SharedPreferenceHelper.saveDistricts(SplashActivity.this, result);
                    afterLoadResource();
                    Log.i("SplashActivity", "Load district is_success: " + success);
                } catch (Exception e) {
                    Log.e("SplashActivity", e.getMessage(), e);
                }
            }
        });
        handlerDistrict.execute(UrlEntity.E_RESOURCE + UrlEntity.A_GET_ALL_DISTRICT, DistrictResponse[].class, request);
        // TODO: Load Ward
        OkHttpHandler handlerWard = new OkHttpHandler(new OkHttpHandler.CallBackRequest<WardResponse[]>() {
            @Override
            public void OnResult(boolean success, WardResponse[] result) {
                try {
                    if (success)
                        SharedPreferenceHelper.saveWards(SplashActivity.this, result);
                    afterLoadResource();
                    Log.i("SplashActivity", "Load ward is_success: " + success);
                } catch (Exception e) {
                    Log.e("SplashActivity", e.getMessage(), e);
                }
            }
        });
        handlerWard.execute(UrlEntity.E_RESOURCE + UrlEntity.A_GET_ALL_WARD, WardResponse[].class, request);
        // TODO: Load ProductType
        OkHttpHandler handlerProductType = new OkHttpHandler(new OkHttpHandler.CallBackRequest<ProductTypeResponse[]>() {
            @Override
            public void OnResult(boolean success, ProductTypeResponse[] result) {
                try {
                    if (success)
                        SharedPreferenceHelper.saveProductTypes(SplashActivity.this, result);
                    afterLoadResource();
                    Log.i("SplashActivity", "Load ward is_success: " + success);
                } catch (Exception e) {
                    Log.e("SplashActivity", e.getMessage(), e);
                }
            }
        });
        handlerProductType.execute(UrlEntity.E_RESOURCE + UrlEntity.A_GET_ALL_PRODUCT_TYPE, ProductTypeResponse[].class, request);
        // TODO: Load Agency
        OkHttpHandler handlerAgency = new OkHttpHandler(new OkHttpHandler.CallBackRequest<AgencyResponse[]>() {
            @Override
            public void OnResult(boolean success, AgencyResponse[] result) {
                try {
                    if (success)
                        SharedPreferenceHelper.saveAgencies(SplashActivity.this, result);
                    afterLoadResource();
                    Log.i("SplashActivity", "Load agency is_success: " + success);
                } catch (Exception e) {
                    Log.e("SplashActivity", e.getMessage(), e);
                }
            }
        });
        handlerAgency.execute(UrlEntity.E_RESOURCE + UrlEntity.A_GET_ALL_AGENCY, AgencyResponse[].class, request);
        // TODO: Load BillStep
        OkHttpHandler handlerBillStep = new OkHttpHandler(new OkHttpHandler.CallBackRequest<BillStepResponse[]>() {
            @Override
            public void OnResult(boolean success, BillStepResponse[] result) {
                try {
                    if (success)
                        SharedPreferenceHelper.saveBillSteps(SplashActivity.this, result);
                    afterLoadResource();
                    Log.i("SplashActivity", "Load bill step is_success: " + success);
                } catch (Exception e) {
                    Log.e("SplashActivity", e.getMessage(), e);
                }
            }
        });
        handlerBillStep.execute(UrlEntity.E_RESOURCE + UrlEntity.A_GET_ALL_BILL_STEP, BillStepResponse[].class, request);

    }

    private synchronized void afterLoadResource() {
        step += 1;
        mCPBLoading.setProgress(step * 100.0f / TOTAL_STEP);

        if (step >= TOTAL_STEP) {
            LoginResponse response = SharedPreferenceHelper.getAccount(getApplicationContext());
            if (response == null) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                DirectionUtils.changeActivity(SplashActivity.this, R.anim.slide_in_from_right, R.anim.slide_out_to_left, true, intent);
            } else {
                OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<LoginResponse>() {
                    @Override
                    public void OnResult(boolean success, LoginResponse result) {
                        Intent intent;
                        if (success) {
                            intent = new Intent(SplashActivity.this, DashboardActivity.class);
                            DirectionUtils.changeActivity(SplashActivity.this, R.anim.slide_in_from_right, R.anim.slide_out_to_left, true, intent);
                        } else {
                            SharedPreferenceHelper.removeAccount(getApplicationContext());
                            intent = new Intent(SplashActivity.this, LoginActivity.class);
                            DirectionUtils.changeActivity(SplashActivity.this, R.anim.slide_in_from_right, R.anim.slide_out_to_left, true, intent);
                        }
                    }
                });
                TextRequest request = new TextRequest();
                request.setText(response.accountSession);
                request.setDeviceId(StringHelper.GetDeviceId(getApplicationContext()));
                handler.execute(UrlEntity.E_ACCOUNT + UrlEntity.A_GET_ACCOUNT_INFO_BY_SESSION, LoginResponse.class, request);
            }
        }
    }
}
