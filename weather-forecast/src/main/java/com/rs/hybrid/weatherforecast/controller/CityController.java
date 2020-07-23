package com.rs.hybrid.weatherforecast.controller;

import com.rs.hybrid.weatherforecast.dto.CityDTO;
import com.rs.hybrid.weatherforecast.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private ConversionService conversionService;

    @GetMapping
    public ResponseEntity getCities() {
        List<CityDTO> cityDTOS = new ArrayList<>();
        cityService.getAll().forEach(city -> {
            cityDTOS.add(conversionService.convert(city, CityDTO.class));
        });
        return ResponseEntity.ok(cityDTOS);
    }


}
