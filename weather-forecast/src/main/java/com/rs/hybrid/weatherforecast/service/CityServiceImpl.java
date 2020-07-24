package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.model.City;
import com.rs.hybrid.weatherforecast.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherForecastService weatherForecastService;

    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public List<City> getSortedCities() {return weatherForecastService.sortByAverageTemperature();}

}
