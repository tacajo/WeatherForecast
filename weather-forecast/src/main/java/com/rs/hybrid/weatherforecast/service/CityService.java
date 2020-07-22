package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.model.City;

import java.util.List;

public interface CityService {

    City save(City city);

    List<City> getAll();
}
