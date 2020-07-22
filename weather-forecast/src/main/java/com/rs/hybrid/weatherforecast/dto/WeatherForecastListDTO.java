package com.rs.hybrid.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class WeatherForecastListDTO {

    private long dt;

    private MainPartDTO main;

    private int visibility;

    private List<WeatherDTO> weather;

    private CloudsDTO clouds;

    private WindDTO wind;

    private int pop;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dt_txt;
}
