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
public class Wind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double speed;

    @Column
    private double deg;
}
