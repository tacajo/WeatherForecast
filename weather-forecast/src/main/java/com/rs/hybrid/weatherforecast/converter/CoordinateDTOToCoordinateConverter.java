package com.rs.hybrid.weatherforecast.converter;

import com.rs.hybrid.weatherforecast.dto.CoordinateDTO;
import com.rs.hybrid.weatherforecast.model.Coordinate;
import org.springframework.core.convert.converter.Converter;

public class CoordinateDTOToCoordinateConverter implements Converter<CoordinateDTO, Coordinate> {

    @Override
    public Coordinate convert(CoordinateDTO coordinateDTO) {
        return new Coordinate().builder()
                .lat(coordinateDTO.getLat())
                .lon(coordinateDTO.getLon())
                .build();
    }
}
