package com.rs.hybrid.weatherforecast.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class APIResponse {

    private int cod;

    private String message;

    private int cnt;

    private List<WeatherForecastListDTO> list;

    private CityDTO city;
}
