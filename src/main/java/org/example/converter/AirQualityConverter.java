package org.example.converter;

import org.example.domain.AirQuality;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class AirQualityConverter {
    public AirQuality convertJsonToAirQuality(JSONObject jsonResponse) {
        AirQuality airQuality = new AirQuality();

        airQuality.setAqi(jsonResponse.getInt("aqius"));
        airQuality.setPm25(jsonResponse.getJSONObject("p2").getDouble("conc"));
        airQuality.setPm10(jsonResponse.getJSONObject("p1").getDouble("conc"));
        airQuality.setO3(jsonResponse.optDouble("o3", 0.0));
        airQuality.setNo2(jsonResponse.optDouble("n2", 0.0));
        airQuality.setSo2(jsonResponse.optDouble("s2", 0.0));
        airQuality.setCo(jsonResponse.optDouble("co", 0.0));

        return airQuality;
    }
}
