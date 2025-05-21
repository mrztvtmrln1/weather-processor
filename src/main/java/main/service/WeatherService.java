package main.service;

import main.dto.DailyWeatherSummaryDto;
import main.dto.WeatherResponseDto;
import main.endpoints.publisher.DailyWeatherSummaryPublisher;
import main.mapper.WeatherMapper;
import main.model.Weather;
import main.model.WeatherAnomaly;
import main.repository.WeatherAnomalyRepository;
import main.repository.WeatherRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final WeatherAnomalyRepository weatherAnomalyRepository;
    private final WeatherMapper weatherMapper;
    private final DailyWeatherSummaryPublisher dailyWeatherSummaryPublisher;

    public WeatherService(WeatherRepository weatherRepository,
                          WeatherAnomalyRepository weatherAnomalyRepository, WeatherMapper weatherMapper, DailyWeatherSummaryPublisher dailyWeatherSummaryPublisher) {
        this.weatherRepository = weatherRepository;
        this.weatherAnomalyRepository = weatherAnomalyRepository;
        this.weatherMapper = weatherMapper;
        this.dailyWeatherSummaryPublisher = dailyWeatherSummaryPublisher;
    }

    @Scheduled(cron = "0 11 * * * *")
    @Transactional
    public void sendDailySummary() {
        LocalDate today = LocalDate.now().minusDays(1);
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = start.plusDays(1);

        List<DailyWeatherSummaryDto> summaries = weatherRepository.findDailySummary(start, end);
        System.out.println("trying to send daily weather to queue");
        System.out.println(summaries);
        dailyWeatherSummaryPublisher.publishSummaries(summaries);
        System.out.println("message sent to queue for telegram bot");
    }

    public void save(WeatherResponseDto weatherResponseDto) {
        Weather lastWeather = weatherRepository.findTopByCityOrderByTimestampDesc(weatherResponseDto.name());
        Double lastTemp = lastWeather != null ? lastWeather.getTemperature() : null;

        Double currentTemp = weatherResponseDto.main().temp();

        if (lastTemp != null) {
            if (Math.abs(currentTemp - lastTemp) > 5.0) {
                WeatherAnomaly anomaly = WeatherAnomaly.builder()
                        .cityName(weatherResponseDto.name())
                        .oldTemperature(lastTemp)
                        .newTemperature(currentTemp)
                        .difference(Math.abs(currentTemp - lastTemp))
                        .detectedAt(LocalDateTime.now())
                        .build();
                weatherAnomalyRepository.save(anomaly);
            }
        }
        Weather weather = weatherMapper.toEntity(weatherResponseDto);
        weatherRepository.save(weather);
    }

}
