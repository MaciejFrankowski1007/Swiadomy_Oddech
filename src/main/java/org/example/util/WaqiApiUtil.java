package org.example.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class WaqiApiUtil {

    private static final String BASE_URL = "https://api.waqi.info/feed";

    @Value("${waqi.api.token}")
    private String apiToken;

    public String getAirQualityByCity(String city) {
        String url = String.format("%s/%s/?token=%s", BASE_URL, city, apiToken);
        System.out.println("Request URL: " + url);
        System.out.println("API Token: " + apiToken);
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            return "{\"status\":\"error\",\"data\":\"" + e.getMessage() + "\"}";
        }
    }
}

