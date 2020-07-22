package com.rs.hybrid.weatherforecast.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherDTO {

    private long id;

    private String main;

    private String description;

    private String icon;
}
