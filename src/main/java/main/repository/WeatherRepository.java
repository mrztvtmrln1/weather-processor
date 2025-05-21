package main.repository;

import main.dto.DailyWeatherSummaryDto;
import main.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Weather findTopByCityOrderByTimestampDesc(String city);

    @Query("SELECT new main.dto.DailyWeatherSummaryDto(w.city, MIN(w.temperature), MAX(w.temperature), AVG(w.temperature)) " +
            "FROM Weather w " +
            "WHERE w.timestamp >= :start AND w.timestamp < :end " +
            "GROUP BY w.city")
    List<DailyWeatherSummaryDto> findDailySummary(LocalDateTime start, LocalDateTime end);
}
