package com.rs.hybrid.weatherforecast.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CityDTO {

    private String name;

    private CoordinateDTO coord;

    private String country;

    private int timezone;

    private long sunrise;

    private long sunset;
}
