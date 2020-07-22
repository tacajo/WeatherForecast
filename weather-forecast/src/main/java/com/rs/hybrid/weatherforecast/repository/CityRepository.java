package com.rs.hybrid.weatherforecast.repository;

import com.rs.hybrid.weatherforecast.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
