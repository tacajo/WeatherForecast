package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.model.City;
import com.rs.hybrid.weatherforecast.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;

    public City save(City city) {
        return cityRepository.save(city);
    }
}
