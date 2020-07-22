package com.rs.hybrid.weatherforecast.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateDTO {

    private float lat;

    private float lon;
}
