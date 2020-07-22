package com.rs.hybrid.weatherforecast.converter;

import com.rs.hybrid.weatherforecast.dto.WeatherDTO;
import com.rs.hybrid.weatherforecast.model.Weather;
import org.springframework.core.convert.converter.Converter;

public class WeatherDTOToWeatherConverter implements Converter<WeatherDTO, Weather> {

    @Override
    public Weather convert(WeatherDTO weatherDTO) {
        return new Weather().builder()
                .weatherId(weatherDTO.getId())
                .description(weatherDTO.getDescription())
                .icon(weatherDTO.getIcon())
                .main(weatherDTO.getMain())
                .build();
    }
}
