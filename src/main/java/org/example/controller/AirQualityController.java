package org.example.controller;

import org.example.service.AirQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AirQualityController {

    private final AirQualityService airQualityService;

    @Autowired
    public AirQualityController(AirQualityService airQualityService) {
        this.airQualityService = airQualityService;
    }

    @GetMapping("/getAirQualityByCity")
    public String getAirQualityByCity(@RequestParam String city, Model model) {
        String airQualityData = airQualityService.getAirQualityByCity(city);
        model.addAttribute("airQualityData", airQualityData);
        return "result";
    }
}


