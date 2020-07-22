package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.model.Coordinate;
import com.rs.hybrid.weatherforecast.repository.CoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordinateServiceImpl implements CoordinateService {

    @Autowired
    private CoordinateRepository coordinateRepository;

    public Coordinate save(Coordinate coordinate) {
        return coordinateRepository.save(coordinate);
    }
}
