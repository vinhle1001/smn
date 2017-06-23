package com.vinhle.smn.api.service.interfaceservice;

import java.util.function.Function;

/**
 * Created by VinhLe on 6/19/2017.
 */
public interface MqttService {

    void subscribe(String topic);
    void publish(String topic, Object obj);
    void publish(String topic, Function func, Object arg);

}
