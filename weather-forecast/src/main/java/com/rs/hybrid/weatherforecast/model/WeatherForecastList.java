package com.rs.hybrid.weatherforecast.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherForecastList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date dt;

    @OneToOne
    private MainPart main;

    @Column
    private int visibility;

    @OneToMany
    private List<Weather> weather;

    @OneToOne
    private Clouds clouds;

    @OneToOne
    private Wind wind;

    @Column
    private int pop;

    @Column
    private Date dt_txt;

    public double getTemp() {
        return getMain().getTemp();
    }
}
