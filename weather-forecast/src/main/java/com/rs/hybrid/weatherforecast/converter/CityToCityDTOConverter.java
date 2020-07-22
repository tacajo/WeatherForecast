package com.rs.hybrid.weatherforecast.converter;

import com.rs.hybrid.weatherforecast.dto.CityDTO;
import com.rs.hybrid.weatherforecast.dto.CoordinateDTO;
import com.rs.hybrid.weatherforecast.model.City;
import org.springframework.core.convert.converter.Converter;

public class CityToCityDTOConverter implements Converter<City, CityDTO> {

    @Override
    public CityDTO convert(City city) {
        return new CityDTO().builder()
                .name(city.getName())
                .coord(new CoordinateDTO().builder()
                        .lat(city.getCoord().getLat())
                        .lon(city.getCoord().getLon())
                        .build())
                .country(city.getCountry())
                .timezone(city.getTimezone())
                .sunrise(city.getSunrise().getTime())
                .sunset(city.getSunrise().getTime())
                .build();
    }
}
