package com.rs.hybrid.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToOne
    private Coordinate coord;

    @Column
    private String country;

    @Column
    private int timezone;

    @Column
    private double avg_temp;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sunrise;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sunset;

    @OneToMany
    private List<WeatherForecastList> weatherForecastList = new ArrayList<>();

    @OneToMany
    private List<MainPart> mainParts = new ArrayList<>();
}
