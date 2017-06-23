package com.vinhle.smn.api.commandline;

import com.vinhle.smn.api.common.StringHelper;
import com.vinhle.smn.api.model.response.ProductOfAgencyResponse;
import com.vinhle.smn.api.model.sql.SmnAgencyEntity;
import com.vinhle.smn.api.repository.AgencyRepository;
import com.vinhle.smn.api.service.interfaceservice.AgencyProductService;
import com.vinhle.smn.api.service.interfaceservice.MqttService;
import com.vinhle.smn.api.setting.MqttSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by VinhLe on 6/20/2017.
 */
@Component
public class MqttPublishProductOfAgencyRunner implements CommandLineRunner {

    /*----------------------------------- Variable $MqttPublishProductOfAgencyRunner ---------------------------------------------*/

    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    AgencyProductService agencyProductService;

    @Autowired
    MqttService mqttService;

    /*----------------------------------- Method $MqttPublishProductOfAgencyRunner ---------------------------------------------*/

    @Override
    public void run(String... strings) throws Exception {
        List<SmnAgencyEntity> agencyEntities = (List<SmnAgencyEntity>) agencyRepository.findAll();
        agencyEntities.forEach(a -> {
            try {
                List<ProductOfAgencyResponse> responses = agencyProductService.getProductOfAgency(a.getAgencyId());
                mqttService.publish(StringHelper.ConcatWithSplit(MqttSetting.SPLIT_CHAR, MqttSetting.TOPIC_PRODUCT_OF_AGENCY, String.valueOf(a.getAgencyId())), responses);
            } catch (Exception e) {
                System.err.println("MqttPublishProductOfAgencyRunner " + e.getMessage());
            }
        });
    }
}
