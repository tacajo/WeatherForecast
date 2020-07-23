package com.rs.hybrid.weatherforecast.controller;

import com.rs.hybrid.weatherforecast.dto.CityDTO;
import com.rs.hybrid.weatherforecast.service.CityService;
import com.rs.hybrid.weatherforecast.service.WeatherForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class WeatherForecastController {

    Logger logger = LoggerFactory.getLogger(WeatherForecastController.class);

    @Autowired
    private WeatherForecastService weatherForecastService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ConversionService conversionService;

    @PostConstruct
    public void init() {
        //weatherForecastService.getData();
    }

    @GetMapping(value = "/sort-by-avg-temp")
    public ResponseEntity averageTemp() {
        if(weatherForecastService.sortByAverageTemperature() == null)
            return ResponseEntity.noContent().build();

        List<CityDTO> cityDTOS = new ArrayList<>();
        weatherForecastService.sortByAverageTemperature().forEach(city -> {
            cityDTOS.add(conversionService.convert(city, CityDTO.class));
        });
        return ResponseEntity.ok(cityDTOS);
    }

    @GetMapping(value = "/{startDate}/{endDate}")
    private ResponseEntity getAverageTemp(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date startDate,
                                          @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date endDate) {
        if (!weatherForecastService.validDates(startDate, endDate)) {
            logger.error("DATE RANGE: {} - {}", new Date(), new Date(new Date().getTime() + 5 * 86400000));
            return ResponseEntity.badRequest().body(String.format("DATE RANGE: %s - %s", new Date(), new Date(new Date().getTime() + 5 * 86400000)));
        }
        if(weatherForecastService.averageTemp(startDate, endDate) == null)
            return ResponseEntity.noContent().build();

        List<CityDTO> cityDTOS = new ArrayList<>();
        weatherForecastService.averageTemp(startDate, endDate).forEach(city -> {
            cityDTOS.add(conversionService.convert(city, CityDTO.class));
        });
        return ResponseEntity.ok(cityDTOS);
    }

    @GetMapping(value = "/{id}/{startDate}/{endDate}")
    private ResponseEntity getCityAverageTemp(@PathVariable("id") Long cityId,
                                              @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date startDate,
                                              @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date endDate) {
        if (!weatherForecastService.validDates(startDate, endDate)) {
            logger.error("DATE RANGE: {} - {}", new Date(), new Date(new Date().getTime() + 5 * 86400000));
            return ResponseEntity.badRequest().body(String.format("DATE RANGE: %s - %s", new Date(), new Date(new Date().getTime() + 5 * 86400000)));
        }

        if (weatherForecastService.cityAverageTemp(startDate, endDate, cityId) == null) {
            logger.error("City with id {} does not exist.", cityId);
            return ResponseEntity.badRequest().body(String.format("City with id %s does not exist.", cityId));
        }

        return ResponseEntity.ok(conversionService.convert(weatherForecastService.cityAverageTemp(startDate, endDate, cityId), CityDTO.class));
    }
}
