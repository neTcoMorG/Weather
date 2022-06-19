package youngjun.bigdataProject.domain.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import youngjun.bigdataProject.domain.entity.WeatherEntity;
import youngjun.bigdataProject.domain.entity.mapping.WeatherData;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository repository;

    public WeatherEntity getWeather(String region) {
        Optional<WeatherEntity> find = repository.findByRegion(region);
        return find.orElse(null);
    }

    public WeatherEntity requestWeather(String region) {
        API api = new API(region);
        return new WeatherEntity(region, api.getWeather());
    }
}
