package main.repository;

import main.model.WeatherAnomaly;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherAnomalyRepository extends JpaRepository<WeatherAnomaly, Long> {
}
