package com.rs.hybrid.weatherforecast.controller;

import com.rs.hybrid.weatherforecast.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class WeatherForecastController {

    @Autowired
    private WeatherForecastService weatherForecastService;

    @PostConstruct
    public void init() {
        //weatherForecastService.getData();
    }
}
