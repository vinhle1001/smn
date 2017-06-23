package com.vinhle.smn.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.vinhle.smn.common.StringHelper;
import com.vinhle.smn.handler.OnMqttServiceCallback;
import com.vinhle.smn.setting.AppSetting;
import com.vinhle.smn.setting.MqttSetting;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhLe on 6/22/2017.
 */

public class MqttService extends Service {

    private static final String TAG = "MqttService";

    /**
     * The {@link MqttAndroidClient} instance this class represents
     **/
    private MqttAndroidClient client = null;
    /**
     * The {@link MqttConnectOptions} that were used to connect this client
     **/
    private MqttConnectOptions conOpt;

    private final IBinder binder = new MqttServiceBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            connect(intent != null && intent.getBooleanExtra(MqttServiceContants.ssl, Boolean.FALSE));
        } catch (MqttException e) {
            Log.e(TAG, "Client connect error: " + e.getMessage(), e);
        } finally {
            registerReceiver(broadcastReceiver, new IntentFilter(MqttSetting.ACTION_CALL_MQTT_SERVICE));
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            disconnect();
        } catch (MqttException e) {
            Log.e(TAG, "Client disconnect error: " + e.getMessage(), e);
        } finally {
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    private void connect(boolean sslConnection) throws MqttException {
        if (client != null) client.disconnect();

        String uri = null;
        String clientId = StringHelper.GetDeviceId(getBaseContext());

        if (sslConnection) {
            uri = "ssl://" + AppSetting.MQTT_BASE_HOST + ":" + AppSetting.MQTT_BASE_PORT;
        } else {
            uri = "tcp://" + AppSetting.MQTT_BASE_HOST + ":" + AppSetting.MQTT_BASE_PORT;
        }

        client = new MqttAndroidClient(getApplicationContext(), uri, clientId);
        client.setCallback(mqttCallback);
        client.connect();
    }

    private void subscribe(String topic) throws MqttException {
        if (client == null || !client.isConnected()) return;

        client.subscribe(topic, MqttServiceContants.defaultQos);
    }

    private void unsubcribe(String topic) throws MqttException {
        if (client == null || !client.isConnected()) return;

        client.unsubscribe(topic);
    }

    private void disconnect() throws MqttException {
        if (client == null || !client.isConnected()) return;

        client.disconnect();
    }

    private void publish(String topic, byte[] payload) throws MqttException {
        if (client == null || !client.isConnected()) return;

        client.publish(topic, payload, MqttServiceContants.defaultQos, Boolean.TRUE);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!intent.hasExtra(MqttServiceContants.action)) return;

            try {
                switch (intent.getStringExtra(MqttServiceContants.action)) {
                    case MqttServiceContants.subscribe:
                        subscribe(intent.getStringExtra(MqttServiceContants.topic));
                        break;
                    case MqttServiceContants.unsubscribe:
                        unsubcribe(intent.getStringExtra(MqttServiceContants.topic));
                        break;
                    case MqttServiceContants.publish:

                        break;
                }
            } catch (Exception e) {
                Log.e(TAG, "Error received message: " + e.getMessage(), e);
            }

        }
    };

    private MqttCallback mqttCallback = new MqttCallback() {
        @Override
        public void connectionLost(Throwable cause) {
            client = null;
            for (OnMqttServiceCallback callback : ((MqttServiceBinder) binder).callbacks) {
                callback.OnConnectionLost();
            }
        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            for (OnMqttServiceCallback callback : ((MqttServiceBinder) binder).callbacks) {
                callback.OnMessageArrived(topic, message.getPayload());
            }
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {
        }
    };

    public class MqttServiceBinder extends Binder {

        private List<OnMqttServiceCallback> callbacks = new ArrayList<>();

        public void registerCallback(OnMqttServiceCallback callback) {
            if (callback == null || callbacks.contains(callback))
                return;

            callbacks.add(callback);
        }

        public void unregisterCallback(OnMqttServiceCallback callback) {
            if (callback == null || !callbacks.contains(callback))
                return;

            callbacks.remove(callback);
        }

    }
}
