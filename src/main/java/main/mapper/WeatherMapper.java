package main.mapper;

import main.dto.WeatherResponseDto;
import main.model.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
//import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

//    WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

    @Mappings({
            @Mapping(source = "name", target = "city"),
            @Mapping(source = "main.temp", target = "temperature"),
            @Mapping(source = "main.humidity", target = "humidity"),
            @Mapping(source = "main.pressure", target = "pressure"),
            @Mapping(source = "wind.speed", target = "windSpeed"),
            @Mapping(source = "weather", target = "description", qualifiedByName = "firstDescription"),
            @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
    })
    Weather toEntity(WeatherResponseDto dto);

    @Named("firstDescription")
    static String mapFirstDescription(java.util.List<main.dto.WeatherResponseDescriptionDto> descriptions) {
        return descriptions != null && !descriptions.isEmpty() ? descriptions.get(0).description() : null;
    }
}
