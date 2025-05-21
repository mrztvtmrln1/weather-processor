package main.dto;

public record WeatherResponseMainDto(
        Double temp,
        Integer pressure,
        Integer humidity)
{}
