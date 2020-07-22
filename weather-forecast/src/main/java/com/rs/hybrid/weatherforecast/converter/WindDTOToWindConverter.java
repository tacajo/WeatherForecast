package com.rs.hybrid.weatherforecast.converter;

import com.rs.hybrid.weatherforecast.dto.WindDTO;
import com.rs.hybrid.weatherforecast.model.Wind;
import org.springframework.core.convert.converter.Converter;

public class WindDTOToWindConverter implements Converter<WindDTO, Wind> {

    @Override
    public Wind convert(WindDTO windDTO) {
        return new Wind().builder()
                .speed(windDTO.getSpeed())
                .deg(windDTO.getDeg())
                .build();
    }
}
