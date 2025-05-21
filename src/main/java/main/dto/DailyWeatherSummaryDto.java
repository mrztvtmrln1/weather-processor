package main.dto;

public record DailyWeatherSummaryDto(
        String cityName,
        Double minTemp,
        Double maxTemp,
        Double avgTemp
) {
}
