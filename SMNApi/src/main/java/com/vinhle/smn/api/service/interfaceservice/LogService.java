package com.vinhle.smn.api.service.interfaceservice;

/**
 * Created by VinhLe on 5/24/2017.
 */
public interface LogService {

    void saveException(String source, String url, String deviceId, Object contentResponse);

    void save(String type, String source, String url, String deviceId, Object contentRequest, Object contentResponse, float timeSpan);

}
