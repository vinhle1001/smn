package com.vinhle.smn.api.service.implservice;

import com.vinhle.smn.api.service.interfaceservice.MqttService;
import com.vinhle.smn.api.springconfig.singleton.JsonMapper;
import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.function.Function;

/**
 * Created by VinhLe on 6/19/2017.
 */
@Service
public class MqttServiceImpl implements MqttService {

    /*----------------------------------- Variable $Mqtt ---------------------------------------------*/

    @Value("${mqtt.host}")
    private String mqttHost;

    @Value("${mqtt.port}")
    private int mqttPort;

    private MQTT mqtt;
    private BlockingConnection blockingConnection;

    /*----------------------------------- Method $Mqtt ---------------------------------------------*/

    private boolean connect() {
        if (blockingConnection == null || !blockingConnection.isConnected()) {
            try {
                mqtt = new MQTT();
                mqtt.setHost(mqttHost, mqttPort);
                blockingConnection = mqtt.blockingConnection();
                blockingConnection.connect();
            } catch (Exception e) {
                System.err.println("MqttServiceImpl " + e.getMessage());
            }
        }

        return blockingConnection != null && blockingConnection.isConnected();
    }

    @Override
    @Async
    public void subscribe(String topic) {
        if (connect()) {
            try {
                Topic[] topics = {new Topic(topic, QoS.AT_LEAST_ONCE)};
                blockingConnection.subscribe(topics);
            } catch (Exception e) {
                System.err.println("MqttServiceImpl " + e.getMessage());
            }
        }
    }

    @Override
    @Async
    public void publish(String topic, Object obj) {
        if (connect()) {
            try {
                blockingConnection.publish(topic, JsonMapper.getInstant().writeValueAsBytes(obj), QoS.AT_MOST_ONCE, Boolean.TRUE);
            } catch (Exception e) {
                System.err.println("MqttServiceImpl " + e.getMessage());
            }
        }
    }

    @Override
    @Async
    public void publish(String topic, Function func, Object arg) {
        if (connect()) {
            try {
                Object obj = func.apply(arg);
                blockingConnection.publish(topic, JsonMapper.getInstant().writeValueAsBytes(obj), QoS.AT_MOST_ONCE, Boolean.TRUE);
            } catch (Exception e) {
                System.err.println("MqttServiceImpl " + e.getMessage());
            }
        }
    }
}
