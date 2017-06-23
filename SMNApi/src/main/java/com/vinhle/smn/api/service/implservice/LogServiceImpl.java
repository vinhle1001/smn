package com.vinhle.smn.api.service.implservice;

import com.vinhle.smn.api.model.sql.SmnLogEntity;
import com.vinhle.smn.api.repository.LogRepository;
import com.vinhle.smn.api.service.interfaceservice.LogService;
import com.vinhle.smn.api.setting.LogSetting;
import com.vinhle.smn.api.springconfig.singleton.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by VinhLe on 5/24/2017.
 */
@Service
public class LogServiceImpl implements LogService {

    /*----------------------------------- Variable $LogService ---------------------------------------------*/

    @Autowired
    LogRepository logRepository;

    /*----------------------------------- Method $LogService ---------------------------------------------*/

    @Override
    @Async
    public void saveException(String source, String url, String deviceId, Object contentResponse) {
        try {
            SmnLogEntity logEntity = new SmnLogEntity();
            logEntity.setLogSource(source);
            logEntity.setLogUrl(url);
            logEntity.setDeviceId(deviceId);
            logEntity.setLogType(LogSetting.LOG_TYPE_EXCEPTION);
            logEntity.setContentResponse(String.valueOf(contentResponse));
            logRepository.save(logEntity);
        } catch (Exception e) {

        }
    }

    @Override
    @Async
    public void save(String type, String source, String url, String deviceId, Object contentRequest, Object contentResponse, float timeSpan) {
        try {
            SmnLogEntity logEntity = new SmnLogEntity();
            logEntity.setLogSource(source);
            logEntity.setLogUrl(url);
            logEntity.setDeviceId(deviceId);
            logEntity.setLogType(type);
            logEntity.setContentRequest(JsonMapper.getInstant().writeValueAsString(contentRequest));
            logEntity.setContentResponse(JsonMapper.getInstant().writeValueAsString(contentResponse));
            logEntity.setTimeSpan(timeSpan);
            logRepository.save(logEntity);
        } catch (Exception e) {

        }
    }
}
