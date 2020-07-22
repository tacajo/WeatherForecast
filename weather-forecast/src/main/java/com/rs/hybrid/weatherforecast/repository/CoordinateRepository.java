package com.rs.hybrid.weatherforecast.repository;

import com.rs.hybrid.weatherforecast.model.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
}
