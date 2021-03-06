package com.rs.hybrid.weatherforecast.converter;

import com.rs.hybrid.weatherforecast.dto.CityDTO;
import com.rs.hybrid.weatherforecast.model.City;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Date;

public class CityDTOToCityConverter implements Converter<CityDTO, City> {

    @Override
    public City convert(CityDTO cityDTO) {
        return new City().builder()
                .name(cityDTO.getName())
                .country(cityDTO.getCountry())
                .timezone(cityDTO.getTimezone())
                .sunrise(new Date(cityDTO.getSunrise() * 1000))
                .sunset(new Date(cityDTO.getSunset() * 1000))
                .weatherForecastList(new ArrayList<>())
                .build();
    }
}
