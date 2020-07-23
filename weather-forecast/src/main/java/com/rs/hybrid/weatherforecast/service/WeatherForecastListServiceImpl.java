package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.model.WeatherForecastList;
import com.rs.hybrid.weatherforecast.repository.WeatherForecastListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherForecastListServiceImpl implements WeatherForecastListService {

    @Autowired
    private WeatherForecastListRepository weatherForecastListRepository;

    @Override
    public WeatherForecastList save(WeatherForecastList weatherForecastList) {
        return weatherForecastListRepository.save(weatherForecastList);
    }
}
