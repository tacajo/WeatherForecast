package com.rs.hybrid.weatherforecast.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MainPartDTO {

    private double temp;

    private double feels_like;

    private double temp_min;

    private double temp_max;

    private double pressure;

    private double sea_level;

    private double grnd_level;

    private double humidity;

    private double temp_kf;

}
