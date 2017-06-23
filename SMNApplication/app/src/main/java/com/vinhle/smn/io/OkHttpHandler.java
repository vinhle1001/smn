package com.vinhle.smn.io;

import android.os.AsyncTask;
import android.util.Log;

import com.vinhle.smn.common.HttpBodyHelper;
import com.vinhle.smn.setting.AppSetting;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by VinhLe on 5/6/2017.
 */

public class OkHttpHandler extends AsyncTask {

    /*----------------------------------- @Interface ----------------------------------------------------*/
    public interface CallBackRequest<T> {
        void OnResult(boolean success, T result);
    }

    /*----------------------------------- Variable @OkHttpHandler ----------------------------------------------------*/
    private OkHttpClient okHttpClient = new OkHttpClient();
    private CallBackRequest callBackRequest;


    public OkHttpHandler(CallBackRequest callBackRequest) {
        this.callBackRequest = callBackRequest;
    }


    /*----------------------------------- Method @OkHttpHandler ----------------------------------------------------*/
    @Override
    protected Object doInBackground(Object[] params) {
        Object result = null;

        try {
            Class clazz = (Class) params[1];

            RequestBody body;
            if (params.length > 2 && params[2] != null) {

                body = RequestBody.create(MediaType.parse("application/bytes; charset=utf-8"), HttpBodyHelper.encrypt(params[2]).getBytes());
            }
            else
                body = RequestBody.create(MediaType.parse("application/bytes; charset=utf-8"), new byte[0]);

            Request request = new Request.Builder()
                    .url(AppSetting.BASE_URL + params[0].toString())
                /*.header()*/
                    .post(body)
                    .build();


            Response response = okHttpClient.newCall(request).execute();
            byte[] data = response.body().bytes();
            result = HttpBodyHelper.decrypt(data, clazz);
            Log.i("OkHttpHandler", params[0].toString() + " : " + new String(data, "UTF-8"));
        } catch (Exception e) {
            Log.e("OkHttpHandler", e.getMessage(), e);
        } finally {
            return result;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        if (callBackRequest != null) {
            callBackRequest.OnResult(o != null, o);
        }
    }
}
