package main.mapper;

import javax.annotation.processing.Generated;
import main.dto.WeatherResponseDto;
import main.dto.WeatherResponseMainDto;
import main.dto.WeatherResponseWindDto;
import main.model.Weather;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-30T15:14:24+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class WeatherMapperImpl implements WeatherMapper {

    @Override
    public Weather toEntity(WeatherResponseDto dto) {
        if ( dto == null ) {
            return null;
        }

        Weather weather = new Weather();

        weather.setCity( dto.name() );
        weather.setTemperature( dtoMainTemp( dto ) );
        weather.setHumidity( dtoMainHumidity( dto ) );
        weather.setPressure( dtoMainPressure( dto ) );
        weather.setWindSpeed( dtoWindSpeed( dto ) );
        weather.setDescription( WeatherMapper.mapFirstDescription( dto.weather() ) );

        weather.setTimestamp( java.time.LocalDateTime.now() );

        return weather;
    }

    private Double dtoMainTemp(WeatherResponseDto weatherResponseDto) {
        if ( weatherResponseDto == null ) {
            return null;
        }
        WeatherResponseMainDto main = weatherResponseDto.main();
        if ( main == null ) {
            return null;
        }
        Double temp = main.temp();
        if ( temp == null ) {
            return null;
        }
        return temp;
    }

    private Integer dtoMainHumidity(WeatherResponseDto weatherResponseDto) {
        if ( weatherResponseDto == null ) {
            return null;
        }
        WeatherResponseMainDto main = weatherResponseDto.main();
        if ( main == null ) {
            return null;
        }
        Integer humidity = main.humidity();
        if ( humidity == null ) {
            return null;
        }
        return humidity;
    }

    private Integer dtoMainPressure(WeatherResponseDto weatherResponseDto) {
        if ( weatherResponseDto == null ) {
            return null;
        }
        WeatherResponseMainDto main = weatherResponseDto.main();
        if ( main == null ) {
            return null;
        }
        Integer pressure = main.pressure();
        if ( pressure == null ) {
            return null;
        }
        return pressure;
    }

    private Double dtoWindSpeed(WeatherResponseDto weatherResponseDto) {
        if ( weatherResponseDto == null ) {
            return null;
        }
        WeatherResponseWindDto wind = weatherResponseDto.wind();
        if ( wind == null ) {
            return null;
        }
        Double speed = wind.speed();
        if ( speed == null ) {
            return null;
        }
        return speed;
    }
}
