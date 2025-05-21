package main.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "weather_anomalies")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Builder
public class WeatherAnomaly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city", nullable = false)
    private String cityName;
    @Column(name = "old_temperature", nullable = false)
    private Double oldTemperature;

    @Column(name = "new_temperature", nullable = false)
    private Double newTemperature;

    @Column(nullable = false)
    private Double difference;

    @Column(name = "detected_at",nullable = false)
    private LocalDateTime detectedAt;

    public WeatherAnomaly(Long id, String cityName, Double oldTemperature, Double newTemperature, Double difference, LocalDateTime detectedAt) {
        this.id = id;
        this.cityName = cityName;
        this.oldTemperature = oldTemperature;
        this.newTemperature = newTemperature;
        this.difference = difference;
        this.detectedAt = detectedAt;
    }
}
