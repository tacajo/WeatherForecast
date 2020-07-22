package com.rs.hybrid.weatherforecast.converter;

import com.rs.hybrid.weatherforecast.dto.WeatherForecastListDTO;
import com.rs.hybrid.weatherforecast.model.WeatherForecastList;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Date;

public class WeatherForecastListDTOToWeatherForecastListConverter implements Converter<WeatherForecastListDTO, WeatherForecastList> {
    @Override
    public WeatherForecastList convert(WeatherForecastListDTO weatherForecastListDTO) {
        return new WeatherForecastList().builder()
                .dt(new Date(weatherForecastListDTO.getDt()))
                .visibility(weatherForecastListDTO.getVisibility())
                .pop(weatherForecastListDTO.getPop())
                .dt_txt(weatherForecastListDTO.getDt_txt())
                .weather(new ArrayList<>())
                .build();
    }
}
