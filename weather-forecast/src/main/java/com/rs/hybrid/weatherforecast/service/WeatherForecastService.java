package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.model.City;

import java.util.Date;
import java.util.List;

public interface WeatherForecastService {

    void getData();

    List<City> sortByAverageTemperature();

    boolean validDates(Date startDate, Date endDate);

    List<City> averageTemp(Date startDate, Date endDate);

    City cityAverageTemp(Date startDate, Date endDate, Long cityId);
}
