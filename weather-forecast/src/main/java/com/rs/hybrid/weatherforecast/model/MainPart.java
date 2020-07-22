package com.rs.hybrid.weatherforecast.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double temp;

    @Column
    private double feels_like;

    @Column
    private double temp_min;

    @Column
    private double temp_max;

    @Column
    private double pressure;

    @Column
    private double sea_level;

    @Column
    private double grnd_level;

    @Column
    private double humidity;

    @Column
    private double temp_kf;
}
