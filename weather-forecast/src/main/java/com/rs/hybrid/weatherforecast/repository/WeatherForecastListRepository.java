package com.rs.hybrid.weatherforecast.repository;

import com.rs.hybrid.weatherforecast.model.WeatherForecastList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherForecastListRepository extends JpaRepository<WeatherForecastList, Long> {
}
