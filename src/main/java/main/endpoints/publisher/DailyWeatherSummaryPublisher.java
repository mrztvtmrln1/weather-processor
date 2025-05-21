package main.endpoints.publisher;

import lombok.RequiredArgsConstructor;
import main.config.RabbitMQConfig;
import main.dto.DailyWeatherSummaryDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DailyWeatherSummaryPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishSummaries(List<DailyWeatherSummaryDto> summaries) {
        summaries.forEach(summary ->
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, summary)
        );
    }
}
