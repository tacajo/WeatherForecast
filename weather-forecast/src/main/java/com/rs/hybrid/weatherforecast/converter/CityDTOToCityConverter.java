package com.rs.hybrid.weatherforecast.converter;

import com.rs.hybrid.weatherforecast.dto.CityDTO;
import com.rs.hybrid.weatherforecast.model.City;
import com.rs.hybrid.weatherforecast.model.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class CityDTOToCityConverter implements Converter<CityDTO, City> {

    @Override
    public City convert(CityDTO cityDTO) {
        return new City().builder()
                .name(cityDTO.getName())
                .country(cityDTO.getCountry())
                .timezone(cityDTO.getTimezone())
                .sunrise(new Date(cityDTO.getSunrise()))
                .sunset(new Date(cityDTO.getSunset()))
                .build();
    }
}
