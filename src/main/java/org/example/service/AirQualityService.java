package org.example.service;

import org.example.util.WaqiApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirQualityService {

    private final WaqiApiUtil waqiApiUtil;

    @Autowired
    public AirQualityService(WaqiApiUtil waqiApiUtil) {
        this.waqiApiUtil = waqiApiUtil;
    }

    public String getAirQualityByCity(String city) {
        return waqiApiUtil.getAirQualityByCity(city);
    }
}
