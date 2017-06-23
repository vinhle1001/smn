package com.vinhle.smn;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.vinhle.smn.service.MqttService;

/**
 * Created by VinhLe on 6/21/2017.
 */
public class SMNApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: Check service is started
        ActivityManager manager = (ActivityManager) getBaseContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (MqttService.class.getName().equals(service.service.getClassName())) {
                Log.d("SMNApplication", "SERVICE ALREADY STARTED " + MqttService.class.getName());
                return;
            }
        }

        Log.d("SMNApplication", "SERVICE ALREADY START " + MqttService.class.getName());
        getBaseContext().startService(new Intent(getBaseContext(), MqttService.class));
    }
}
