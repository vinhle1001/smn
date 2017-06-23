package com.vinhle.smn.handler;

/**
 * Created by VinhLe on 6/22/2017.
 */

public interface OnMqttServiceCallback {

    void OnMessageArrived(String topic, byte[] payload) throws Exception;

    void OnConnectionLost();

}
