package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.model.City;
import com.rs.hybrid.weatherforecast.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    public City save(City city) {
        return cityRepository.save(city);
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public List<City> averageTemp() {
        return cityRepository.findAll().stream()
                .sorted(Comparator.comparing(City::getAvg_temp).reversed())
                .collect(Collectors.toList());
    }


}
