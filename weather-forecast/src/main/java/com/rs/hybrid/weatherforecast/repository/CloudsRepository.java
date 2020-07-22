package com.rs.hybrid.weatherforecast.repository;

import com.rs.hybrid.weatherforecast.model.Clouds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloudsRepository extends JpaRepository<Clouds, Long> {
}
