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
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private long weatherId;

    @Column
    private String main;

    @Column
    private String description;

    @Column
    private String icon;
}
