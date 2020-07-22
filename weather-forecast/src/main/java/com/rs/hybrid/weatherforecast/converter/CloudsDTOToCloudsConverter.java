package com.rs.hybrid.weatherforecast.converter;

import com.rs.hybrid.weatherforecast.dto.CloudsDTO;
import com.rs.hybrid.weatherforecast.model.Clouds;
import org.springframework.core.convert.converter.Converter;

public class CloudsDTOToCloudsConverter implements Converter<CloudsDTO, Clouds> {

    @Override
    public Clouds convert(CloudsDTO cloudsDTO) {
        return new Clouds().builder()
                .fieldAll(cloudsDTO.getAll())
                .build();
    }
}
