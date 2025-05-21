package main.endpoints.listener;

import main.dto.WeatherResponseDto;
import main.service.WeatherService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class WeatherConsumer {

    private final WeatherService weatherService;

    public WeatherConsumer(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RabbitListener(queues = "weather-data")
    public void receiveWeatherData(WeatherResponseDto dto) {
        System.out.println("Получено сообщение: " + dto);
        weatherService.save(dto);
    }
}
