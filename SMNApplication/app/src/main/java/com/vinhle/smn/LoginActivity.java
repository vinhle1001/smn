package com.vinhle.smn;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vinhle.smn.common.DirectionUtils;
import com.vinhle.smn.common.SharedPreferenceHelper;
import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.io.OkHttpHandler;
import com.vinhle.smn.model.request.LoginRequest;
import com.vinhle.smn.model.response.LoginResponse;
import com.vinhle.smn.setting.AppSetting;
import com.vinhle.smn.setting.UrlEntity;

/**
 * Created by VinhLe on 5/4/2017.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText mEDTUsername;
    private EditText mEDTPassword;
    private Button mBTNLogin;
    private ProgressDialog mPGDWaiting;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEDTUsername = (EditText) findViewById(R.id.edt_username);
        mEDTPassword = (EditText) findViewById(R.id.edt_password);
        mBTNLogin = (Button) findViewById(R.id.btn_login);

        mBTNLogin.setOnClickListener(btnLoginClickListener);
    }

    private void CreateAlertDialog(@StringRes int messageId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setMessage(messageId);
        builder.setCancelable(Boolean.FALSE);
        builder.setNegativeButton(R.string.done, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /*----------------------------------- Method Action Listener LoginActivity ----------------------------------------------------*/
    private View.OnClickListener btnLoginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final LoginRequest request = new LoginRequest(mEDTUsername.getText().toString(), mEDTPassword.getText().toString());
            request.setDeviceId(StringHelper.GetDeviceId(LoginActivity.this));

            mPGDWaiting = ProgressDialog.show(LoginActivity.this, getResources().getString(R.string.app_name), getResources().getString(R.string.loading), Boolean.FALSE);

            OkHttpHandler handler = new OkHttpHandler(new OkHttpHandler.CallBackRequest<LoginResponse>() {
                @Override
                public void OnResult(boolean success, LoginResponse result) {
                    if (mPGDWaiting.isShowing()) mPGDWaiting.dismiss();
                    if (success && result.code == AppSetting.SUCCESS_CODE) {
                        SharedPreferenceHelper.saveAccount(getApplicationContext(), result);
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        DirectionUtils.changeActivity(LoginActivity.this, R.anim.slide_in_from_right, R.anim.slide_out_to_left, true, intent);
                    } else if (result.code == AppSetting.WRONG_EMAIL_CODE) {
                        CreateAlertDialog(R.string.error_wrong_email);
                    } else if (result.code == AppSetting.WRONG_PASSWORD_CODE) {
                        CreateAlertDialog(R.string.error_wrong_password);
                    } else {
                        CreateAlertDialog(R.string.error_wrong_data_response);
                    }
                }
            });
            handler.execute(UrlEntity.E_ACCOUNT + UrlEntity.A_CHECK_LOGIN, LoginResponse.class, request);
        }
    };
}
