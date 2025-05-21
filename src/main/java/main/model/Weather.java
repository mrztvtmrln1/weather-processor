package main.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    @Column(nullable = false)
    private Double temperature;

    private Integer humidity;

    private Integer pressure;

    @Column(name = "wind_speed")
    private Double windSpeed;

    private String description;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Weather(Long id, String city, Double temperature, Integer humidity, Integer pressure, Double windSpeed, String description, LocalDateTime timestamp) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.description = description;
        this.timestamp = timestamp;
    }
}

