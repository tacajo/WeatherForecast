package com.rs.hybrid.weatherforecast.config;

import com.rs.hybrid.weatherforecast.converter.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CityDTOToCityConverter());
        registry.addConverter(new CoordinateDTOToCoordinateConverter());
        registry.addConverter(new MainPartDTOToMainPartConverter());
        registry.addConverter(new WindDTOToWindConverter());
        registry.addConverter(new CloudsDTOToCloudsConverter());
        registry.addConverter(new WeatherDTOToWeatherConverter());
        registry.addConverter(new WeatherForecastListDTOToWeatherForecastListConverter());
        registry.addConverter(new CityToCityDTOConverter());
    }
}
