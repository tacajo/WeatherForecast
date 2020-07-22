package com.rs.hybrid.weatherforecast.repository;

import com.rs.hybrid.weatherforecast.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
