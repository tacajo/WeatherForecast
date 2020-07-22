package com.rs.hybrid.weatherforecast.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    private String name;

    private CoordinateDTO coord;

    private String country;

    private int timezone;

    private long sunrise;

    private long sunset;
}
