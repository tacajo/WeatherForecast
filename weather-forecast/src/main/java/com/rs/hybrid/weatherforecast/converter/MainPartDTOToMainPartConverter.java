package com.rs.hybrid.weatherforecast.converter;

import com.rs.hybrid.weatherforecast.dto.MainPartDTO;
import com.rs.hybrid.weatherforecast.model.MainPart;
import org.springframework.core.convert.converter.Converter;

public class MainPartDTOToMainPartConverter implements Converter<MainPartDTO, MainPart> {

    @Override
    public MainPart convert(MainPartDTO mainPartDTO) {
        return new MainPart().builder()
                .temp(mainPartDTO.getTemp())
                .feels_like(mainPartDTO.getFeels_like())
                .temp_min(mainPartDTO.getTemp_min())
                .temp_max(mainPartDTO.getTemp_max())
                .pressure(mainPartDTO.getPressure())
                .sea_level(mainPartDTO.getSea_level())
                .grnd_level(mainPartDTO.getGrnd_level())
                .humidity(mainPartDTO.getHumidity())
                .temp_kf(mainPartDTO.getTemp_kf())
                .build();
    }
}
