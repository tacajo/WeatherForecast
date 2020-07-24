package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    Optional<City> findById(Long id);

    City save(City city);

    List<City> getAll();

    List<City> getSortedCities();
}
