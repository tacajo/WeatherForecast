package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.model.Weather;
import com.rs.hybrid.weatherforecast.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService{

    @Autowired
    private WeatherRepository weatherRepository;

    public Weather save(Weather weather) {
        return weatherRepository.save(weather);
    }
}
