package com.rs.hybrid.weatherforecast.repository;

import com.rs.hybrid.weatherforecast.model.Wind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WindRepository extends JpaRepository<Wind, Long> {
}
